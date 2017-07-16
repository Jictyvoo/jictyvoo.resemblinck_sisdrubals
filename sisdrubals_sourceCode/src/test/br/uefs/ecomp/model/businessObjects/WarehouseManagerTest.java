package br.uefs.ecomp.model.businessObjects;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.model.businessObjects2.WarehouseManager;
import br.uefs.ecomp.util.exception2.AlienCaractere;
import br.uefs.ecomp.util.exception2.DuplicatedLocalization;
import br.uefs.ecomp.util.exception2.InputFileMissing;
import br.uefs.ecomp.util.exception2.InputInformationBigger;
import br.uefs.ecomp.util.exception2.InputInformationMissing;
import br.uefs.ecomp.util.exception2.InvalidDateTime;
import junit.framework.TestCase;

/**
 * @author Joao Victor & Resemblinck
 */
public class WarehouseManagerTest extends TestCase {
	
	@Before
	public void setUp(){/*Do nothing*/}
	
	@Test
	public void testExcecaoInputFileMissing(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("armazemDoesntExist.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof InputFileMissing);
		};
	}
	
	@Test
	public void testExcecaoDuplicatedLocalization(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("../../../../../../../../../armazem.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof DuplicatedLocalization);
		};
	}
	
	@Test
	public void testExcecaoCaractereAlienigena(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("../../../../../../../../armazem.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof AlienCaractere);
		};
	}
	
	@Test
	public void testExcecaoInputInformationBigger(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("../../../../../../../../armazem.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof InputInformationBigger);
		};
	}
	
	@Test
	public void testExcecaoInputInformationMissing(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("../../../../../../../../armazem.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof InputInformationMissing);
		};
	}
	
	@Test
	public void testExcecaoInvalidDateTime(){
		WarehouseManager warehouseTest = new WarehouseManager();
		try{
			warehouseTest.readInputFile("../../../../../../../../armazem.csv");
			assertFalse("Exception not throw correct", false);
		} catch (Exception exceptionFound) {
			assertTrue("Exception as correct", exceptionFound instanceof InvalidDateTime);
		};
	}
   
}