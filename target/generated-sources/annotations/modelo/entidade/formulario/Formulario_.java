package modelo.entidade.formulario;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.Estrelas;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.enumeracao.mapa.Ocorrencia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Formulario.class)
public abstract class Formulario_ {

	public static volatile SingularAttribute<Formulario, Estrelas> NivelTransito;
	public static volatile SingularAttribute<Formulario, Ocorrencia> ocorrencia;
	public static volatile SingularAttribute<Formulario, Ponto> idPontoAvaliado;
	public static volatile SingularAttribute<Formulario, UsuarioCadastrado> idUsuario;
	public static volatile SingularAttribute<Formulario, Estrelas> nivelEstrutura;
	public static volatile SingularAttribute<Formulario, Long> idFormulario;
	public static volatile SingularAttribute<Formulario, Double> media;
	public static volatile SingularAttribute<Formulario, String> comentario;
	public static volatile SingularAttribute<Formulario, Estrelas> nivelIluminacao;
	public static volatile SingularAttribute<Formulario, NivelBloqueio> bloqueioRuas;

	public static final String NIVEL_TRANSITO = "NivelTransito";
	public static final String OCORRENCIA = "ocorrencia";
	public static final String ID_PONTO_AVALIADO = "idPontoAvaliado";
	public static final String ID_USUARIO = "idUsuario";
	public static final String NIVEL_ESTRUTURA = "nivelEstrutura";
	public static final String ID_FORMULARIO = "idFormulario";
	public static final String MEDIA = "media";
	public static final String COMENTARIO = "comentario";
	public static final String NIVEL_ILUMINACAO = "nivelIluminacao";
	public static final String BLOQUEIO_RUAS = "bloqueioRuas";

}

