package modelo.entidade.mapa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import modelo.consultaAPI.ConsultaTrajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;

@Entity
@Table(name = "trajeto")
public class Trajeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trajeto", nullable = false, unique = true)
	private long idTrajeto;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_partida_trajeto", referencedColumnName = "id_ponto", nullable = false)
	private Ponto inicio;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ponto_trajeto", joinColumns = @JoinColumn(name = "id_trajeto"), inverseJoinColumns = @JoinColumn(name = "id_ponto"))
	private List<Ponto> pontos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_chegada_trajeto", referencedColumnName = "id_ponto", nullable = false)
	private Ponto chegada;

	@Column(name = "Meio_transporte", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeioDeTransporte transporteUsado;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.ALL })
	@JoinTable(name = "usuario_trajeto", joinColumns = @JoinColumn(name = "id_trajeto"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<UsuarioCadastrado> usuariosCadastrados;

	public Trajeto() {
	}

	public Trajeto(long id) {
		setIdTrajeto(id);
	}

	public Trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(long id, Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, JsonMappingException, IOException {
		this.setIdTrajeto(id);
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Long getIdTrajeto() {
		return idTrajeto;
	}

	public void setIdTrajeto(long idTrajeto) {
		this.idTrajeto = idTrajeto;
	}

	public Ponto getInicio() {
		return inicio;
	}

	public void setInicio(Ponto inicio) {

		this.inicio = inicio;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public Ponto getChegada() {
		return chegada;
	}
	
	public void setChegada(Ponto chegada) {
		this.chegada = chegada;
	}

	public MeioDeTransporte getTransporteUsado() {
		return transporteUsado;
	}

	public void setTransporteUsado(MeioDeTransporte transporteUsado) {
		this.transporteUsado = transporteUsado;
	}

	public List<UsuarioCadastrado> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	public void setUsuariosCadastrados(List<UsuarioCadastrado> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
	}

	public List<Ponto> criarLineString() throws JsonParseException, org.codehaus.jackson.map.JsonMappingException,
			IOException {
		List<Ponto> pontos = ConsultaTrajeto.criarLineString(this.inicio, this.chegada, this.transporteUsado);
		
		List<Ponto> todosOsPontosDoTrajeto = new ArrayList<Ponto>();
		
		todosOsPontosDoTrajeto.add(this.getInicio());
		todosOsPontosDoTrajeto.addAll(pontos);
		todosOsPontosDoTrajeto.add(this.getChegada());
		
		return todosOsPontosDoTrajeto;
		
	}

	public void addPonto(Ponto ponto) {
		pontos.add(ponto);
	}

	public void removePonto(Ponto ponto) {
		pontos.remove(ponto);
	}

	public void addUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado) {
		usuariosCadastrados.add(usuarioCadastrado);
	}

	public void remoreUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado) {
		usuariosCadastrados.remove(usuarioCadastrado);
	}

}
