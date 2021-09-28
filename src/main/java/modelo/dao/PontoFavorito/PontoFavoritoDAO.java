package modelo.dao.PontoFavorito;

import java.util.List;

import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;

public interface PontoFavoritoDAO {
	
	void inserirPontoFav(PontoFavorito pontoFavorito);
	
	void deletarPontoFav(PontoFavorito pontoFavorito);
	
	void atualizarPontoFav(PontoFavorito pontoFavorito);
	
	PontoFavorito recuperarPontoFavId(PontoFavorito pontoFavorito);
	
	List<PontoFavorito> recuperarPontoFavoritoUsuario(UsuarioCadastrado usuario);

}
