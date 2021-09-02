package modelo.entidade.formulario;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.usuario.UsuarioCadastrado;

@Entity
@Table(name = "Formulario")
public class Formulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY  )
	@Column(name = "id_formulario", nullable = false, unique = true, columnDefinition = "UNSIGNED INT")
	private Long idFormulario;

	@Column(name = "media_formulario", scale = 2, precision = 1, nullable = false, unique = false)
	private double media;

	private boolean lesaoCorporal;

	private boolean furto;

	private boolean roubo;

	private boolean homicidio;

	private boolean latrocinio;
	
	@Column(name = "comentario_Formulario", length = 300, nullable = true, unique = true)
	private String comentario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ponto_avaliado", nullable = false, columnDefinition = "UNSIGNED INT")
	private Ponto idPontoAvaliado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", columnDefinition = "enum default NULL" + "UNSIGNED INT")
	private UsuarioCadastrado idUsuario;

	public Formulario() {
	}

	public Formulario(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
	 String comentario, PontoAvaliado idPontoAvaliado,UsuarioCadastrado idUsuario) {

		setLesaoCorporal(lesaoCorporal);
		setFurto(furto);
		setRoubo(roubo);
		setHomicidio(homicidio);
		setLatrocinio(latrocinio);
		setComentario(comentario);
		setMedia(calcularMedia());
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Formulario(Long idFormulario,boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
	String comentario, PontoAvaliado idPontoAvaliado,UsuarioCadastrado idUsuario) {

		setIdFormulario(idFormulario);
		setLesaoCorporal(lesaoCorporal);
		setFurto(furto);
		setRoubo(roubo);
		setHomicidio(homicidio);
		setLatrocinio(latrocinio);
		setComentario(comentario);
		setComentario(comentario);
		setMedia(calcularMedia());
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Formulario(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio, String comentario, Ponto idPontoAvaliado,
			UsuarioCadastrado idUsuario) {

		setLesaoCorporal(lesaoCorporal);
		setFurto(furto);
		setRoubo(roubo);
		setHomicidio(homicidio);
		setLatrocinio(latrocinio);
		setComentario(comentario);
		setComentario(comentario);
		setMedia(calcularMedia());
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(Long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public boolean isLatrocinio() {
		return latrocinio;
	}

	public void setLatrocinio(boolean latrocinio) {
		this.latrocinio = latrocinio;
	}

	public boolean isHomicidio() {
		return homicidio;
	}

	public void setHomicidio(boolean homicidio) {
		this.homicidio = homicidio;
	}

	public boolean isRoubo() {
		return roubo;
	}

	public void setRoubo(boolean roubo) {
		this.roubo = roubo;
	}

	public boolean isFurto() {
		return furto;
	}

	public void setFurto(boolean furto) {
		this.furto = furto;
	}

	public boolean isLesaoCorporal() {
		return lesaoCorporal;
	}

	public void setLesaoCorporal(boolean lesaoCorporal) {
		this.lesaoCorporal = lesaoCorporal;
	}

	public String getComentario() {
		return comentario;
	}

	private void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public Ponto getIdPontoAvaliado() {
		return idPontoAvaliado;
	}

	public void setIdPontoAvaliado(Ponto idPontoAvaliado) {
		this.idPontoAvaliado = idPontoAvaliado;
	}

	public UsuarioCadastrado getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioCadastrado idUsuario) {
		this.idUsuario = idUsuario;
	}

	private int calcularMedia() {
		ArrayList<Boolean> ocorrencias = new ArrayList<>();
		ocorrencias.add(isLesaoCorporal());
		ocorrencias.add(isFurto());
		ocorrencias.add(isRoubo());
		ocorrencias.add(isHomicidio());
		ocorrencias.add(isLatrocinio());

		int media = 0;

		for (int i = 0; i < ocorrencias.size(); i++){
			if (ocorrencias.get(i)){
				media += i+1;
			}
		}

		return media;

	}

}
