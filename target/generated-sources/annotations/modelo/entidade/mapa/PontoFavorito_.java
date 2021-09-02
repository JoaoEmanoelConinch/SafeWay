package modelo.entidade.mapa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.usuario.UsuarioCadastrado;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PontoFavorito.class)
public abstract class PontoFavorito_ extends modelo.entidade.mapa.Ponto_ {

	public static volatile SingularAttribute<PontoFavorito, Ponto> ponto;
	public static volatile SingularAttribute<PontoFavorito, String> nomePonto;
	public static volatile SingularAttribute<PontoFavorito, UsuarioCadastrado> usuario;
	public static volatile SingularAttribute<PontoFavorito, Long> idPontoFav;

	public static final String PONTO = "ponto";
	public static final String NOME_PONTO = "nomePonto";
	public static final String USUARIO = "usuario";
	public static final String ID_PONTO_FAV = "idPontoFav";

}

