package br.uefs.ecomp.util.exception;

public class AlienCaractere extends Exception {

	/**
	 * importante caso a exce��o seja serializada
	 */
	private static final long serialVersionUID = 8505418310526661812L;

	/*
	 * constroi um objeto InputInformationMissing com a mensagem passada por par�metro
	 */
	public AlienCaractere(String alienCaractere){
		super("Foi encontrado o seguinte caractere Alienigena:" + alienCaractere);
	}

	/*
	 * controi um objeto InputInformationMissing com mensagem e a causa dessa exce��o
	 */
	public AlienCaractere(String alienCaractere, Throwable cause){
		super("Foi encontrado o seguinte caractere Alienigena:" + alienCaractere, cause);
	}
}
