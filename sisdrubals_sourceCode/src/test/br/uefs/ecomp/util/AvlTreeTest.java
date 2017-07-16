package br.uefs.ecomp.util;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.util.exception.DuplicatedLocalization;
import br.uefs.ecomp.util.AvlTree;
import br.uefs.ecomp.util.Iterator;
import junit.framework.TestCase;

/**
 * @author João Victor & Resemblinck
 */
public class AvlTreeTest extends TestCase {

		@Before
		public void setUp(){}
		
		@Test
		public void testAddInTree() throws DuplicatedLocalization{
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
		public void testRemoveInTree() throws DuplicatedLocalization{
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
		public void testHeight() throws DuplicatedLocalization{
			AvlTree<Integer> arvoreTeste = new AvlTree<Integer>();
			
			for(int i = 0; i < 16; i ++)
				arvoreTeste.add(i);
			
			assertEquals(4, arvoreTeste.height());
		}
		
		@Test (expected = DuplicatedLocalization.class)
		public void testList() throws DuplicatedLocalization{
			AvlTree<Integer> arvoreTeste = new AvlTree<Integer>();
			arvoreTeste.add(4);
			arvoreTeste.add(2);
			arvoreTeste.add(0);
			arvoreTeste.add(3);
			arvoreTeste.add(1);
			Iterator<Integer> itr = arvoreTeste.list();
			for(Integer i = 0; i < 5; i++){
				assertEquals(i, itr.next());
			}
		}
		
		@Test
		public void testSize() throws DuplicatedLocalization{
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