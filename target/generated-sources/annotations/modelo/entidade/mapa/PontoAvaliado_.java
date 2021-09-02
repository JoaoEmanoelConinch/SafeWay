package modelo.entidade.mapa;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PontoAvaliado.class)
public abstract class PontoAvaliado_ extends modelo.entidade.mapa.Ponto_ {

	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeLezoesCorporais;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeRoubos;
	public static volatile SingularAttribute<PontoAvaliado, Ponto> ponto;
	public static volatile SingularAttribute<PontoAvaliado, Long> idPontoAvaliado;
	public static volatile SingularAttribute<PontoAvaliado, ArrayList> avaliacoes;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeFurtos;
	public static volatile SingularAttribute<PontoAvaliado, Double> mediaDeAvaliacao;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeHomicidios;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeLatrocinio;
	public static volatile SingularAttribute<PontoAvaliado, Boolean> bloqueio;

	public static final String QUANTIDADE_LEZOES_CORPORAIS = "quantidadeLezoesCorporais";
	public static final String QUANTIDADE_ROUBOS = "quantidadeRoubos";
	public static final String PONTO = "ponto";
	public static final String ID_PONTO_AVALIADO = "idPontoAvaliado";
	public static final String AVALIACOES = "avaliacoes";
	public static final String QUANTIDADE_FURTOS = "quantidadeFurtos";
	public static final String MEDIA_DE_AVALIACAO = "mediaDeAvaliacao";
	public static final String QUANTIDADE_HOMICIDIOS = "quantidadeHomicidios";
	public static final String QUANTIDADE_LATROCINIO = "quantidadeLatrocinio";
	public static final String BLOQUEIO = "bloqueio";

}

