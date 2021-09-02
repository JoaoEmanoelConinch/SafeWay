package modelo.entidade.mapa;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ponto.class)
public abstract class Ponto_ {

	public static volatile SingularAttribute<Ponto, ArrayList> trajetos;
	public static volatile SingularAttribute<Ponto, Long> idPonto;
	public static volatile SingularAttribute<Ponto, Double> latitude;
	public static volatile SingularAttribute<Ponto, Double> longitude;

	public static final String TRAJETOS = "trajetos";
	public static final String ID_PONTO = "idPonto";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

}

