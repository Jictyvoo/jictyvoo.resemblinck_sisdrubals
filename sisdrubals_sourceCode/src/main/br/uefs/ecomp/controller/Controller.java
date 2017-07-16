package br.uefs.ecomp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.uefs.ecomp.model.businessObjects.WarehouseManager;
import br.uefs.ecomp.model.valueObjects.Localization;
import br.uefs.ecomp.model.valueObjects.Merchandise;
import br.uefs.ecomp.util.Iterator;
import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.exception.InputFileMissing;

/**
 * @author Joao Victor & Resemblinck Freitas
 */
public class Controller {

	private WarehouseManager warehouseInstance;
	
	private static Controller controllerInstance;

	/**
	 * Default constructor
	 */
	private Controller() {
		this.warehouseInstance = new WarehouseManager();
	}
	
	/**
	 * Método para instânciar o Controller apenas uma única vez
	 * @return Controller - Intância criada
	 */
	public static Controller getInstance(){
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}

	/**
	 * Método para realizar a leitura do arquivo de entrada.
	 * 
	 * @return String - String contendo os erros encontrados durante a leitura.
	 * @throws InputFileMissing 
	 * @throws DuplicatedLocalization 
	 */
	public String readInputFile(String fileName) throws InputFileMissing, DuplicatedLocalization {
		return warehouseInstance.readInputFile(fileName);
	}

	/**
	 * Método para salvar os dados que estiver na árvore no momento em que o método for chamado.
	 */
	public void saveProgram() {
		warehouseInstance.saveProgram();
	}

	/**
	 * Método para carregar todos os dados salvos em uma execução anterior do programa.
	 * @return Boolean - True caso os dados sejam recuperados com sucesso, false caso contrário.
	 */
	public boolean loadProgram() {
		return warehouseInstance.loadProgram();
	}

	/**
	 * Método para registrar uma nova mercadoria na árvore.
	 * 
	 * @param lotId - Id do lote da mercadoria a ser inserida.
	 * @param adress - Endereço da localização.
	 * @param block - Bloco da localização.
	 * @param merchandiseNumber - Número da mercadoria.
	 * @param providerReceived - Fornecedor.
	 * @param dateReceived - Data de inserção.
	 * @param timeReceived - Hora da inserção.
	 * @throws DuplicatedLocalization
	 */
	public void registerMerchandise(int lotId, String adress, String block, int merchandiseNumber, String providerReceived, String dateReceived, String timeReceived) throws DuplicatedLocalization {
		Localization localization = new Localization(lotId, adress, block, merchandiseNumber);
		LocalDate insertDate = LocalDate.parse(dateReceived, DateTimeFormatter.ofPattern("dd/MM/yyyy")); //Converte uma string em LocalDate.
		LocalTime insertHour = LocalTime.parse(timeReceived, DateTimeFormatter.ofPattern("HH:mm")); //Converte uma string em LocalTime.
		warehouseInstance.registerMerchandise(localization, providerReceived, insertDate, insertHour);
	}

	/**
	 * Método que recebe os campos da localização da mercadoria para removê-la da árvore.
	 * 
	 * @param lotId - Id do lote da mercadoria.
	 * @param adress - Endereço da localização da mercadoria.
	 * @param block - Bloco de localização.
	 * @param merchandiseNumber - Número da mercadoria.
	 * @return Merchandise - Referência para a mercadoria removida ou null caso ela não seja encontrada.
	 */
	public Merchandise removeMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.removeMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * Método que recebe os campos da localização da mercadoria para buscá-la na árvore.
	 * 
	 * @param lotId - Id do lote da mercadoria.
	 * @param adress - Endereço da localização da mercadoria.
	 * @param block - Bloco de localização.
	 * @param merchandiseNumber - Número da mercadoria.
	 * @return Merchandise - Referência para a mercadoria encontrada ou null caso ela não seja encontrada.
	 */
	public Merchandise searchMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.searchMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * Método para realizar a listagem dos elementos da árvore.
	 * 
	 * @return Iterator - Retorna um iterador para que a listagem possa ser realizada.
	 */
	public Iterator<Merchandise> listAll() {
		return warehouseInstance.listAll();
	}

	/**
	 * Método para verificar a quantidade total de mercadorias presente no armazem.
	 * 
	 * @return int - Número de elementos da árvore, que também é o número total de mercadorias.
	 */
	public int totalMerchandise() {
		return warehouseInstance.totalMerchandise();
	}

}