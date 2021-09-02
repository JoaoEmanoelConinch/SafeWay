package modelo.entidade.mapa;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.enumeracao.mapa.MeioDeTransporte;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Trajeto.class)
public abstract class Trajeto_ {

	public static volatile SingularAttribute<Trajeto, ArrayList> pontos;
	public static volatile SingularAttribute<Trajeto, Ponto> chegada;
	public static volatile SingularAttribute<Trajeto, MeioDeTransporte> transporteUsado;
	public static volatile SingularAttribute<Trajeto, Ponto> inicio;
	public static volatile SingularAttribute<Trajeto, Long> idTrajeto;
	public static volatile SingularAttribute<Trajeto, ArrayList> usuariosCadastrados;

	public static final String PONTOS = "pontos";
	public static final String CHEGADA = "chegada";
	public static final String TRANSPORTE_USADO = "transporteUsado";
	public static final String INICIO = "inicio";
	public static final String ID_TRAJETO = "idTrajeto";
	public static final String USUARIOS_CADASTRADOS = "usuariosCadastrados";

}

