package modelo.dao.Formulario;

import java.util.List;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoFavorito;

public interface FormularioDAO {
	
	void inserirAvaliacao(Formulario formulario);
	
	void deletarAvaliacao(Formulario formulario);
	
	void atualizarAvaliacao(Formulario formulario);
	
	List<Formulario> recuperarAvaliacoes(PontoAvaliado ponto);

}
