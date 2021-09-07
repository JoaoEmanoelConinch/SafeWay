package modelo.entidade.mapa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@PrimaryKeyJoinColumn(name="id_ponto")
@Table(name = "ponto_favorito")

public class PontoFavorito extends Ponto{

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_ponto_favorito", length = 20, nullable = false )
	private String nomePonto;

	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(
	        name = "id_ponto_favorito",
	        referencedColumnName = "id_ponto"
	    )
	private Ponto ponto;
 
	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(
	        name = "id_usuario",
	        referencedColumnName = "id_usuario"
	    )
	private UsuarioCadastrado usuario;

	public PontoFavorito() {
	}

	public PontoFavorito(Ponto ponto, String nomePonto) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		super(ponto.getLatitude(), ponto.getLongitude());
		setNomePonto(nomePonto);
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
