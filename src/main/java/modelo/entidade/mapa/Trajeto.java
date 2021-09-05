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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controlador.consultaAPI.ConsultaTrajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

 
@Entity
@Table(name = "trajeto")
public class Trajeto implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trajeto", nullable = false, unique = true , columnDefinition = "UNSIGNED INT" )
	private Long idTrajeto;


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_partida_trajeto",
        referencedColumnName = "id_ponto")
	private Ponto inicio;

	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "ponto_trajeto",
    joinColumns = @JoinColumn(name = "id_trajeto"),
    inverseJoinColumns = @JoinColumn(name = "id_ponto")
    )
	private List<Ponto> pontos;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_chegada_trajeto",
        referencedColumnName = "id_ponto")
	private Ponto chegada;

	@Column(name = "Meio_transporte")
	@Enumerated(EnumType.STRING)
	private MeioDeTransporte transporteUsado;
	

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	    @JoinTable(name = "usuario_trajeto",
	    joinColumns = @JoinColumn(name = "id_trajeto"),
	    inverseJoinColumns = @JoinColumn(name = "id_usuario")
	    )
	private List<UsuarioCadastrado> usuariosCadastrados;

	public Trajeto() {
	}

	public Trajeto(Long id, Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws StatusInvalidoException, JsonParseException, org.codehaus.jackson.map.JsonMappingException,
			IOException {
		this.setIdTrajeto(id);
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.criarLineString(inicio, chegada, transporteUsado);
	}

	public Trajeto(Long id, String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, JsonMappingException,
			JsonProcessingException, StatusInvalidoException, IOException {
		this(id, Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado);
	}

	public Trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws StatusInvalidoException,
			JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.criarLineString(inicio, chegada, transporteUsado);
		this.setPontos(new ArrayList<Ponto>());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, JsonMappingException,
			JsonProcessingException, StatusInvalidoException, IOException {
		this(Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado);
	}

	public Long getIdTrajeto() {
		return idTrajeto;
	}

	public void setIdTrajeto(Long idTrajeto) {
		this.idTrajeto = idTrajeto;
	}

	public Ponto getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.inicio = Ponto.informatLocal(inicio);
	}

	private void setInicio(Ponto inicio) {
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

	public void setChegada(String chegada)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.chegada = Ponto.informatLocal(chegada);
	}

	private void setChegada(Ponto chegada) {
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

	public void criarLineString(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		ConsultaTrajeto.criarLineString(inicio, chegada, transporteUsado);
	}

	public void addPonto (Ponto ponto){
		pontos.add(ponto);
	}

	public void removePonto (Ponto ponto){
		pontos.remove(ponto);
	}

	public void addUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado){
		usuariosCadastrados.add(usuarioCadastrado);
	}

	public void remoreUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado){
		usuariosCadastrados.remove(usuarioCadastrado);
	}

}
