package modelo.entidade.mapa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@PrimaryKeyJoinColumn(name="id_ponto_favorito")
@Table(name = "ponto_favorito")

public class PontoFavorito extends Ponto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto_favorito", nullable = false, unique = true, columnDefinition = "UNSIGNED LONG")
	private Long idPontoFav;

	@Column(name = "nome_ponto_favorito", length = 20, nullable = false )
	private String nomePonto;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ponto")
	private Ponto ponto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioCadastrado usuario;

	public PontoFavorito() {
	}

	public PontoFavorito(Long idPontoFav, Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLongitude());

		setIdPontoFav(idPontoFav);
		setNomePonto(nomePonto);
	}

	public PontoFavorito(Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLongitude());
		setNomePonto(nomePonto);
	}

	public Long getIdPontoFav() {
		return idPontoFav;
	}

	public void setIdPontoFav(Long idPontoFav) {
		this.idPontoFav = idPontoFav;
	}

	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;

	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public UsuarioCadastrado getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioCadastrado usuario) {
		this.usuario = usuario;
	}

	public static PontoFavorito favoritarPontoENomear(Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {

		return new PontoFavorito(ponto, nomePonto);

	}

}
