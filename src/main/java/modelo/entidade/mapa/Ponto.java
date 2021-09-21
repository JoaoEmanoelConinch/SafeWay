package modelo.entidade.mapa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import modelo.consultaAPI.ConsultaPonto;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Ponto")
public class Ponto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto", nullable = false, unique = true)
	private long idPonto;

	@Column(name = "latitude", nullable = false)
	@Type(type = "double")
	private double latitude;

	@Column(name = "longitude", nullable = false)
	@Type(type = "double")
	private double longitude;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ponto_trajeto", joinColumns = @JoinColumn(name = "id_ponto"), inverseJoinColumns = @JoinColumn(name = "id_trageto"))
	@Fetch(FetchMode.JOIN)
	private List<Trajeto> trajetos;

	public Ponto() {}
	
	public Ponto(long id) {
		setId(id);
	}

	public Ponto(long idPonto, double latitude, double longitude, List<Trajeto> trajetos) {
		this.setId(idPonto);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(trajetos);
	}

	public Ponto(double latitude, double longitude) throws StatusInvalidoException {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(new ArrayList<Trajeto>());
	}

	public static Ponto informatLocal(String local) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informatLocal(local);
	}

	public static Ponto informatLocal(String local, int posicao)
			throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informatLocal(local, posicao);
	}

	public static List<Ponto> informatLocais(String local) {
		return ConsultaPonto.informatLocais(local);
	}

	public void setId(Long id) {

		this.idPonto = id;
	}

	public long getId() {
		return idPonto;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		;
	}

	public double getLongitude() {
		return this.longitude;

	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}

	public List<Trajeto> getTrajetos() {
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

	@Override
	public boolean equals(Object objeto) {
		
		if (this == objeto)
			return true;

		if (objeto == null)
			return false;

		if (getClass() != objeto.getClass())
			return false;
		
		Ponto ponto = (Ponto) objeto;
		
		if (!(this.getLatitude() == ponto.getLatitude())||!(this.getLongitude() == ponto.getLongitude())) {
			return false;
		}
		
		return true;
		
	}

	public static Ponto parseUnsignedPonto(java.lang.String s)throws StatusInvalidoException, NumeroMenorQueZeroException{
		return new Ponto(0, 0);
	}

	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void removeTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
}
