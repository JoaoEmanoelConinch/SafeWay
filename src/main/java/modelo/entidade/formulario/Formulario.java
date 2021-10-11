package modelo.entidade.formulario;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;

@Entity
@Table(name = "Formulario")
public class Formulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_formulario", nullable = false, unique = true)
	private long idFormulario;

	@Column(name = "media_formulario", scale = 2, precision = 1, nullable = false)
	private double media;
	
	@Column(name = "lesao_corporal_formulario", nullable = false)
	private boolean lesaoCorporal;

	@Column(name = "furto_formulario", nullable = false)
	private boolean furto;

	@Column(name = "roubo_formulario", nullable = false)
	private boolean roubo;

	@Column(name = "homicidio_formulario", nullable = false)
	private boolean homicidio;

	@Column(name = "latrocinio_formulario", nullable = false)
	private boolean latrocinio;

	@Column(name = "bloqueio_rua_formulario", nullable = false)
	private boolean bloqueioRua;
	
	@Column(name = "comentario_formulario", length = 300, nullable = true)
	private String comentario;

	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "id_ponto",
				referencedColumnName = "id_ponto",
				nullable = false
			)
	private Ponto idPonto;
	

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @JoinColumn(
		        name = "id_usuario",
		        referencedColumnName = "id_usuario",
		        nullable = true
		    )
	private UsuarioCadastrado usuario;
	
	public Formulario(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio, String comentario, boolean bloqueio, Ponto pontoUsavel) {
		setIdFormulario(idFormulario);
		setLesaoCorporal(lesaoCorporal);
		setFurto(furto);
		setRoubo(roubo);
		setHomicidio(homicidio);
		setLatrocinio(latrocinio);
		setComentario(comentario);
		setBloqueioRuas(bloqueio);
		setMedia(calcularMedia());
		setIdPontoAvaliado(pontoUsavel);
	}
	
	public Formulario(long idFormulario) {
		setIdFormulario(idFormulario);
	}

//	public Formulario(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
//	 String comentario, boolean bloqueioRuas, Ponto ponto, UsuarioCadastrado usuario) {
//
//		setLesaoCorporal(lesaoCorporal);
//		setFurto(furto);
//		setRoubo(roubo);
//		setHomicidio(homicidio);
//		setLatrocinio(latrocinio);
//		setComentario(comentario);
//		setBloqueioRuas(bloqueioRuas);
//		setMedia(calcularMedia());
//		setIdPontoAvaliado(idPontoAvaliado);
//		setIdUsuario(idUsuario);
//
//	}

//	public Formulario(long idFormulario,boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio,
//	boolean latrocinio, boolean bloqueioRuas, double media, String comentario, Ponto idPontoAvaliado,UsuarioCadastrado idUsuario) {
//
//		setIdFormulario(idFormulario);
//		setLesaoCorporal(lesaoCorporal);
//		setFurto(furto);
//		setRoubo(roubo);
//		setHomicidio(homicidio);
//		setLatrocinio(latrocinio);
//		setComentario(comentario);
//		setBloqueioRuas(bloqueioRuas);
//		setMedia(media);
//		setIdPontoAvaliado(idPontoAvaliado);
//		setIdUsuario(idUsuario);
//
//	}
	
	public Formulario(long idFormulario,boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio,
			boolean latrocinio, boolean bloqueioRuas, double media, String comentario, Ponto ponto, UsuarioCadastrado usuario) {

				setIdFormulario(idFormulario);
				setLesaoCorporal(lesaoCorporal);
				setFurto(furto);
				setRoubo(roubo);
				setHomicidio(homicidio);
				setLatrocinio(latrocinio);
				setComentario(comentario);
				setBloqueioRuas(bloqueioRuas);
				setMedia(calcularMedia());
				setIdPontoAvaliado(ponto);
				setIdUsuario(usuario);

			}
	
	public Formulario(long idFormulario,boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio,
			boolean latrocinio, boolean bloqueioRuas, String comentario) {

				setIdFormulario(idFormulario);
				setLesaoCorporal(lesaoCorporal);
				setFurto(furto);
				setRoubo(roubo);
				setHomicidio(homicidio);
				setLatrocinio(latrocinio);
				setComentario(comentario);
				setMedia(calcularMedia());
				setBloqueioRuas(bloqueioRuas);
//				setIdPontoAvaliado(idPontoAvaliado);

			}

	public Formulario(boolean lesaoCorporal, boolean furto, boolean roubo, boolean homicidio, boolean latrocinio,
	 String comentario, boolean bloqueioRuas, Ponto idPontoAvaliado, UsuarioCadastrado idUsuario) {

		setLesaoCorporal(lesaoCorporal);
		setFurto(furto);
		setRoubo(roubo);
		setHomicidio(homicidio);
		setLatrocinio(latrocinio);
		setComentario(comentario);
		setBloqueioRuas(bloqueioRuas);
		setMedia(calcularMedia());
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(long idFormulario) {
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

	public boolean isBloqueioRuas() {
		return bloqueioRua;
	}

	public void setBloqueioRuas(boolean bloqueioRua) {
		this.bloqueioRua = bloqueioRua;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public void setIdPontoAvaliado(Ponto idPontoAvaliado) {
		this.idPonto = idPontoAvaliado;
	}

	public UsuarioCadastrado getIdUsuario() {
		return usuario;
	}

	public void setIdUsuario(UsuarioCadastrado idUsuario) {
		this.usuario = idUsuario;
	}

	private double calcularMedia() {
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

		return media / 5;

	}

}
