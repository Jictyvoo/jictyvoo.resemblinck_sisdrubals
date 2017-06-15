package br.uefs.ecomp.model.businessObjects;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.uefs.ecomp.model.valueObjects.Mercadoria;
import br.uefs.ecomp.util.BinaryTree;
import br.uefs.ecomp.util.Iterator;

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
	 */
	public boolean readInputFile(String fileName) {
		// TODO implement here
		try {
			FileReader readingFile = new FileReader(fileName);	/*abre o arquivo apenas com permissoes de leitura*/
			BufferedReader readingNow = new BufferedReader(readingFile);	/*cria um objeto que vai armazenar os valores do arquivo em um buffer*/
	 
			String lineReaded = readingNow.readLine(); /*inicia a leitura da primeira linha antes do laco para que possa encerrar o laco*/
			while (lineReaded != null) {	/*linha do final do arquivo de texto recebe null ao identificar o final do arquivo*/
				/*aqui insere funcao para corta a linha do arquivo em tres partes para armazenar os dados corretamente*/
				String[] splitedString = lineReaded.split(",|,\\s");
				if(splitedString.length < 3){
					//throw Exception; lanca excecao
					lineReaded = readingNow.readLine(); /*le as linhas restantes do arquivo*/
					continue;
				}
				Mercadoria newMerchandise = new Mercadoria(Integer.parseInt(splitedString[0]), splitedString[1]);
				newMerchandise.getHoraInsercao();	/*para tirar o warning por enquanto --> deletar*/
				/*adiciona a mercadoria em um lote que seria pesquisado na arvore*/
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
		// TODO implement here
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
		// TODO implement here
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
	public boolean registerMerchandise(Mercadoria receivedMerchandise) {
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
	public Mercadoria searchMerchandise(String provider, String location) {
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