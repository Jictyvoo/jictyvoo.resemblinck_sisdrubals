package br.uefs.ecomp.util;

import java.io.Serializable;

public class Node<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 791172966631627645L;
	private Node<E> left;
	private Node<E> right;
	private Node<E> parent;
	private E data;
	private int balancing;

	public Node(E elemento) {
		setLeft(setRight(setParent(null)));
		setBalancing(0);
		setData(elemento);
	}

	public Node<E> getLeft() {
		return left;
	}

	public void setLeft(Node<E> left) {
		this.left = left;
	}

	public Node<E> getRight() {
		return right;
	}

	public Node<E> setRight(Node<E> right) {
		this.right = right;
		return right;
	}

	public Node<E> getParent() {
		return parent;
	}

	public Node<E> setParent(Node<E> parent) {
		this.parent = parent;
		return parent;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public int getBalancing() {
		return balancing;
	}

	public void setBalancing(int balancing) {
		this.balancing = balancing;
	}

}
