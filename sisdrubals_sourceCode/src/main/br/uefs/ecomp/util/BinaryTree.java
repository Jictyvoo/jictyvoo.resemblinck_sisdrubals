package br.uefs.ecomp.util;

import java.io.Serializable;

/**
 * @author Joao Victor & Resemblinck
 */
public class BinaryTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3672508300406100816L;

	/**
	 * Raiz da arvore, no inicial a tudo que ira ser armazenado na arvore
	 */
	private BinaryTreeNode root;

	/**
	 * atributo que armazena o tamanho da arvore, isto eh, a quantidade de folhas contidas na mesma
	 */
	private int sizeVar;

	/**
	 * Default constructor
	 */
	public BinaryTree() {
		this.root = new BinaryTreeNode();
		this.sizeVar = 0;
	}
	
	public void add(Object dataReceived){
		
	}

	/**
	 * @param newRoot novo objeto armazenado na raiz
	 */
	public void addRoot(Object newRoot) {
		if(this.root.getData() == null)
			this.sizeVar += 1;
		this.root.setData(newRoot);
	}

	/**
	 * @return
	 */
	public Element getRoot() {
		return this.root;
	}

	/**
	 * @param elementReceived 
	 * @param dataReceived 
	 */
	public void addLeft(Element elementReceived, Object dataReceived) {
		if(((BinaryTreeNode) elementReceived).getLeft() == null)
			this.sizeVar += 1;
		((BinaryTreeNode) elementReceived).setLeft(new BinaryTreeNode());
		((BinaryTreeNode) elementReceived).getLeft().setData(dataReceived);
	}

	/**
	 * @param elementReceived 
	 * @param dataReceived 
	 * @return
	 */
	public void addRight(Element elementReceived, Object dataReceived) {
		if(((BinaryTreeNode) elementReceived).getRight() == null)
			this.sizeVar += 1;
		((BinaryTreeNode) elementReceived).setRight(new BinaryTreeNode());
		((BinaryTreeNode) elementReceived).getRight().setData(dataReceived);
	}

	/**
	 * @param elementReceived 
	 * @return
	 */
	public Element getLeft(Element elementReceived) {
		return ((BinaryTreeNode) elementReceived).getLeft();
	}

	/**
	 * @param elementReceived 
	 * @return
	 */
	public Element getRight(Element elementReceived) {
		return ((BinaryTreeNode) elementReceived).getRight();
	}

	/**
	 * @param elementReceived 
	 * @param dataReceived 
	 * @return
	 */
	public void set(Element elementReceived, Object dataReceived) {
		((BinaryTreeNode) elementReceived).setData(dataReceived);
	}

	/**
	 * @param elementReceived 
	 * @return
	 */
	public Element remove(Element elementReceived) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public int size() {
		return this.sizeVar;
	}

	/**
	 * @param elementReceived 
	 * @return
	 */
	public int height(Element elementReceived) {
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public Iterator<Element> iterator() {
		// TODO implement here
		return null;
	}

}