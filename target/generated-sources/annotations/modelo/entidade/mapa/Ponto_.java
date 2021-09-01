package modelo.entidade.mapa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.geojson.Point;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ponto.class)
public abstract class Ponto_ {

	public static volatile SingularAttribute<Ponto, Long> idPonto;
	public static volatile SingularAttribute<Ponto, Point> LongLatAlt;

	public static final String ID_PONTO = "idPonto";
	public static final String LONG_LAT_ALT = "LongLatAlt";

}

