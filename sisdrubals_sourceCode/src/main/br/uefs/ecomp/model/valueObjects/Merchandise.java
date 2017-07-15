package br.uefs.ecomp.model.valueObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Joao Victor & Resemblinck
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

	/**
	 * @param receivedLocalization 
	 * @param providerReceived
	 */
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
	 * @return
	 */
	public Localization getLocalization() {
		return this.localization;
	}

	/**
	 * @param value
	 */
	public void setLocalization(Localization value) {
		this.localization = value;
	}

	/**
	 * @return
	 */
	public String getProvider() {
		return this.provider;
	}

	/**
	 * @param value
	 */
	public void setProvider(String value) {
		this.provider = value;
	}

	/**
	 * @return
	 */
	public LocalDate getInsertDate() {
		return this.insertDate;
	}

	/**
	 * @param value
	 */
	public void setInsertDate(LocalDate value) {
		this.insertDate = value;
	}

	/**
	 * @return
	 */
	public LocalTime getInsertHour() {
		return this.insertHour;
	}

	/**
	 * @param value
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