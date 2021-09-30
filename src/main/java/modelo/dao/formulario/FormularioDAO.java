package modelo.dao.formulario;

import java.util.List;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;

public interface FormularioDAO {
	
	void inserirAvaliacao(Formulario formulario);
	
	void deletarAvaliacao(Formulario formulario);
	
	void atualizarAvaliacao(Formulario formulario);
	
	List<Formulario> recuperarAvaliacoes(Ponto ponto);
	
	Formulario recuperarAvaliacaoId(Formulario form);

}
