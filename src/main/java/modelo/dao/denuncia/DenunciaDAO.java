package modelo.dao.denuncia;

import java.util.List;

import modelo.entidade.Denuncia.Denuncia;
import modelo.entidade.mapa.Ponto;

public interface DenunciaDAO {
	
	void inserirDenuncia(Denuncia denuncia);
	
	void deletarDenuncia(Denuncia denuncia);
	
	void atualizarDenuncia(Denuncia denuncia);
	
	List<Denuncia> recuperarDenuncias(Ponto ponto);
	
	Denuncia recuperarDenuncia(Denuncia denuncia);

}
