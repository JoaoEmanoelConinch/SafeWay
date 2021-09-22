package modelo.dao.PontoFavorito;

import java.util.List;

import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.usuario.UsuarioCadastrado;

public interface PontoFavDAO {
	
	void inserirPontoFav(PontoFavorito pontoFav);
	
	void deletarPontoFav(PontoFavorito pontoFav);
	
	void atualizarPontoFav(PontoFavorito pontoFav);
	
	PontoFavorito recuperarPontoFavId(PontoFavorito ponto);
	
	List<PontoFavorito> recuperarPontoFavoritoUsuario(UsuarioCadastrado usuario);

}
