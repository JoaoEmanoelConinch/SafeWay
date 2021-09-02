package modelo.dao.PontoAvaliado;

import java.util.List;

import modelo.entidade.mapa.PontoAvaliado;

public interface PontoAvaliadoDAO {
	
	void adicionarPontoAvaliado(PontoAvaliado pontoAvaliado);
	
	void deletarPontoAvaliado(PontoAvaliado pontoAvaliado);
	
	List<PontoAvaliado> recuperarPontosAvaliados();
	
	List<PontoAvaliado> recuperarPontoAvMaiorQue(int nota);
	
	List<PontoAvaliado> recuperarPontoAvMenorQue(int nota);
	
	PontoAvaliado recuperarPontoAvaId(Long id);

}
