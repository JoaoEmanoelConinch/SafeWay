package modelo.excecao.usuario;

public class CodigoInvalidoException extends Exception {
	public CodigoInvalidoException(String codigo) {
		super(codigo);
	}
}
