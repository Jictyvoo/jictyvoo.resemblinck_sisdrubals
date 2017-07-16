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
	 * M�todo que retorna a localiza��o da mercadoria.
	 * @return localization
	 */
	public Localization getLocalization() {
		return this.localization;
	}

	/**
	 * M�todo que altera a localiza��o.
	 * @param value - Nova localiza��o
	 */
	public void setLocalization(Localization value) {
		this.localization = value;
	}

	/**
	 * M�todo que retorna o fornecedor da mercadoria.
	 * @return provider
	 */
	public String getProvider() {
		return this.provider;
	}

	/**
	 * M�todo que altera o fornecedor da mercadoria.
	 * @param value - novo fornecedor
	 */
	public void setProvider(String value) {
		this.provider = value;
	}

	/**
	 * M�todo que retorna a data de inser��o da mercadoria.
	 * @return insertDate
	 */
	public LocalDate getInsertDate() {
		return this.insertDate;
	}

	/**
	 * M�todo que altera a data de inser��o da mercadoria.
	 * @param value - nova data
	 */
	public void setInsertDate(LocalDate value) {
		this.insertDate = value;
	}

	/**
	 * M�todo que retorna a hora de inser��o da mercadoria.
	 * @return insertHour
	 */
	public LocalTime getInsertHour() {
		return this.insertHour;
	}

	/**
	 * M�todo que altera a hora de inser��o da mercadoria.
	 * @param value - nova hora de inser��o
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