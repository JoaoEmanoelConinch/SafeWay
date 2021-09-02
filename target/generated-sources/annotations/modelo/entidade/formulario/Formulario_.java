package modelo.entidade.formulario;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Formulario.class)
public abstract class Formulario_ {

	public static volatile SingularAttribute<Formulario, Boolean> lesaoCorporal;
	public static volatile SingularAttribute<Formulario, Boolean> latrocinio;
	public static volatile SingularAttribute<Formulario, Ponto> idPontoAvaliado;
	public static volatile SingularAttribute<Formulario, Boolean> homicidio;
	public static volatile SingularAttribute<Formulario, UsuarioCadastrado> idUsuario;
	public static volatile SingularAttribute<Formulario, Long> idFormulario;
	public static volatile SingularAttribute<Formulario, Boolean> furto;
	public static volatile SingularAttribute<Formulario, Double> media;
	public static volatile SingularAttribute<Formulario, String> comentario;
	public static volatile SingularAttribute<Formulario, Boolean> roubo;
	public static volatile SingularAttribute<Formulario, Boolean> bloqueioRuas;

	public static final String LESAO_CORPORAL = "lesaoCorporal";
	public static final String LATROCINIO = "latrocinio";
	public static final String ID_PONTO_AVALIADO = "idPontoAvaliado";
	public static final String HOMICIDIO = "homicidio";
	public static final String ID_USUARIO = "idUsuario";
	public static final String ID_FORMULARIO = "idFormulario";
	public static final String FURTO = "furto";
	public static final String MEDIA = "media";
	public static final String COMENTARIO = "comentario";
	public static final String ROUBO = "roubo";
	public static final String BLOQUEIO_RUAS = "bloqueioRuas";

}

