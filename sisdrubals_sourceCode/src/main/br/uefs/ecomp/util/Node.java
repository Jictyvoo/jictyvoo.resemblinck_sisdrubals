package br.uefs.ecomp.util;

import java.io.Serializable;

/**
 * Classe para ser utilizada com n� da �rvore AVL
 * @author Jo�o Victor Oliveira Couto & Resemblinck Freitas
 **/
public class Node<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 791172966631627645L;
	
	/**
	 * Refer�ncia para o filho esquerdo
	 */
	private Node<E> left;
	
	/**
	 * Refer�ncia para o filho direito
	 */
	private Node<E> right;
	
	/**
	 * Refer�ncia para o pai
	 */
	private Node<E> parent;
	
	/**
	 * Elemento do n�
	 */
	private E data;
	
	/**
	 * Fator de balanceamento
	 */
	private int balancing;

	/**
	 * Construtor que recebe um elemento
	 * @param data
	 */
	public Node(E data) {
		setLeft(setRight(setParent(null))); 
		setBalancing(0);
		setData(data);
	}

	/**
	 * M�todo que retorna o filho esquerdo
	 * @return left
	 */
	public Node<E> getLeft() {
		return left;
	}

	/**
	 * M�todo que altera o filho esquerdo
	 * @param left - novo filho esquerdo
	 */
	public void setLeft(Node<E> left) {
		this.left = left;
	}

	/**
	 * M�todo que retorna o filho direito
	 * @return right
	 */
	public Node<E> getRight() {
		return right;
	}

	/**
	 * M�todo que altera o filho direito
	 * @param right - novo filho direito
	 * @return Retorna o novo filho direito
	 */
	public Node<E> setRight(Node<E> right) {
		this.right = right;
		return right;
	}

	/**
	 * M�todo que retorna o pai
	 * @return parent
	 */
	public Node<E> getParent() {
		return parent;
	}

	/**
	 * M�todo que altera o pai
	 * @param parent - novo pai
	 * @return Retorna o novo pai
	 */
	public Node<E> setParent(Node<E> parent) {
		this.parent = parent;
		return parent;
	}

	/**
	 * M�todo que retorna o elemento
	 * @return data
	 */
	public E getData() {
		return data;
	}

	/**
	 * M�todo que altera o elemento
	 * @param data - novo elemento
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * M�todo que retorna o fator de balanceamento
	 * @return int - balancing
	 */
	public int getBalancing() {
		return balancing;
	}

	/**
	 * M�todo que altera o fator de balanceamento
	 * @param balancing - nova fator
	 */
	public void setBalancing(int balancing) {
		this.balancing = balancing;
	}

}
