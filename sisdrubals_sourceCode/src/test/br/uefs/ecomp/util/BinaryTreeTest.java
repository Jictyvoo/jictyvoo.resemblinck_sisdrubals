package br.uefs.ecomp.util;

import java.util.Random;

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
			AvlTree<String> arvoreTeste = new AvlTree<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree insertion";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.add(vectorString[position]);

			for(int position = 0; position < 5; position += 1)
				assertEquals(vectorString[position], arvoreTeste.search(vectorString[position]));
		}
		
		@Test
		public void testRemoveInTree(){
			AvlTree<String> arvoreTeste = new AvlTree<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree remove";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.add(vectorString[position]);
			
			arvoreTeste.remove(vectorString[3]);
			arvoreTeste.remove(vectorString[1]);

			for(int position = 0; position < 5; position += 1){
				if(position == 3 || position == 1)
					assertEquals(null, arvoreTeste.search(vectorString[position]));
				else
					assertEquals(vectorString[position], arvoreTeste.search(vectorString[position]));
			}

		}
		
		@Test
		public void testHeight(){
			AvlTree<String> arvoreTeste = new AvlTree<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree height";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.add(vectorString[position]);
			
			assertEquals(3, arvoreTeste.height());
		}
		
		@Test
		public void testList(){
			AvlTree<Integer> arvoreTeste = new AvlTree<Integer>();
			Integer[] vectorInteger = new Integer[11];
			Random random = new Random();
			for(int position = 0; position < 11; position += 1)
				vectorInteger[0] = random.nextInt(200);

			for(Integer insertThis : vectorInteger)
				arvoreTeste.add(insertThis);
			Iterator<Integer> ordenado = arvoreTeste.list();
			Integer anterior = ordenado.next();
			while(ordenado.hasNext()){
				Integer testNow = ordenado.next();
				assertEquals(true, testNow >= anterior);
				anterior = testNow;
			}
		}
		
		@Test
		public void testSize(){
			AvlTree<String> arvoreTeste = new AvlTree<String>();
			String[] vectorString = new String[5];
			vectorString[0] = "It's time to";
			vectorString[1] = "test the";
			vectorString[2] = "tree size";
			vectorString[3] = "I want";
			vectorString[4] = "that works";
			
			for(int position = 0; position < 5; position += 1)
				arvoreTeste.add(vectorString[position]);
			
			assertEquals(5, arvoreTeste.size());
		}
	
}