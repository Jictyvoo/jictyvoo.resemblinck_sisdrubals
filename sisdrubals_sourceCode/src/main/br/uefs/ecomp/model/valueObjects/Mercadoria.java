package br.uefs.ecomp.model.valueObjects;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author João Victor & Resemblinck
 */
public class Mercadoria {

	/**
	 * 
	 */
	private int lote;

	/**
	 * 
	 */
	private String fornecedor;

	/**
	 * 
	 */
	private LocalDate dataInsercao;

	/**
	 * 
	 */
	private LocalTime horaInsercao;

	/**
	 * Default constructor
	 */
	public Mercadoria(int lote, String fornecedor) {
		this.horaInsercao = LocalTime.now();
		this.dataInsercao = LocalDate.now();
		this.fornecedor = fornecedor;
		this.lote = lote;
	}

	/**
	 * @return
	 */
	public LocalTime getHoraInsercao() {
		return this.horaInsercao;
	}

	/**
	 * @param value
	 */
	public void setHoraInsercao(LocalTime value) {
		this.horaInsercao = value;
	}

	/**
	 * @return
	 */
	public LocalDate getDataInsercao() {
		return this.dataInsercao;
	}

	/**
	 * @param value
	 */
	public void setDataInsercao(LocalDate value) {
		this.dataInsercao = value;
	}
	
	public String getFornecedor(){
		return this.fornecedor;
	}
	
	public int getLote(){
		return this.lote;
	}

}