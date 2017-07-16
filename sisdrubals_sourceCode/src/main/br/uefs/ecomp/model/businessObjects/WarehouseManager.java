package br.uefs.ecomp.model.businessObjects;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.exception.InputFileMissing;
import br.uefs.ecomp.util.exception.InputInformationBigger;
import br.uefs.ecomp.util.exception.InputInformationMissing;

/**
 * @author Joao Victor & Resemblinck
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
	 * Método que realiza a leitura do arquivo recebido.
	 * @param fileName - Nome ou diretorio do arquivo.
	 * @return String - String contendo os erros encontrados durante a leitura.
	 * @throws InputFileMissing
	 * @throws DuplicatedLocalization
	 */
	public String readInputFile(String fileName) throws InputFileMissing, DuplicatedLocalization {
		String returnString = "";
		try {
			FileReader readingFile = new FileReader(fileName);	//Abre o arquivo apenas com permissoes de leitura
			BufferedReader readingNow = new BufferedReader(readingFile);	//cria um objeto que vai armazenar os valores do arquivo em um buffer
	 
			String lineReaded = readingNow.readLine(); /*inicia a leitura da primeira linha antes do laco para que possa encerrar o laco*/
			while (lineReaded != null) {	/*linha do final do arquivo de texto recebe null ao identificar o final do arquivo*/
				/*aqui insere funcao para corta a linha do arquivo em partes para armazenar os dados corretamente*/
				String[] splitedString = lineReaded.split(";");
				if(splitedString.length < 7){
					returnString += new InputInformationMissing();
					System.err.printf("Conteudo da linha contem poucas informacoes");
				}
				else if(splitedString.length > 7){
					returnString += new InputInformationBigger();
					System.err.printf("Conteudo da linha contem informacoes demais");
					lineReaded = readingNow.readLine(); /*le a proxima do arquivo*/
					continue;
				}

				LocalDate insertDate = LocalDate.parse(splitedString[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")); //Converte uma string em LocalDate
				LocalTime insertHour = LocalTime.parse(splitedString[6], DateTimeFormatter.ofPattern("HH:mm")); //Converte uma string em LocalTime
				Localization newLocalization = new Localization(Integer.parseInt(splitedString[0]), splitedString[1], splitedString[2], Integer.parseInt(splitedString[3]));
				Merchandise newMerchandise = new Merchandise(newLocalization, splitedString[4], insertDate, insertHour);
				if(this.stockedProducts.search(newMerchandise) == null)
					this.stockedProducts.add(newMerchandise);	/*adiciona a Merchandise na arvore*/
				else{
					System.err.printf("Mercadoria duplicada");
					returnString += new DuplicatedLocalization(newLocalization);
				}
				lineReaded = readingNow.readLine(); /*le as linhas restantes do arquivo*/
			}
			readingFile.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",	e.getMessage());
			throw new InputFileMissing(fileName);
		}
		return returnString;
	}

	/**
	 * Método que serializa a árvore gravando todos os elementos (Mercadorias) em um arquivo, para que possam ser recuperados posteriormente.
	 */
	public void saveProgram() {
		try{
			//Cria arquivo no disco rigido utilizando FileOutputStream, para posteriormente gravar as informacoes
			FileOutputStream fileSaving = new FileOutputStream("../binaryTree.ser");
			
			//Usa a classe ObjectOutputStream para escrever a arvore no arquivo
			ObjectOutputStream objectSaver = new ObjectOutputStream(fileSaving);	
			
			objectSaver.writeObject(this.stockedProducts);	//grava a arvore no arquivo
			
			objectSaver.close(); //fecha o arquivo
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Método que desserializa uma árvore gravada em uma execução enterior do programa, recuperando todas as mercadorias.
	 * 
	 * @return Boolean - True caso os dados sejam recuperados com sucesso, false caso contrário.
	 */
	@SuppressWarnings("unchecked")
	public boolean loadProgram() {
		 try{
			
			//Abre o arquivo onde estava salva a arvore
			FileInputStream fileReader = new FileInputStream("../binaryTree.ser");
			
			//le o objeto arvore salva no arquivo
			ObjectInputStream receivedTree = new ObjectInputStream(fileReader);
			
			//pega o que tem no arquivo e converte para a arvore a ser utilizada
			this.stockedProducts = (AvlTree<Merchandise>) receivedTree.readObject();
			receivedTree.close(); //fecha o arquivo
			return true;
	 
		}catch(Exception ex){
			ex.printStackTrace(); 
		}
		return false;
	}

	/**
	 * Método para registrar uma nova mercadoria na árvore.
	 * 
	 * @param receivedLocalization - Localização da mercadoria
	 * @param providerReceived - Fornecedor.
	 * @param dateReceived - Data de inserção.
	 * @param timeReceived - Hora da inserção.
	 * @throws DuplicatedLocalization
	 */
	public void registerMerchandise(Localization receivedLocalization, String providerReceived, LocalDate dateReceived, LocalTime timeReceived) throws DuplicatedLocalization {
		this.stockedProducts.add(new Merchandise(receivedLocalization, providerReceived, dateReceived, timeReceived));
	}

	/**
	 * Método que recebe os campos da localização da mercadoria para removê-la da árvore.
	 * 
	 * @param merchandise - Mercadoria com a localização a ser removida.
	 * @return Merchandise - Referência para a mercadoria removida ou null caso ela não seja encontrada.
	 */
	public Merchandise removeMerchandise(Merchandise merchandise) {
		return this.stockedProducts.remove(merchandise);
	}

	/**
	 * Método que recebe os campos da localização da mercadoria para buscá-la na árvore.
	 * 
	 * @param merchandise - Mercadoria com a localização a ser buscada.
	 * @return Merchandise - Referência para a mercadoria encontrada ou null caso ela não seja encontrada.
	 */
	public Merchandise searchMerchandise(Merchandise merchandise) {
		return this.stockedProducts.search(merchandise);
	}

	/**
	 * Método para realizar a listagem dos elementos da árvore.
	 * 
	 * @return Iterator - Retorna um iterador para que a listagem possa ser realizada.
	 */
	public Iterator<Merchandise> listAll() {
		return this.stockedProducts.list();
	}

	/**
	 * Método para verificar a quantidade total de mercadorias presente no armazem.
	 * 
	 * @return int - Número de elementos da árvore, que também é o número total de mercadorias.
	 */
	public int totalMerchandise() {
		return this.stockedProducts.size();
	}

}