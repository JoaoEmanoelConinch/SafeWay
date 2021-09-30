package modelo.entidade.mapa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import modelo.consultaAPI.ConsultaPonto;
import modelo.entidade.formulario.Formulario;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Table(name = "ponto")
public class Ponto {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto", nullable = false, unique = true)
	private long id_ponto;
	 
	@Column(name = "latitude", nullable = false)
	@Type(type = "double")
	private double latitude;

	@Column(name = "longitude", nullable = false)
	@Type(type = "double")
	private double longitude;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ponto_trajeto", joinColumns = @JoinColumn(name = "id_ponto"))
	@Fetch(FetchMode.JOIN)
	private List<Trajeto> trajetos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idPonto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formulario> avaliacoes;
	
	public Ponto(long idPonto, double latitude, double longitude, List<Trajeto> trajetos, List<Formulario> avaliacoes) {
		this.setIdPonto(idPonto);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(trajetos);
		this.setAvaliacoes(avaliacoes);
		
	}
	
	public Ponto(double latitude, double longitude) throws StatusInvalidoException {
		
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setTrajetos(new ArrayList<Trajeto>());
	}

	
	public Ponto(long id) {
		setIdPonto(id);
	}
	
	public Ponto(){}

	public long getIdPonto() {
		return id_ponto;
	}
	
	public void setIdPonto(long idPonto) {
		this.id_ponto = idPonto;
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
	
	public List<Formulario> getAvaliacoes(){
		return avaliacoes;
	}
	
	public void setAvaliacoes(List<Formulario> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public static Ponto informarLocal(String local) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informarLocal(local);
	}

	public static Ponto informarLocal(String local, int posicao)
			throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return ConsultaPonto.informarLocal(local, posicao);
	}

	public static List<Ponto> informarLocais(String local) {
		return ConsultaPonto.informarLocais(local);
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


	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void removeTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
	
	
	public void addAvaliacao(Formulario form) {
		avaliacoes.add(form);
	}

	public void removeAvaliacao(Formulario form) {
		avaliacoes.remove(form);
	}
  
  public List<Double> criarVetor(){
		List<Double> pontoVetor = new ArrayList<Double>(2);
		
		pontoVetor.add(this.getLongitude());
		pontoVetor.add(this.getLatitude());
		
		return pontoVetor;
	}

	public String informarLatLong() {
		return ConsultaPonto.informarLatLong(this);
	
}
