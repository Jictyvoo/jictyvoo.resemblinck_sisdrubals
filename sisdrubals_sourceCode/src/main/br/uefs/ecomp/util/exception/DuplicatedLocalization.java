package br.uefs.ecomp.util.exception;

import br.uefs.ecomp.model.valueObjects.Localization;

public class DuplicatedLocalization extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6947785349865713973L;

	public DuplicatedLocalization(Localization duplicated){
		super("O item " + duplicated + " inserido ja existe");
	}
}
