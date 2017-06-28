package br.uefs.ecomp.util.exception;

public class InputInformationMissing extends Exception {

	/**
	 * importante caso a exce��o seja serializada
	 */
	private static final long serialVersionUID = -6830743704949132310L;
	
	/*
	 * constroi um objeto InputInformationMissing com a mensagem passada por par�metro
	 */
	public InputInformationMissing(){
		super("A linha de entrada contem menos dados que o necessario");
	}

	/*
	 * controi um objeto InputInformationMissing com mensagem e a causa dessa exce��o
	 */
	public InputInformationMissing(Throwable cause){
		super("A linha de entrada contem menos dados que o necessario", cause);
	}
}
