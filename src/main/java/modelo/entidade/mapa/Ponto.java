package modelo.entidade.mapa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import modelo.excecao.mapa.StatusInvalidoException;

public class Ponto extends PontoAbstrato {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ponto_trajeto", joinColumns = @JoinColumn(name = "id_ponto"))
	@Fetch(FetchMode.JOIN)
	private List<Trajeto> trajetos;
	
	public Ponto(long idPonto, double latitude, double longitude, List<Trajeto> trajetos) {
		super(idPonto, latitude, longitude, trajetos);
		this.setTrajetos(trajetos);
	}
	
	public Ponto(double latitude, double longitude) throws StatusInvalidoException {
		super(latitude, longitude);
		this.setTrajetos(new ArrayList<Trajeto>());
	}
	
	public Ponto(long id) {
		super(id);
	}
	
	public Ponto(){
		super();
	}

	public void setTrajetos(List<Trajeto> trajetos) {
		this.trajetos = trajetos;
	}

	public List<Trajeto> getTrajetos() {
		return trajetos;

	}
	
	public void addTrajeto(Trajeto trajeto) {
		trajetos.add(trajeto);
	}

	public void removeTrajeto(Trajeto trajeto) {
		trajetos.remove(trajeto);
	}
	
}
