package modelo.dao.ponto;

import java.util.List;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAbstrato;

public interface PontoDAO {

	void inserirPonto(Ponto ponto);
	
	void deletarPonto(Ponto ponto);
	
	void atualizarPonto(Ponto ponto);
	
	List<Ponto> recuperarPontos();
	
	Ponto recuperarPonto(Ponto ponto);
	
	Ponto verificarPonto(Ponto ponto);
}
