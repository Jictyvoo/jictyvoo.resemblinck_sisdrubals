package br.uefs.ecomp.model.valueObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Joao Victor & Resemblinck Freitas
 */
public class Merchandise implements Serializable, Comparable<Merchandise> {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 396664994876129622L;

	/**
	 * Localizacao id chave para a identificacao da mercadoria
	 */
	private Localization localization;

	/**
	 * o fornecedor daquela mercadoria
	 */
	private String provider;

	/**
	 * data de insercao da mercadoria no armazem
	 */
	private LocalDate insertDate;

	/**
	 * hora de insercao da mercadoria no armazem
	 */
	private LocalTime insertHour;

	public Merchandise(Localization receivedLocalization, String providerReceived, LocalDate dateReceived, LocalTime timeReceived) {
		this.localization = receivedLocalization;
		this.provider = providerReceived;
		this.insertDate = dateReceived;
		this.insertHour = timeReceived;
	}
	
	public Merchandise(Localization receivedLocalization) {
		this.localization = receivedLocalization;
	}

	/**
	 * Método que retorna a localização da mercadoria.
	 * @return localization
	 */
	public Localization getLocalization() {
		return this.localization;
	}

	/**
	 * Método que altera a localização.
	 * @param value - Nova localização
	 */
	public void setLocalization(Localization value) {
		this.localization = value;
	}

	/**
	 * Método que retorna o fornecedor da mercadoria.
	 * @return provider
	 */
	public String getProvider() {
		return this.provider;
	}

	/**
	 * Método que altera o fornecedor da mercadoria.
	 * @param value - novo fornecedor
	 */
	public void setProvider(String value) {
		this.provider = value;
	}

	/**
	 * Método que retorna a data de inserção da mercadoria.
	 * @return insertDate
	 */
	public LocalDate getInsertDate() {
		return this.insertDate;
	}

	/**
	 * Método que altera a data de inserção da mercadoria.
	 * @param value - nova data
	 */
	public void setInsertDate(LocalDate value) {
		this.insertDate = value;
	}

	/**
	 * Método que retorna a hora de inserção da mercadoria.
	 * @return insertHour
	 */
	public LocalTime getInsertHour() {
		return this.insertHour;
	}

	/**
	 * Método que altera a hora de inserção da mercadoria.
	 * @param value - nova hora de inserção
	 */
	public void setInsertHour(LocalTime value) {
		this.insertHour = value;
	}

	@Override
	public String toString() {
		return localization + ";" + provider + ";" + insertDate + ";" + insertHour;
	}

	@Override
	public int compareTo(Merchandise arg0) {
		return this.localization.compareTo(arg0.getLocalization());
	}
}