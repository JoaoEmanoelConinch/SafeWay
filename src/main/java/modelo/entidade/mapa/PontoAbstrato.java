package modelo.entidade.mapa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import controlador.consultaAPI.ConsultaPonto;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

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

	public static PontoAbstrato informatLocal(String local) throws StatusInvalidoException, NumeroMenorQueZeroException {
		return ConsultaPonto.informatLocal(local);
	}

	public static PontoAbstrato informatLocal(String local, int posicao)
			throws StatusInvalidoException, NumeroMenorQueZeroException {
		return ConsultaPonto.informatLocal(local, posicao);
	}

	public static List<PontoAbstrato> informatLocais(String local) {
		return ConsultaPonto.informatLocais(local);
	}

	public void setId(Long id) {

		this.idPonto = id;
	}

	public long getId() {
		return idPonto;
	}

	public ArrayList<Double> transformarPontoEmVetor() {
		ArrayList<Double> pontoVetro = new ArrayList<Double>(2);
		pontoVetro.add(this.getLongitude());
		pontoVetro.add(this.getLatitude());
		return pontoVetro;
	}

	public String TransformarVetorEmString() {
		return transformarPontoEmVetor().toString();
	}

	

	
}