package br.uefs.ecomp.util.exception;

public class InputInformationBigger extends Exception {

	/**
	 * importante caso a exceção seja serializada
	 */
	private static final long serialVersionUID = 8741016812744242724L;

	/*
	 * constroi um objeto InputInformationMissing com a mensagem passada por parâmetro
	 */
	public InputInformationBigger(){
		super("A linha de entrada contem mais dados que o necessario");
	}

	/*
	 * controi um objeto InputInformationMissing com mensagem e a causa dessa exceção
	 */
	public InputInformationBigger(Throwable cause){
		super("A linha de entrada contem mais dados que o necessario", cause);
	}
}
