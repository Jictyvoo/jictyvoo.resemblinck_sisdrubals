package br.uefs.ecomp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.uefs.ecomp.model.businessObjects.WarehouseManager;
import br.uefs.ecomp.model.valueObjects.Localization;
import br.uefs.ecomp.model.valueObjects.Merchandise;
import br.uefs.ecomp.util.Iterator;
import br.uefs.ecomp.util.exception.InputFileMissing;

/**
 * @author Joao Victor & Resemblinck
 */
public class Controller {

	/**
	 * 
	 */
	private WarehouseManager warehouseInstance;
	
	private static Controller controllerInstance;

	/**
	 * Default constructor
	 */
	private Controller() {
		this.warehouseInstance = new WarehouseManager();
	}
	
	public static Controller getInstance(){
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}

	/**
	 * @return
	 * @throws InputFileMissing 
	 */
	public String readInputFile(String fileName) throws InputFileMissing {
		return warehouseInstance.readInputFile(fileName);
	}

	/**
	 * @return
	 */
	public void saveProgram() {
		warehouseInstance.saveProgram();
	}

	/**
	 * @return
	 */
	public boolean loadProgram() {
		return warehouseInstance.loadProgram();
	}

	/**
	 * @param receivedMerchandise 
	 * @return
	 */
	public void registerMerchandise(int lotId, String adress, String block, int merchandiseNumber, String providerReceived, String dateReceived, String timeReceived) {
		Localization localization = new Localization(lotId, adress, block, merchandiseNumber);
		LocalDate insertDate = LocalDate.parse(dateReceived, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalTime insertHour = LocalTime.parse(timeReceived, DateTimeFormatter.ofPattern("HH:mm"));
		warehouseInstance.registerMerchandise(localization, providerReceived, insertDate, insertHour);
	}

	/**
	 * @param merchandiseId 
	 * @return
	 */
	public boolean removeMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.removeMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * @param provider 
	 * @param location 
	 * @return
	 */
	public Merchandise searchMerchandise(int lotId, String adress, String block, int merchandiseNumber) {
		return warehouseInstance.searchMerchandise(new Merchandise(new Localization(lotId, adress, block, merchandiseNumber)));
	}

	/**
	 * @return
	 */
	public Iterator<Merchandise> listAll() {
		return warehouseInstance.listAll();
	}

	/**
	 * @return
	 */
	public int totalMerchandise() {
		return warehouseInstance.totalMerchandise();
	}

}