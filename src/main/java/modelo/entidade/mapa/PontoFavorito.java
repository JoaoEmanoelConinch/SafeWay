package modelo.entidade.mapa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@PrimaryKeyJoinColumn(name="id_ponto")
@Table(name = "ponto_favorito")

public class PontoFavorito extends Ponto{

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_ponto_favorito", length = 20, nullable = true )
	private String nomePonto;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(
	        name = "id_ponto_favorito",
	        referencedColumnName = "id_ponto"
	    )
	private Ponto ponto;
 
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(
	        name = "id_usuario",
	        referencedColumnName = "id_usuario"
	    )
	private UsuarioCadastrado usuario;

	public PontoFavorito() {}
	
	public PontoFavorito(long id) {
		super(id);
	}

	public PontoFavorito(Ponto ponto, String nomePonto, UsuarioCadastrado usuario)
	throws StatusInvalidoException
{
		super(ponto.getLatitude(), ponto.getLongitude());
		
		setPonto(ponto);
		setNomePonto(nomePonto);
		setUsuario(usuario);
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

	public static PontoFavorito favoritarPontoENomear(Ponto ponto, String nomePonto, UsuarioCadastrado usuario)
	throws StatusInvalidoException{

		return new PontoFavorito(ponto, nomePonto, usuario);

	}

}
