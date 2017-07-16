package br.uefs.ecomp.util;

import java.io.Serializable;

/**
 * Classe para ser utilizada com nó da Árvore AVL
 * @author João Victor Oliveira Couto & Resemblinck Freitas
 **/
public class Node<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 791172966631627645L;
	
	/**
	 * Referência para o filho esquerdo
	 */
	private Node<E> left;
	
	/**
	 * Referência para o filho direito
	 */
	private Node<E> right;
	
	/**
	 * Referência para o pai
	 */
	private Node<E> parent;
	
	/**
	 * Elemento do nó
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
	 * Método que retorna o filho esquerdo
	 * @return left
	 */
	public Node<E> getLeft() {
		return left;
	}

	/**
	 * Método que altera o filho esquerdo
	 * @param left - novo filho esquerdo
	 */
	public void setLeft(Node<E> left) {
		this.left = left;
	}

	/**
	 * Método que retorna o filho direito
	 * @return right
	 */
	public Node<E> getRight() {
		return right;
	}

	/**
	 * Método que altera o filho direito
	 * @param right - novo filho direito
	 * @return Retorna o novo filho direito
	 */
	public Node<E> setRight(Node<E> right) {
		this.right = right;
		return right;
	}

	/**
	 * Método que retorna o pai
	 * @return parent
	 */
	public Node<E> getParent() {
		return parent;
	}

	/**
	 * Método que altera o pai
	 * @param parent - novo pai
	 * @return Retorna o novo pai
	 */
	public Node<E> setParent(Node<E> parent) {
		this.parent = parent;
		return parent;
	}

	/**
	 * Método que retorna o elemento
	 * @return data
	 */
	public E getData() {
		return data;
	}

	/**
	 * Método que altera o elemento
	 * @param data - novo elemento
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Método que retorna o fator de balanceamento
	 * @return int - balancing
	 */
	public int getBalancing() {
		return balancing;
	}

	/**
	 * Método que altera o fator de balanceamento
	 * @param balancing - nova fator
	 */
	public void setBalancing(int balancing) {
		this.balancing = balancing;
	}

}
