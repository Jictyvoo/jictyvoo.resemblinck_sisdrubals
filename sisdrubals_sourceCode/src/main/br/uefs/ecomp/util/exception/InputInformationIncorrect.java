package br.uefs.ecomp.util.exception;

public class InputInformationIncorrect extends Exception {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = 8741016812744242724L;

	/**
	 * Constr�i uma exce��o do tipo InputInformationIncorrect indicando a linha do arquivo que
	 * cont�m uma quantidade inv�lida de dados
	 * @param lineNumber - Linha do arquivo com erro
	 */
	public InputInformationIncorrect(int lineNumber){
		super("A linha " + lineNumber + " do arquivo de entrada contem quantidade incorreta de dados");
	}
	
	/**
	 * Constr�i uma exce��o do tipo InputInformationIncorrect
	 */
	public InputInformationIncorrect(){
		super("Quantidade incorreta de dados");
	}

}
