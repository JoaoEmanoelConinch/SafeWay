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

import modelo.consultaAPI.ConsultaTrajeto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

 
@Entity
@Table(name = "trajeto")
public class Trajeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trajeto", nullable = false, unique = true)
	private long idTrajeto;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
        name = "id_partida_trajeto",
        referencedColumnName = "id_ponto",
        nullable = false)
	private PontoAbstrato inicio;

	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL })
    @JoinTable(name = "ponto_trajeto",
    joinColumns = @JoinColumn(name = "id_trajeto"),
    inverseJoinColumns = @JoinColumn(name = "id_ponto")
    )
	private List<Ponto> pontos;

	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
        name = "id_chegada_trajeto",
        referencedColumnName = "id_ponto",
        nullable = false)
	private Ponto chegada;

	@Column(name = "Meio_transporte", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeioDeTransporte transporteUsado;
	

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
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

	public Trajeto(long id, Ponto inicio, Ponto chegada, List<Ponto> pontos, MeioDeTransporte transporteUsado, List<UsuarioCadastrado> usuariosCadastrados)
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
		this.setPontos(criarLineString());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		this.setInicio(Ponto.informatLocal(inicio));
		this.setChegada(Ponto.informatLocal(chegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, int posicaoInicio, String chegada, int posicaoChegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		this.setInicio(Ponto.informatLocal(inicio, posicaoInicio));
		this.setChegada(Ponto.informatLocal(chegada, posicaoChegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString());
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}
	
	public Trajeto(long id, Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws JsonParseException, JsonMappingException, IOException {
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

	public PontoAbstrato getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		this.inicio = Ponto.informatLocal(inicio);
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

	public PontoAbstrato getChegada() {
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

	public List<Ponto> criarLineString()
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		 return ConsultaTrajeto.criarLineString(this.inicio, this.chegada, this.transporteUsado);
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

	
	
//	public List<PontoAvaliado> pegarPontosAvaliadosDoTrageto(){
//		List<PontoAvaliado> pontosAvaliados = new ArrayList<PontoAvaliado>();
//		List<Ponto> pontos = this.getPontos();
//		
//		for (int i = 0; i < pontos.size(); i++) {
//			if (pontos.get(i).getClass().equals("PontoAvaliado")){
//				pontosAvaliados.add(pontos.get(i));
//			}
//		}
//		
//		return pontosAvaliados;
//	}
//	
//	public List<Double> pegarMediasDosPontosAvaliadosDoTrageto() {
//		List<Double> medias = new ArrayList<Double>();
//		List<PontoAvaliado> pontosAvaliados = pegarPontosAvaliadosDoTrageto();
//		
//		for (int i = 0; i < pontosAvaliados.size(); i++) {
//			medias.add(pontosAvaliados.get(i).getMediaDeAvaliacao());
//		}
//		
//		return medias;
//	}

	public void addPonto (Ponto ponto){
		pontos.add(ponto);
	}

	public void removePonto (PontoAbstrato ponto){
		pontos.remove(ponto);
	}

	public void addUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado){
		usuariosCadastrados.add(usuarioCadastrado);
	}

	public void remoreUsuarioCadastrado(UsuarioCadastrado usuarioCadastrado){
		usuariosCadastrados.remove(usuarioCadastrado);
	}

}
