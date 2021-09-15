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


import controlador.consultaAPI.ConsultaTrajeto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

 
@Entity
@Table(name = "trajeto")
public class Trajeto implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trajeto", nullable = false, unique = true)
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

	@Column(name = "Meio_transporte", nullable = false)
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
	
	public Trajeto(long id) {
		setIdTrajeto(id);
	}

	public Trajeto(Long id, Ponto inicio, Ponto chegada, List<Ponto> pontos, MeioDeTransporte transporteUsado, List<UsuarioCadastrado> usuariosCadastrados)
			throws StatusInvalidoException, JsonParseException, org.codehaus.jackson.map.JsonMappingException,
			IOException {
		this.setIdTrajeto(id);
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(pontos);
		this.setUsuariosCadastrados(usuariosCadastrados);
	}

	public Trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws StatusInvalidoException,
			JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(inicio, chegada, transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException {
		this.setInicio(Ponto.informatLocal(inicio));
		this.setChegada(Ponto.informatLocal(chegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, int posicaoInicio, String chegada, int posicaoChegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException {
		this.setInicio(Ponto.informatLocal(inicio, posicaoInicio));
		this.setChegada(Ponto.informatLocal(chegada, posicaoChegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
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

	public List<Ponto> criarLineString(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		 return ConsultaTrajeto.criarLineString(inicio, chegada, transporteUsado);
	}
	
	public List<PontoAvaliado> pegarPontosAvaliadosDoTrageto(){
		List<PontoAvaliado> pontosAvaliados = new ArrayList<PontoAvaliado>();
		List<Ponto> pontos = this.getPontos();
		
		for (int i = 0; i < pontos.size(); i++) {
			if (pontos.get(i).getClass().equals("PontoAvaliado")){
				pontosAvaliados.add((PontoAvaliado) pontos.get(i));
			}
		}
		
		return pontosAvaliados;
	}
	
	public List<Double> pegarMediasDosPontosAvaliadosDoTrageto() {
		List<Double> medias = new ArrayList<Double>();
		List<PontoAvaliado> pontosAvaliados = pegarPontosAvaliadosDoTrageto();
		
		for (int i = 0; i < pontosAvaliados.size(); i++) {
			medias.add(pontosAvaliados.get(i).getMediaDeAvaliacao());
		}
		
		return medias;
	}
	
	public List<Ponto> verificarQuaisPontosExixtemEm(List<Ponto> pontos) {
		List<Ponto> pontosNaoExistentes = new ArrayList<Ponto>();
		
		for (int i = 0; i < this.getPontos().size();i++){
			if (!(pontos.contains(this.getPontos().get(i)))) {
				pontosNaoExistentes.add(this.getPontos().get(i));
			}
		}
		
		return pontosNaoExistentes;
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
