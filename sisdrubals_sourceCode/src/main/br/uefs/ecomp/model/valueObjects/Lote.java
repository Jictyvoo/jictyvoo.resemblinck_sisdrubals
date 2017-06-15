package br.uefs.ecomp.model.valueObjects;

import br.uefs.ecomp.util.BinaryTree;

/**
 * @author João Victor & Resemblinck
 */
public class Lote {

	/**
	 * Default constructor
	 */
	public Lote() {
	}

	/**
	 * 
	 */
	private BinaryTree mercadorias;

	/**
	 * 
	 */
	private int id;

	/**
	 * 
	 */
	private String localizacao;


	/**
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return
	 */
	public String getLocalizacao() {
		return this.localizacao;
	}

	/**
	 * @param localizacaoRecebida
	 */
	public void setLocalizacao(String localizacaoRecebida) {
		this.localizacao = localizacaoRecebida;
	}

}