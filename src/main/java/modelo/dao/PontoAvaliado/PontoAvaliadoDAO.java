package modelo.dao.PontoAvaliado;

import java.util.List;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;

public interface PontoAvaliadoDAO {
	
	void adicionarPontoAvaliado(PontoAvaliado pontoAvaliado);
	
	void deletarPontoAvaliado(PontoAvaliado pontoAvaliado);
	
	void atualizarPontoAvaliado(PontoAvaliado pontoAvaliado);
	
	List<PontoAvaliado> recuperarPontosAvaliados();
	
	List<PontoAvaliado> recuperarPontoAvaliadoMediaIgual(PontoAvaliado ponto);
	
	PontoAvaliado recuperarPontoAvaId(PontoAvaliado ponto);
	
	boolean verificarPontoAvaliado(PontoAvaliado p);

}
