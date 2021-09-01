package modelo.excecao.usuario;

public class SenhaPequenaException extends Exception{

	public SenhaPequenaException (String senha){
		super(senha);
	}
	
}