package modelo.entidade.usuario;

import java.io.IOException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private long id;	
	
	public Usuario(){}
	
	public Usuario(long id) {
		setId(id);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public static List<Ponto> informatLocais(String local){
		return Ponto.informatLocais(local);
	} 
	
	public String DefinirPartida(String inicio) {
		return inicio;
	}

	public String DefinirDestino(String chegada) {
		return chegada;
	}

	public MeioDeTransporte DefinirTransporte(MeioDeTransporte transporte) {
		return transporte;
	}

	public Trajeto trajeto(String inicio, String chegada, MeioDeTransporte transporte) throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException{

		return new Trajeto(DefinirPartida(inicio), DefinirDestino(chegada), DefinirTransporte(transporte));
	}

}
