package modelo.enumeracao.mapa;

public enum Estrelas {

	MEIA(1),
	UMA(2),
	UMA_E_MEIA(3),
	DUAS(4),
	DUAS_E_MEIA(5),
	TRES(6),
	TRES_E_MEIA(7),
	QUATRO(8),
	QUATRO_E_MEIA(9),
	CINCO(10);
	
	private final double pesoNota;
	
		private Estrelas(double valor) {
		this.pesoNota = valor;
		}
	
		public double getPeso() {
			return pesoNota;
		}
	
}
