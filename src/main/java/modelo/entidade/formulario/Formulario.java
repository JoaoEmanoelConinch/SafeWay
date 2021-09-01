package modelo.entidade.formulario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import modelo.enumeracao.mapa.Estrelas;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.enumeracao.mapa.Ocorrencia;

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

	@Column(name = "ocorrencia_Formulario", nullable = false, columnDefinition = "enum default NENHUMA")
	@Enumerated(EnumType.STRING)
	private Ocorrencia ocorrencia;

	@Column(name = "nivel_estrutura_Formulario", nullable = false, columnDefinition = "enum default ZERO" )
	@Enumerated(EnumType.STRING)
	private Estrelas nivelEstrutura;

	@Column(name = "nivel_Iluminacao_Formulario", nullable = false, columnDefinition = "enum default ZERO")
	@Enumerated(EnumType.STRING)
	private Estrelas nivelIluminacao;

	@Column(name = "bloqueio_Ruas_Formulario", nullable = false, columnDefinition = "enum default LIVRE")
	@Enumerated(EnumType.STRING)
	private NivelBloqueio bloqueioRuas;

	@Column(name = "nivel_Transito_Formulario", nullable = false, columnDefinition = "enum default ZERO")
	@Enumerated(EnumType.STRING)
	private Estrelas NivelTransito;

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

	public Formulario(Ocorrencia ocorrencia, Estrelas nivelEstrutura, Estrelas nivelIluminacao,
			NivelBloqueio bloqueioRuas, Estrelas NivelTransito, String comentario, PontoAvaliado idPontoAvaliado,
			UsuarioCadastrado idUsuario) {

		setOcorrencia(ocorrencia);
		setNivelEstrutura(nivelEstrutura);
		setNivelIluminacao(nivelIluminacao);
		setNivelBloqueio(bloqueioRuas);
		setNivelTransito(NivelTransito);
		setComentario(comentario);
		setMedia();
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Formulario(Long idFormulario, Ocorrencia ocorrencia, Estrelas nivelEstrutura, Estrelas nivelIluminacao,
			NivelBloqueio bloqueioRuas, Estrelas NivelTransito, String comentario, PontoAvaliado idPontoAvaliado,
			UsuarioCadastrado idUsuario) {

		setIdFormulario(idFormulario);
		setOcorrencia(ocorrencia);
		setNivelEstrutura(nivelEstrutura);
		setNivelIluminacao(nivelIluminacao);
		setNivelBloqueio(bloqueioRuas);
		setNivelTransito(NivelTransito);
		setComentario(comentario);
		setMedia();
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Formulario(Ocorrencia ocorrencias, Estrelas nivelEstrutura, Estrelas nivelIluminacao,
			NivelBloqueio bloqueioRuas, Estrelas NivelTransito, String comentario, Ponto idPontoAvaliado,
			UsuarioCadastrado idUsuario) {

		setOcorrencia(ocorrencias);
		setNivelEstrutura(nivelEstrutura);
		setNivelIluminacao(nivelIluminacao);
		setNivelBloqueio(bloqueioRuas);
		setNivelTransito(NivelTransito);
		setComentario(comentario);
		setMedia();
		setIdPontoAvaliado(idPontoAvaliado);
		setIdUsuario(idUsuario);

	}

	public Long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(Long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Estrelas getNivelEstrutura() {
		return nivelEstrutura;
	}

	private void setNivelEstrutura(Estrelas nivelEstrutura) {
		this.nivelEstrutura = nivelEstrutura;
	}

	public Estrelas getNivelIluminacao() {
		return nivelIluminacao;
	}

	private void setNivelIluminacao(Estrelas nivelIluminacao) {
		this.nivelIluminacao = nivelIluminacao;
	}

	public NivelBloqueio getNivelBloqueio() {
		return bloqueioRuas;
	}

	private void setNivelBloqueio(NivelBloqueio bloqueioRuas) {
		this.bloqueioRuas = bloqueioRuas;
	}

	public Estrelas getNivelTransito() {
		return NivelTransito;
	}

	private void setNivelTransito(Estrelas nivelTransito) {
		this.NivelTransito = nivelTransito;
	}

	public String getComentario() {
		return comentario;
	}

	private void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	private void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public double getMedia() {
		return media;
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

	private void setMedia() {
		this.media = (((getNivelEstrutura().getPeso() + getNivelTransito().getPeso() + getNivelIluminacao().getPeso())
				/ 3) - getOcorrencia().getPeso());
	}

}
