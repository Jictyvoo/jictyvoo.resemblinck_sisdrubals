package br.uefs.ecomp.model.businessObjects;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.util.exception.AlienCaractere;
import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.exception.InputFileMissing;
import br.uefs.ecomp.util.exception.InputInformationBigger;
import br.uefs.ecomp.util.exception.InputInformationMissing;
import br.uefs.ecomp.util.exception.InvalidDateTime;
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