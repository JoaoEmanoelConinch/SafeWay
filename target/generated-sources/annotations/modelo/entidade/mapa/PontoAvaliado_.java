package modelo.entidade.mapa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.formulario.Formulario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PontoAvaliado.class)
public abstract class PontoAvaliado_ extends modelo.entidade.mapa.Ponto_ {

	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeLesoesCorporais;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeRoubos;
	public static volatile SingularAttribute<PontoAvaliado, Ponto> ponto;
	public static volatile ListAttribute<PontoAvaliado, Formulario> avaliacoes;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeFurtos;
	public static volatile SingularAttribute<PontoAvaliado, Double> mediaDeAvaliacao;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeHomicidios;
	public static volatile SingularAttribute<PontoAvaliado, Long> quantidadeLatrocinio;
	public static volatile SingularAttribute<PontoAvaliado, Boolean> bloqueio;

	public static final String QUANTIDADE_LESOES_CORPORAIS = "quantidadeLesoesCorporais";
	public static final String QUANTIDADE_ROUBOS = "quantidadeRoubos";
	public static final String PONTO = "ponto";
	public static final String AVALIACOES = "avaliacoes";
	public static final String QUANTIDADE_FURTOS = "quantidadeFurtos";
	public static final String MEDIA_DE_AVALIACAO = "mediaDeAvaliacao";
	public static final String QUANTIDADE_HOMICIDIOS = "quantidadeHomicidios";
	public static final String QUANTIDADE_LATROCINIO = "quantidadeLatrocinio";
	public static final String BLOQUEIO = "bloqueio";

}

