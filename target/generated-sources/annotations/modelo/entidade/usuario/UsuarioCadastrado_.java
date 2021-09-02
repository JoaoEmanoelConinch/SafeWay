package modelo.entidade.usuario;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.mapa.PontoFavorito;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioCadastrado.class)
public abstract class UsuarioCadastrado_ {

	public static volatile SingularAttribute<UsuarioCadastrado, ArrayList> trajetos;
	public static volatile SingularAttribute<UsuarioCadastrado, String> senha;
	public static volatile SingularAttribute<UsuarioCadastrado, Long> idUsuario;
	public static volatile SingularAttribute<UsuarioCadastrado, ArrayList> formulariosDoUsuario;
	public static volatile SingularAttribute<UsuarioCadastrado, String> nome;
	public static volatile ListAttribute<UsuarioCadastrado, PontoFavorito> favoritos;
	public static volatile SingularAttribute<UsuarioCadastrado, String> email;

	public static final String TRAJETOS = "trajetos";
	public static final String SENHA = "senha";
	public static final String ID_USUARIO = "idUsuario";
	public static final String FORMULARIOS_DO_USUARIO = "formulariosDoUsuario";
	public static final String NOME = "nome";
	public static final String FAVORITOS = "favoritos";
	public static final String EMAIL = "email";

}

