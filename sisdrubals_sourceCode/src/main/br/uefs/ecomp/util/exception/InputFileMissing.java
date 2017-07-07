package br.uefs.ecomp.util.exception;

public class InputFileMissing extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2293208436196407260L;
	
	/*
	 * constroi um objeto InputInformationMissing com a mensagem passada por parâmetro
	 */
	public InputFileMissing(String fileNotFound){
		super("O arquivo " + fileNotFound + " não foi encontrado");
	}
}
