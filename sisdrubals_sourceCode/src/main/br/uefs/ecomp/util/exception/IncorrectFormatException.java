package br.uefs.ecomp.util.exception;

public class IncorrectFormatException extends Exception {

	/**
	 * Necessario para a serializacao
	 */
	private static final long serialVersionUID = -5742240435414446236L;

	/**
	 * Constr�i uma exce��o do tipo IncorrectFormatException indicando qual o campo dos dados inv�lido
	 * @param e - Campo inv�lido
	 */
	public IncorrectFormatException(String e){
		super("O campo " + e + " n�o constitui um argumento v�lido!");
	}
	
	/**
	 * Constr�i uma exce��o do tipo IncorrectFormatException indicando qual a linha do arquivo que possui campos dos dados inv�lido
	 * @param lineNumber - Linha do arquivo onde foi encontrado o erro
	 */
	public IncorrectFormatException(int lineNumber){
		super("Na linha " + lineNumber + " do arquivo de entrada possui dado(s) inv�lido(s)!");
	}
	
	/**
	 * Constr�i uma exce��o do tipo IncorrectFormatException
	 */
	public IncorrectFormatException(){
		super("Argumento v�lido!");
	}

}
