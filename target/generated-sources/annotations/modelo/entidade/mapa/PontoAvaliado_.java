package modelo.entidade.mapa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.formulario.Formulario;
import modelo.enumeracao.mapa.NivelBloqueio;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PontoAvaliado.class)
public abstract class PontoAvaliado_ extends modelo.entidade.mapa.Ponto_ {

	public static volatile SingularAttribute<PontoAvaliado, Integer> nivelDeEstruturaDaRua;
	public static volatile SingularAttribute<PontoAvaliado, Integer> nivelDeTransito;
	public static volatile SingularAttribute<PontoAvaliado, Ponto> ponto;
	public static volatile SingularAttribute<PontoAvaliado, Long> idPontoAvaliado;
	public static volatile ListAttribute<PontoAvaliado, Formulario> avaliacoes;
	public static volatile SingularAttribute<PontoAvaliado, Integer> mediaDeAvaliacao;
	public static volatile SingularAttribute<PontoAvaliado, Double> nivelDeCriminalidade;
	public static volatile SingularAttribute<PontoAvaliado, Integer> nivelDeIluminacao;
	public static volatile SingularAttribute<PontoAvaliado, NivelBloqueio> bloqueio;

	public static final String NIVEL_DE_ESTRUTURA_DA_RUA = "nivelDeEstruturaDaRua";
	public static final String NIVEL_DE_TRANSITO = "nivelDeTransito";
	public static final String PONTO = "ponto";
	public static final String ID_PONTO_AVALIADO = "idPontoAvaliado";
	public static final String AVALIACOES = "avaliacoes";
	public static final String MEDIA_DE_AVALIACAO = "mediaDeAvaliacao";
	public static final String NIVEL_DE_CRIMINALIDADE = "nivelDeCriminalidade";
	public static final String NIVEL_DE_ILUMINACAO = "nivelDeIluminacao";
	public static final String BLOQUEIO = "bloqueio";

}

