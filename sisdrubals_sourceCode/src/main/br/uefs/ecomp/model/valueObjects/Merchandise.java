package br.uefs.ecomp.model.valueObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Joao Victor & Resemblinck
 */
public class Merchandise implements Serializable, Comparable<Merchandise> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396664994876129622L;

	/**
	 * 
	 */
	private Localization localization;

	/**
	 * 
	 */
	private String provider;

	/**
	 * 
	 */
	private LocalDate insertDate;

	/**
	 * 
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
		// TODO Auto-generated method stub
		return 0;
	}
}