package modelo.dao.Ponto;

import java.util.List;

import modelo.entidade.mapa.PontoAbstrato;

public interface PontoDAO {

	void inserirPonto(PontoAbstrato ponto);
	
	void deletarPonto(PontoAbstrato ponto);
	
	List<PontoAbstrato> recuperarPontos();
	
}
