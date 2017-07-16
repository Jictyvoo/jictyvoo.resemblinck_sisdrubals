package br.uefs.ecomp.model.businessObjects;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.model.businessObjects.WarehouseManager;
import br.uefs.ecomp.util.exception.*;
import junit.framework.TestCase;

/**
 * @author Joao Victor & Resemblinck
 */
public class WarehouseManagerTest extends TestCase {
	
	private WarehouseManager warehouseTest = new WarehouseManager();
	
	@Before
	public void setUp() throws IOException{
			String [] mercadorias = {"1;16587;B1;513;F1;22/03/2017;12:00",
									 "A2;29876;B2;667;F2;25/04/2017;13:00",
									 "1;3;93090;C1;335;F3;10/05/2017;14:00",
									 "4;22234;A1;009;F4;09/06/2017;15:30",
									 "5;87676;B3;444;F3;12/06/2017;22:45",
									 "1;16587;B1;513;F1;22/03/2017;12:00"};
			FileWriter arq = new FileWriter("../armazem.csv");
			PrintWriter gravarArq = new PrintWriter(arq);
			for(int i = 0; i < mercadorias.length; i++){
				gravarArq.print(mercadorias[i] + (i == mercadorias.length - 1 ? "" : "\n"));
			}
			gravarArq.close();
	}
	
	@Test
	public void testIncorrectFormatException() {
		String excecoes = new IncorrectFormatException(2) + ";" + new InputInformationIncorrect(3)
				+ ";" + new DuplicatedLocalization(6);
		String[] ex = excecoes.split(";");
		String[] retorno;
		try {
			retorno = warehouseTest.readInputFile("../armazem.csv");
			for(int i = 0; i < ex.length; i++)
				assertEquals(ex[i], retorno[i]);
			
		} catch (IOException e) {
			e.printStackTrace();
			assertFalse("Erro", false);
		}
		
	}
	
	@Test
	public void testMissingFile(){
		try {
			warehouseTest.readInputFile("../doesntExist.csv");
			assertTrue("Erro, não esperado encontrar arquivo", false);
		} catch (IOException e) {
			assertEquals(true, e instanceof FileNotFoundException);
		}
	}
	
	@Test
	public void testReadAllValidInformations(){
		try {
			warehouseTest.readInputFile("../armazem.csv");
		} catch (IOException e) {
			assertFalse("Não esperada falha", true);
			e.printStackTrace();
		}
		assertEquals(3, warehouseTest.totalMerchandise());
	}
	
	@Test
	public void testLoadWarning(){
		try {
			warehouseTest.saveProgram();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			warehouseTest.loadProgram();
		} catch (DataOverlayException e) {
			assertEquals(true, e instanceof DataOverlayException);
		} catch (IOException e) {
			System.err.println("Não Há salvamento anterior do programa");
		}
	}
}