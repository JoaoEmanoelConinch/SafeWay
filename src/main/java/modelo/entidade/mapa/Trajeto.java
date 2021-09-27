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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	private List<PontoAbstrato> pontos;

	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(
        name = "id_chegada_trajeto",
        referencedColumnName = "id_ponto",
        nullable = false)
	private PontoAbstrato chegada;

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

	public Trajeto(Long id, PontoAbstrato inicio, PontoAbstrato chegada, List<PontoAbstrato> pontos, MeioDeTransporte transporteUsado, List<UsuarioCadastrado> usuariosCadastrados)
			throws StatusInvalidoException, JsonParseException, org.codehaus.jackson.map.JsonMappingException,
			IOException {
		this.setIdTrajeto(id);
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(pontos);
		this.setUsuariosCadastrados(usuariosCadastrados);
	}

	public Trajeto(PontoAbstrato inicio, PontoAbstrato chegada, MeioDeTransporte transporteUsado) throws StatusInvalidoException,
			JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(inicio, chegada, transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException {
		this.setInicio(PontoAbstrato.informatLocal(inicio));
		this.setChegada(PontoAbstrato.informatLocal(chegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(PontoAbstrato.informatLocal(inicio), PontoAbstrato.informatLocal(chegada), transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Trajeto(String inicio, int posicaoInicio, String chegada, int posicaoChegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException {
		this.setInicio(PontoAbstrato.informatLocal(inicio, posicaoInicio));
		this.setChegada(PontoAbstrato.informatLocal(chegada, posicaoChegada));
		this.setTransporteUsado(transporteUsado);
		this.setPontos(criarLineString(PontoAbstrato.informatLocal(inicio), PontoAbstrato.informatLocal(chegada), transporteUsado));
		this.setUsuariosCadastrados(new ArrayList<UsuarioCadastrado>());
	}

	public Long getIdTrajeto() {
		return idTrajeto;
	}

	public void setIdTrajeto(Long idTrajeto) {
		this.idTrajeto = idTrajeto;
	}

	public PontoAbstrato getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) throws StatusInvalidoException, NumeroMenorQueZeroException{
		this.inicio = PontoAbstrato.informatLocal(inicio);
	}

	private void setInicio(PontoAbstrato inicio) {
		this.inicio = inicio;
	}

	public List<PontoAbstrato> getPontos() {
		return pontos;
	}

	public void setPontos(List<PontoAbstrato> pontos) {
		this.pontos = pontos;
	}

	public PontoAbstrato getChegada() {
		return chegada;
	}

	public void setChegada(String chegada) throws StatusInvalidoException, NumeroMenorQueZeroException{
		this.chegada = PontoAbstrato.informatLocal(chegada);
	}

	private void setChegada(PontoAbstrato chegada) {
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

	public List<PontoAbstrato> criarLineString(PontoAbstrato inicio, PontoAbstrato chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		 return ConsultaTrajeto.criarLineString(inicio, chegada, transporteUsado);
	}
	
	
	
	public List<PontoAvaliado> pegarPontosAvaliadosDoTrageto(){
		List<PontoAvaliado> pontosAvaliados = new ArrayList<PontoAvaliado>();
		List<PontoAbstrato> pontos = this.getPontos();
		
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

	public void addPonto (PontoAbstrato ponto){
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
