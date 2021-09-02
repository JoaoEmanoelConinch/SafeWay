package modelo.enumeracao.mapa;

public enum Ocorrencia {
	
	LESAO_CORPORAL(0.2),
	FURTO(0.4),
	ROUBO(0.6),
	HOMICIDIO(0.8),
	LATROCINIO(1.0),
	NENHUMA(0.0);
	
	private final double pesoOcorrencia;
	
	Ocorrencia(double valor) {
	pesoOcorrencia = valor;
	}

	public double getPeso() {
		return pesoOcorrencia;
	}
}
