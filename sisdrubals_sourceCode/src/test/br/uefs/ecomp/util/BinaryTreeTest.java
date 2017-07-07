package br.uefs.ecomp.util;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author João Victor & Resemblinck
 */
public class BinaryTreeTest extends TestCase {

		@Before
		public void setUp(){}
		
		@Test
		public void testAddInTree(){
			ArvoreAvl<String> arvoreTeste = new ArvoreAvl<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree insertion";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.inserir(vectorString[position]);

			for(int position = 0; position < 5; position += 1)
				assertEquals(vectorString[position], arvoreTeste.buscar(vectorString[position]));
		}
		
		@Test
		public void testRemoveInTree(){
			ArvoreAvl<String> arvoreTeste = new ArvoreAvl<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree remove";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.inserir(vectorString[position]);
			
			arvoreTeste.remover(vectorString[3]);
			arvoreTeste.remover(vectorString[1]);

			for(int position = 0; position < 5; position += 1){
				if(position == 3 || position == 1)
					assertEquals(null, arvoreTeste.buscar(vectorString[position]));
				else
					assertEquals(vectorString[position], arvoreTeste.buscar(vectorString[position]));
			}

		}
	
}