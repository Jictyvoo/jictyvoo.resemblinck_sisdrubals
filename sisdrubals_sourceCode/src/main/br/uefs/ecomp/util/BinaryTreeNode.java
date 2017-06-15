package br.uefs.ecomp.util;

/**
 * @author João Victor & Resemblinck
 */
public class BinaryTreeNode implements Element {

	/**
	 * Atributo que armazena os dados na folha
	 */
	private Object data;

	/**
	 * No que aponta para o pai da folha, isto eh, aquele que aponta para a folha
	 */
	private BinaryTreeNode parent;

	/**
	 * aponta para uma folha subsequente a esta, que eh identificada pelo nome left
	 */
	private BinaryTreeNode left;

	/**
	 * aponta para uma folha subsequente a esta, que eh identificada pelo nome right
	 */
	private BinaryTreeNode right;

	/**
	 * Default constructor
	 */
	public BinaryTreeNode() {
		this.left = null;
		this.parent = null;
		this.right = null;
	}

	/**
	 * @param parentReceived 
	 * @param dataReceived
	 */
	public BinaryTreeNode(BinaryTreeNode parentReceived, Object dataReceived) {
		this.parent = parentReceived;
		this.data = dataReceived;
		this.left = null;
		this.right = null;
	}

	/**
	 * @return retorna o elemento guardado na folha
	 */
	public Element getData() {
		return (Element) this.data;
	}

	/**
	 * @param dataReceived objeto a ser armazenado na folha
	 */
	public void setData(Object dataReceived) {
		this.data = dataReceived;
	}

	/**
	 * @return retorna o no pai a este
	 */
	public BinaryTreeNode getParent() {
		return this.parent;
	}

	/**
	 * @param parentReceived o novo no pai deste no
	 */
	public void setParent(BinaryTreeNode parentReceived) {
		this.parent = parentReceived;
	}

	/**
	 * @return retorna o no da esquerda
	 */
	public BinaryTreeNode getLeft() {
		return this.left;
	}

	/**
	 * @param leftReceived seta um novo valor para o no da esquerda
	 */
	public void setLeft(BinaryTreeNode leftReceived) {
		this.left = leftReceived;
	}

	/**
	 * @return retorna o valor da direita
	 */
	public BinaryTreeNode getRight() {
		return this.right;
	}

	/**
	 * @param rightReceived seta um novo valor para o no da direita
	 */
	public void setRight(BinaryTreeNode rightReceived) {
		this.right = rightReceived;
	}

}