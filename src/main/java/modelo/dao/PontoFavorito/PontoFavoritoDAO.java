package modelo.dao.PontoFavorito;

import java.util.List;

import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;

public interface PontoFavoritoDAO {
	
	void inserirPontoFavorito(PontoFavorito pontoFavorito);
	
	void deletarPontoFavorito(PontoFavorito pontoFavorito);
	
	void atualizarPontoFavorito(PontoFavorito pontoFavorito);
	
	PontoFavorito recuperarPontoFavoritoId(PontoFavorito pontoFavorito);
	
	List<PontoFavorito> recuperarPontoFavoritoUsuario(UsuarioCadastrado usuario);

}
