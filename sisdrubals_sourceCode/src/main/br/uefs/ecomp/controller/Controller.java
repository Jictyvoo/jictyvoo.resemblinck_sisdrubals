package br.uefs.ecomp.controller;

import java.io.IOException;

import br.uefs.ecomp.model.businessObjects.WarehouseManager;
import br.uefs.ecomp.model.valueObjects.Localization;
import br.uefs.ecomp.model.valueObjects.Merchandise;
import br.uefs.ecomp.util.Iterator;
import br.uefs.ecomp.util.exception.DataOverlayException;
import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.exception.IncorrectFormatException;
import br.uefs.ecomp.util.exception.InputInformationIncorrect;

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
	 * M�todo para inst�nciar o Controller apenas uma �nica vez
	 * @return Controller - Int�ncia criada
	 */
	public static Controller getInstance(){
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}

	/**
	 * M�todo para realizar a leitura do arquivo de entrada.
	 * 
	 * @return String - String contendo os erros encontrados durante a leitura.
	 * @throws InputFileMissing 
	 * @throws DuplicatedLocalization 
	 * @throws IOException 
	 */
	public String[] readInputFile(String fileName) throws DuplicatedLocalization, IOException {
		return warehouseInstance.readInputFile(fileName);
	}

	/**
	 * M�todo para salvar os dados que estiver na �rvore no momento em que o m�todo for chamado.
	 * @throws IOException 
	 */
	public void saveProgram() throws IOException {
		warehouseInstance.saveProgram();
	}

	/**
	 * M�todo para carregar todos os dados salvos em uma execu��o anterior do programa.
	 * @return Boolean - True caso os dados sejam recuperados com sucesso, false caso contr�rio.
	 * @throws IOException 
	 */
	public void loadProgram() throws IOException {
		try {
			warehouseInstance.loadProgram();
		} catch (DataOverlayException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * M�todo para registrar uma nova mercadoria na �rvore.
	 * 
	 * @param lotId - Id do lote da mercadoria a ser inserida.
	 * @param adress - Endere�o da localiza��o.
	 * @param block - Bloco da localiza��o.
	 * @param merchandiseNumber - N�mero da mercadoria.
	 * @param providerReceived - Fornecedor.
	 * @param dateReceived - Data de inser��o.
	 * @param timeReceived - Hora da inser��o.
	 * @throws DuplicatedLocalization
	 */
	public void registerMerchandise(int lotId, String adress, String block, int merchandiseNumber, String providerReceived, String dateReceived, String timeReceived) throws DuplicatedLocalization {
		String merchandise = lotId + ";" + adress + ";" + block + ";" + merchandiseNumber + ";" + providerReceived
				 + ";"+ dateReceived + ";" + timeReceived;
		try {
			warehouseInstance.registerMerchandise(merchandise);
		} catch (DuplicatedLocalization e) {
			System.out.println(e.getMessage());	
		} catch (InputInformationIncorrect e) {
			System.out.println(e.getMessage());
		} catch (IncorrectFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * M�todo que recebe os campos da localiza��o da mercadoria para remov�-la da �rvore.
	 * 
	 * @param lotId - Id do lote da mercadoria.
	 * @param adress - Endere�o da localiza��o da mercadoria.
	 * @param block - Bloco de localiza��o.
	 * @param merchandiseNumber - N�mero da mercadoria.
	 * @return Merchandise - Refer�ncia para a mercadoria removida ou null caso ela n�o seja encontrada.
	 */
	public Merchandise removeMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.removeMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * M�todo que recebe os campos da localiza��o da mercadoria para busc�-la na �rvore.
	 * 
	 * @param lotId - Id do lote da mercadoria.
	 * @param adress - Endere�o da localiza��o da mercadoria.
	 * @param block - Bloco de localiza��o.
	 * @param merchandiseNumber - N�mero da mercadoria.
	 * @return Merchandise - Refer�ncia para a mercadoria encontrada ou null caso ela n�o seja encontrada.
	 */
	public Merchandise searchMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.searchMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * M�todo para realizar a listagem dos elementos da �rvore.
	 * 
	 * @return Iterator - Retorna um iterador para que a listagem possa ser realizada.
	 */
	public Iterator<Merchandise> listAll() {
		return warehouseInstance.listAll();
	}

	/**
	 * M�todo para verificar a quantidade total de mercadorias presente no armazem.
	 * 
	 * @return int - N�mero de elementos da �rvore, que tamb�m � o n�mero total de mercadorias.
	 */
	public int totalMerchandise() {
		return warehouseInstance.totalMerchandise();
	}

}