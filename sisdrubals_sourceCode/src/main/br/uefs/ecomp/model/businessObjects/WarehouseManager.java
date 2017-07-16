package br.uefs.ecomp.model.businessObjects;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.uefs.ecomp.model.valueObjects.Localization;
import br.uefs.ecomp.model.valueObjects.Merchandise;
import br.uefs.ecomp.util.AvlTree;
import br.uefs.ecomp.util.Iterator;
import br.uefs.ecomp.util.exception.DataOverlayException;
import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.exception.IncorrectFormatException;
import br.uefs.ecomp.util.exception.InputInformationIncorrect;

/**
 * @author Joao Victor & Resemblinck Freitas
 */
public class WarehouseManager {

	/**
	 * Arvore que armazena as mercadorias estocadas no armazem.
	 */
	private AvlTree<Merchandise> stockedProducts;

	/**
	 * Inicializa o construtor
	 */
	public WarehouseManager() {
		this.stockedProducts = new AvlTree<Merchandise>();
	}

	/**
	 * M�todo que realiza a leitura do arquivo recebido.
	 * @param fileName - Nome ou diretorio do arquivo.
	 * @return String[] - Vetor de String contendo os erros encontrados durante a leitura.
	 * @throws InputFileMissing
	 * @throws IOException 
	 */
	public String[] readInputFile(String fileName) throws IOException {
		String returnString = ""; //String para armazenar erros encontrados
		int lineNumber = 1; //Contador de linhas do arquivo
		FileReader readingFile = null;
		BufferedReader readingNow = null;
		try {
			readingFile = new FileReader(fileName); //Tenta abrir o arquivo
			readingNow = new BufferedReader(readingFile); //Buffer para realizar a leitura
	 
			String lineReaded = null;
			while ((lineReaded = readingNow.readLine()) != null) { //L� cada linha do arquivo at� chegar no final
			
				try {
					
					registerMerchandise(lineReaded); //Verifica a validade dos dados e tenta registrar a Merchandise na arvore
					
				} catch (DuplicatedLocalization e) { //Erro de Mercadoria repetida
					returnString += new DuplicatedLocalization(lineNumber) + ";";	
				} catch (InputInformationIncorrect e) { //Erro na quantidade de dados passados
					returnString += new InputInformationIncorrect(lineNumber) + ";";
				} catch (IncorrectFormatException e) { //Erro no formato dos dados de entrada
					returnString += new IncorrectFormatException(lineNumber) + ";";
				} finally {
					lineNumber++;
				}
			}
		} catch (IOException exceptionFound) { //Erro na abertura do arquivo
			returnString += exceptionFound.getMessage();
			throw exceptionFound;
		}
		finally { //Fecha o arquivo
			if(readingFile != null){
				readingFile.close();
				if(readingNow != null)
					readingNow.close();
			}
		}
		return returnString.split(";"); //Retorna um vetor de String com os erros encontrados
	}

	/**
	 * M�todo que serializa a �rvore gravando todos os elementos (Mercadorias) em um arquivo, para que possam ser recuperados posteriormente.
	 * @throws IOException 
	 */
	public void saveProgram() throws IOException {
		FileOutputStream fileSaving = null;
		ObjectOutputStream objectSaver = null;

			try {
				//Cria arquivo no disco rigido utilizando FileOutputStream, para posteriormente gravar as informacoes
				fileSaving = new FileOutputStream("../binaryTree.ser");
				//Usa a classe ObjectOutputStream para escrever a arvore no arquivo
				objectSaver = new ObjectOutputStream(fileSaving);
				objectSaver.writeObject(this.stockedProducts); //grava a arvore no arquivo
				
			} catch (FileNotFoundException e) { //Erro ao buscar ou tentar criar o arquivo
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally { //Fecha o arquivo
				if(fileSaving != null){
					fileSaving.close();
					if(objectSaver != null)
						objectSaver.close();
				}
			}
			
	}

	/**
	 * M�todo que desserializa uma �rvore gravada em uma execu��o enterior do programa, recuperando todas as mercadorias.
	 * 
	 * @return Boolean - True caso os dados sejam recuperados com sucesso, false caso contr�rio.
	 * @throws IOException 
	 * @throws DataOverlayException 
	 */
	@SuppressWarnings("unchecked")
	public void loadProgram() throws IOException, DataOverlayException {
			FileInputStream fileReader = null;
			boolean throwException = false;
			ObjectInputStream receivedTree = null;
			try {
				fileReader = new FileInputStream("../binaryTree.ser"); //Abre o arquivo onde estava salva a arvore
				receivedTree = new ObjectInputStream(fileReader); //le o objeto arvore salva no arquivo
				
				if(!this.stockedProducts.isEmpty()) //Verifica se ja existem dados cadastrados
					throwException = true;
				else
					this.stockedProducts = (AvlTree<Merchandise>) receivedTree.readObject(); //pega o que tem no arquivo e converte para a arvore a ser utilizada
			
			} catch (FileNotFoundException e) { //Erro ao buscar o arquivo
				throw e;
			} catch (ClassNotFoundException e) { //Erro ao tentar desserializar a arvore
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally { //Fecha o arquivo
				if(fileReader != null){
					fileReader.close();
					if(receivedTree != null)
						receivedTree.close();
				}
			}
			
			if(throwException)
				throw new DataOverlayException("J� existem dados cadastrados! Prosseguir com esta opera��o resultaria "
						+ "em uma sopreposi��o.");
	}

	/**
	 * M�todo para registrar uma nova mercadoria na �rvore.
	 * 
	 * @param attributes - String contendo todos os atributos necess�rios para criar uma Merchandise.
	 * @throws InputInformationIncorrect
	 * @throws IncorrectFormatException
	 * @throws DuplicatedLocalization
	 */
	public void registerMerchandise(String attributes) throws InputInformationIncorrect, IncorrectFormatException, DuplicatedLocalization  {
		String[] splitedString = attributes.split(";"); //Quebra a String recebida para separar os dados
		if(splitedString.length != 7){ //Verifica se a quantidade de dados est� correta
			throw new InputInformationIncorrect();
		}
		if(!splitedString[0].matches("\\d*")){ //Verifica se o id do lote cont�m o formato certo
			throw new IncorrectFormatException(splitedString[0]);
		}
		if(!splitedString[1].matches("\\d*")){ //Verifica se o endere�o de localiza��o cont�m o formato certo
			throw new IncorrectFormatException(splitedString[1]);
		}
		if(!splitedString[2].matches("[A-Z]*\\d*")){ //Verifica se o bloco de localiza��o cont�m o formato certo
			throw new IncorrectFormatException(splitedString[2]);
		}
		if(!splitedString[3].matches("\\d*")){ //Verifica se o n�mero da mercadoria cont�m o formato certo
			throw new IncorrectFormatException(splitedString[3]);
		}
		if(!splitedString[4].matches("[F]\\d*")){ //Verifica se o fornecedor cont�m o formato certo
			throw new IncorrectFormatException(splitedString[4]);
		}
		if(!splitedString[5].matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")){ //Verifica se a data de inser��o cont�m o formato certo
			throw new IncorrectFormatException(splitedString[5]);
		}
		if(!splitedString[6].matches("\\d\\d:\\d\\d")){ //Verifica se a hora de inser��o cont�m o formato certo
			throw new IncorrectFormatException(splitedString[6]);
		}
		LocalDate insertDate = LocalDate.parse(splitedString[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")); //Converte uma string em LocalDate
		LocalTime insertHour = LocalTime.parse(splitedString[6], DateTimeFormatter.ofPattern("HH:mm")); //Converte uma string em LocalTime
		Localization newLocalization = new Localization(Integer.parseInt(splitedString[0]), splitedString[1], splitedString[2], Integer.parseInt(splitedString[3]));
		this.stockedProducts.add(new Merchandise(newLocalization, splitedString[4], insertDate, insertHour)); //Adiciona a Merchandise na arvore
	}

	/**
	 * M�todo que recebe os campos da localiza��o da mercadoria para remov�-la da �rvore.
	 * 
	 * @param merchandise - Mercadoria com a localiza��o a ser removida.
	 * @return Merchandise - Refer�ncia para a mercadoria removida ou null caso ela n�o seja encontrada.
	 */
	public Merchandise removeMerchandise(Merchandise merchandise) {
		return this.stockedProducts.remove(merchandise);
	}

	/**
	 * M�todo que recebe os campos da localiza��o da mercadoria para busc�-la na �rvore.
	 * 
	 * @param merchandise - Mercadoria com a localiza��o a ser buscada.
	 * @return Merchandise - Refer�ncia para a mercadoria encontrada ou null caso ela n�o seja encontrada.
	 */
	public Merchandise searchMerchandise(Merchandise merchandise) {
		return this.stockedProducts.search(merchandise);
	}

	/**
	 * M�todo para realizar a listagem dos elementos da �rvore.
	 * 
	 * @return Iterator - Retorna um iterador para que a listagem possa ser realizada.
	 */
	public Iterator<Merchandise> listAll() {
		return this.stockedProducts.list();
	}

	/**
	 * M�todo para verificar a quantidade total de mercadorias presente no armazem.
	 * 
	 * @return int - N�mero de elementos da �rvore, que tamb�m � o n�mero total de mercadorias.
	 */
	public int totalMerchandise() {
		return this.stockedProducts.size();
	}

}