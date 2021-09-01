package modelo.entidade.mapa;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.geojson.Point;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.consultaAPI.ConsultaPonto;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Ponto")
public class Ponto implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto",nullable = false,unique = true, columnDefinition = "UNSIGNED INT")
	private Long idPonto; 
	
	@Column(columnDefinition = "long_lat_ponto" , nullable = false)
	@Type(type = "org.hibernate.spatial.GeometryType")
	private Point LongLatAlt;

	
	public Ponto() {}

	public Ponto(double latitude, double longitude)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.setLatitude(latitude);
		this.setLongitude(longitude);

	}

	public static Ponto informatLocal(String local)
			throws JsonMappingException, JsonProcessingException, StatusInvalidoException {
		return ConsultaPonto.informatLocal(local);
	}

	public void setId(Long id) {
		this.idPonto = id;
	}

	public Long getId() {
		return idPonto;
	}

	public Point getLongLatAlt() {
		return LongLatAlt;
	}

	public void setLongLatAlt(Point ponto) {
		this.LongLatAlt = ponto;
	}


	private void setLatitude(double latitude) {
		this.getLongLatAlt().getCoordinates().setLatitude(latitude);
	}

	public double getLatitude() {
		return getLongLatAlt().getCoordinates().getLatitude();
	}

	private void setLongitude(double longitude) {
		this.getLongLatAlt().getCoordinates().setLongitude(longitude);
		;
	}

	public double getLongitude() {
		return getLongLatAlt().getCoordinates().getLongitude();
	}

	public ArrayList<Double> transformarPontoEmVetor() {
		ArrayList<Double> pontoVetro = new ArrayList<Double>(2);
		pontoVetro.add(this.getLongitude());
		pontoVetro.add(this.getLatitude());
		return pontoVetro;
	}


	public String TransformarVetorEmString() {
		return transformarPontoEmVetor().toString();
	}

}
