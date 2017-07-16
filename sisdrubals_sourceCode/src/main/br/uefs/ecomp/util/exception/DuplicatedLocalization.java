package br.uefs.ecomp.util.exception;

public class DuplicatedLocalization extends Exception {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 6947785349865713973L;

	/**
	 * Constrói uma exceção do tipo DuplicatedLocalization indicando a linha do arquivo onde ocorreu o erro
	 * @param lineNumber - Linha do arquivo na qual o erro foi encontrado
	 */
	public DuplicatedLocalization(int lineNumber){
		super("O item da linha " + lineNumber + " ja existe");
	}
	
	/**
	 * Constrói uma exceção do tipo DuplicatedLocalization.
	 */
	public DuplicatedLocalization(){
		super("O item inserido ja existe");
	}
	
}
