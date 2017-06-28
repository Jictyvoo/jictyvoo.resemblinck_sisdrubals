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
import br.uefs.ecomp.util.BinaryTree;
import br.uefs.ecomp.util.Iterator;
import br.uefs.ecomp.util.exception.InputInformationBigger;
import br.uefs.ecomp.util.exception.InputInformationMissing;

/**
 * @author Joao Victor & Resemblinck
 */
public class WarehouseManager {

	/**
	 * Default constructor
	 */
	public WarehouseManager() {
	}

	/**
	 * Arvore que armazena os produtos estocados no armazem bem como os lotes que contem tais produtos
	 */
	private BinaryTree stockedProducts;


	/**
	 * @return
	 * @throws InputInformationMissing 
	 * @throws InputInformationBigger 
	 */
	public boolean readInputFile(String fileName) {
		try {
			FileReader readingFile = new FileReader(fileName);	/*abre o arquivo apenas com permissoes de leitura*/
			BufferedReader readingNow = new BufferedReader(readingFile);	/*cria um objeto que vai armazenar os valores do arquivo em um buffer*/
	 
			String lineReaded = readingNow.readLine(); /*inicia a leitura da primeira linha antes do laco para que possa encerrar o laco*/
			while (lineReaded != null) {	/*linha do final do arquivo de texto recebe null ao identificar o final do arquivo*/
				/*aqui insere funcao para corta a linha do arquivo em tres partes para armazenar os dados corretamente*/
				String[] splitedString = lineReaded.split(";");
				if(splitedString.length < 7){
					try {
						throw new InputInformationMissing();
					} catch (InputInformationMissing e) {
						e.printStackTrace();
					}
				}
				else if(splitedString.length > 7){
					try {
						throw new InputInformationBigger();
					} catch (InputInformationBigger e) {
						e.printStackTrace();
					}
					lineReaded = readingNow.readLine(); /*le a proxima do arquivo*/
					continue;
				}

				LocalDate insertDate = LocalDate.parse(splitedString[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalTime insertHour = LocalTime.parse(splitedString[6], DateTimeFormatter.ofPattern("HH:mm"));
				Localization newLocalization = new Localization(Integer.parseInt(splitedString[0]), splitedString[1], splitedString[2], Integer.parseInt(splitedString[3]));
				Merchandise newMerchandise = new Merchandise(newLocalization, splitedString[4], insertDate, insertHour);
				this.stockedProducts.add(newMerchandise);	/*adiciona a Merchandise na arvore*/
				lineReaded = readingNow.readLine(); /*le as linhas restantes do arquivo*/
			}
			readingFile.close();
			return true;
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",	e.getMessage());
		}
		return false;
	}

	/**
	 * @return
	 */
	public void saveProgram() {
		try{
		
		/*Cria arquivo no disco rigido utilizando FileOutputStream, para posteriormente gravar as informacoes*/
		FileOutputStream fileSaving = new FileOutputStream("../binaryTree.ser");
		
		/*Usa a classe ObjectOutputStream para escrever a arvore no arquivo*/
		ObjectOutputStream objectSaver = new ObjectOutputStream(fileSaving);	
		
		objectSaver.writeObject(this.stockedProducts);	/*grava a arvore no arquivo*/
		
		objectSaver.close();
 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public boolean loadProgram() {
		 try{
			
			/*Abre o arquivo onde estava salva a arvore*/
			FileInputStream fileReader = new FileInputStream("../binaryTree.ser");
			
			/*le o objeto arvore salva no arquivo*/
			ObjectInputStream receivedTree = new ObjectInputStream(fileReader);
			
			/*pega o que tem no arquivo e converte para a arvore a ser utilizada*/
			this.stockedProducts = (BinaryTree) receivedTree.readObject();
			receivedTree.close();
			return true;
	 
		}catch(Exception ex){
			ex.printStackTrace(); 
		}
		return false;
	}

	/**
	 * @param receivedMerchandise 
	 * @return
	 */
	public boolean registerMerchandise(Merchandise receivedMerchandise) {
		// TODO implement here
		return false;
	}

	/**
	 * @param merchandiseId 
	 * @return
	 */
	public boolean removeMerchandise(int merchandiseId) {
		// TODO implement here
		return false;
	}

	/**
	 * @param provider 
	 * @param location 
	 * @return
	 */
	public Merchandise searchMerchandise(String provider, String location) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public Iterator listAll() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int totalMerchandise() {
		return this.stockedProducts.size();
	}

}