package br.uefs.ecomp.model.valueObjects;

import java.io.Serializable;

/**
 * @author João Victor & Resemblinck
 */
public class Localization implements Serializable, Comparable<Localization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2083354264825032920L;

	/**
	 * Identificacao do Lote
	 */
	private int lotId;

	/**
	 * O endereco da mercadoria no armazem
	 */
	private String address;

	/**
	 * O bloco em que se encontra a mercadoria
	 */
	private String block;

	/**
	 * Numero da mercadoria dentro do bloco
	 */
	private int merchandiseNumber;


	public Localization(int lotIdReceived, String addressReceived, String blockReceived, int numberReceived) {
		super();
		this.lotId = lotIdReceived;
		this.address = addressReceived;
		this.block = blockReceived;
		this.merchandiseNumber = numberReceived;
	}

	/**
	 * M�todo que recupera o Id do Lote.
	 * @return lotId
	 */
	public int getLotId() {
		return this.lotId;
	}

	/**
	 * M�todo que altera o Id do Lote.
	 * @param value - Novo id do lote.
	 */
	public void setLotId(int value) {
		this.lotId = value;
	}

	/**
	 * M�todo que retorna o endere�o.
	 * @return adress
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * M�todo que altera o endere�o.
	 * @param value - novo endere�o.
	 */
	public void setAddress(String value) {
		this.address = value;
	}

	/**
	 * M�todo que retorna o Bloco.
	 * @return block
	 */
	public String getBlock() {
		return this.block;
	}

	/**
	 * M�todo que altera o bloco.
	 * @param value - novo bloco.
	 */
	public void setBlock(String value) {
		this.block = value;
	}

	/**
	 * M�todo que retorna o numero da mercadoria.
	 * @return merchandiseNumber
	 */
	public int getMerchandiseNumber() {
		return this.merchandiseNumber;
	}

	/**
	 * M�todo que altera o numero da mercadoria.
	 * @param value - novo numero.
	 */
	public void setMerchandiseNumber(int value) {
		this.merchandiseNumber = value;
	}

	@Override
	public String toString() {
		return lotId + ";" + address + ";" + block + ";" + merchandiseNumber;
	}

	@Override
	public int compareTo(Localization o) {
		if(this.lotId == o.getLotId()){
			if(this.address.compareTo(o.getAddress()) == 0){
				if(this.block.compareTo(o.getBlock()) == 0){
					if(this.merchandiseNumber == o.getMerchandiseNumber())
						return 0;
					
					else if(this.merchandiseNumber < o.getMerchandiseNumber())
						return -1;
					else
						return 1;
				}
				else if(this.merchandiseNumber < o.getMerchandiseNumber())
					return -1;
				else
					return 1;
			}
			else if(this.address.compareTo(o.getAddress()) <= -1)
				return -1;
			else
				return 1;
		}
		else if(this.lotId < o.getLotId())
			return -1;
		return 1;
	}
}