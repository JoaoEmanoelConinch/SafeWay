package modelo.entidade.mapa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PontoAbstrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto", nullable = false, unique = true)
	private long idPonto;

	public PontoAbstrato() {}
	
	public PontoAbstrato(long id) {
		setId(id);
	}

	public void setId(Long id) {

		this.idPonto = id;
	}

	public long getId() {
		return idPonto;
	}

}
