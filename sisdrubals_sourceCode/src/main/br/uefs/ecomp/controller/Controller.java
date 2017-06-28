package br.uefs.ecomp.controller;

import br.uefs.ecomp.model.businessObjects.WarehouseManager;
import br.uefs.ecomp.model.valueObjects.Merchandise;
import br.uefs.ecomp.util.Iterator;

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
	
	public Controller getInstance(){
		if(controllerInstance == null)
			controllerInstance = new Controller();
		return controllerInstance;
	}

	/**
	 * @return
	 */
	public boolean readInputFile(String fileName) {
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
	public boolean registerMerchandise(Merchandise receivedMerchandise) {
		return warehouseInstance.registerMerchandise(receivedMerchandise);
	}

	/**
	 * @param merchandiseId 
	 * @return
	 */
	public boolean removeMerchandise(int merchandiseId) {
		return warehouseInstance.removeMerchandise(merchandiseId);
	}

	/**
	 * @param provider 
	 * @param location 
	 * @return
	 */
	public Merchandise searchMerchandise(String provider, String location) {
		return warehouseInstance.searchMerchandise(provider, location);
	}

	/**
	 * @return
	 */
	public Iterator listAll() {
		return warehouseInstance.listAll();
	}

	/**
	 * @return
	 */
	public int totalMerchandise() {
		return warehouseInstance.totalMerchandise();
	}

}