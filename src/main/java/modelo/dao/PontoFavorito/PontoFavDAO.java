package modelo.dao.PontoFavorito;

import modelo.entidade.mapa.PontoFavorito;

public interface PontoFavDAO {
	
	void inserirPontoFav(PontoFavorito pontoFav);
	
	void deletarPontoFav(PontoFavorito pontoFav);
	
	void atualizarPontoFav(PontoFavorito pontoFav);

}
