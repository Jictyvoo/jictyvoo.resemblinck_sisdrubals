package br.uefs.ecomp.util;

import java.io.Serializable;

public class No<E extends Comparable<E>> implements Serializable {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 791172966631627645L;
	private No<E> esquerda;
	private No<E> direita;
	private No<E> pai;
	private E elemento;
	private int balanceamento;

	public No(E elemento) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setElemento(elemento);
	}

	public No<E> getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No<E> esquerda) {
		this.esquerda = esquerda;
	}

	public No<E> getDireita() {
		return direita;
	}

	public No<E> setDireita(No<E> direita) {
		this.direita = direita;
		return direita;
	}

	public No<E> getPai() {
		return pai;
	}

	public No<E> setPai(No<E> pai) {
		this.pai = pai;
		return pai;
	}

	public E getElemento() {
		return elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

}
