package modelo.entidade.mapa;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import controlador.consultaAPI.ConsultaPonto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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

	@Column(name = "latitude" , nullable = false)
	@Type(type = "double")
	private double latitude;

	@Column(name = "longitude", nullable = false)
	@Type(type = "double")
	private double longitude;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "ponto_trageto", joinColumns = @JoinColumn(name = "id_ponto"), inverseJoinColumns = @JoinColumn(name = "id_trageto"))
	private ArrayList<Trajeto> trajetos;

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

	private void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	private void setLongitude(double longitude) {
		this.longitude = longitude;
		;
	}

	public double getLongitude() {
		return this.longitude;

	}

	public void setTrajetos(ArrayList<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}

	public ArrayList<Trajeto> getTrajetos() {
		return trajetos;
    
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

	public void addTrajeto(Trajeto trajeto){
		trajetos.add(trajeto);
	}

	public void removeTrajeto(Trajeto trajeto){
		trajetos.remove(trajeto);
	}
}
