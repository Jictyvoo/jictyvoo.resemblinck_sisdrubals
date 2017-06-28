package br.uefs.ecomp.util.exception;

public class InputInformationMissing extends Exception {

	/**
	 * importante caso a exceção seja serializada
	 */
	private static final long serialVersionUID = -6830743704949132310L;
	
	/*
	 * constroi um objeto InputInformationMissing com a mensagem passada por parâmetro
	 */
	public InputInformationMissing(){
		super("A linha de entrada contem menos dados que o necessario");
	}

	/*
	 * controi um objeto InputInformationMissing com mensagem e a causa dessa exceção
	 */
	public InputInformationMissing(Throwable cause){
		super("A linha de entrada contem menos dados que o necessario", cause);
	}
}
