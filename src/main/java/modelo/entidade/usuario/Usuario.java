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
import modelo.entidade.mapa.PontoAbstrato;
import modelo.entidade.mapa.Trajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

	public Ponto DefinirLocal (String local) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		return Ponto.informarLocal(local);
	}

	public Ponto DefinirLocal (String local, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{
		return Ponto.informarLocal(local, posicao);
	}
	
	public Ponto DefinirPartida(String inicio) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(inicio);
	}

	public Ponto DefinirDestino(String chegada) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(chegada);
	}

	public Ponto DefinirPartida(String inicio, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(inicio, posicao);
	}

	public Ponto DefinirDestino(String chegada, int posicao) throws StatusInvalidoException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {
		return DefinirLocal(chegada, posicao);
	}

	public MeioDeTransporte DefinirTransporte(MeioDeTransporte transporte) {
		return transporte;
	}

	public Trajeto trajeto(String inicio, String chegada, MeioDeTransporte transporte)
			throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{

		return new Trajeto(DefinirPartida(inicio), DefinirDestino(chegada), DefinirTransporte(transporte));
	}

	public Trajeto trajeto(String inicio, int posicaoInicio, String chegada, int posicaoChegada, MeioDeTransporte transporte)
			throws JsonParseException, JsonMappingException, StatusInvalidoException, IOException, NumeroMenorQueZeroException, NumeroMaiorQueLimiteException{

		return new Trajeto(DefinirPartida(inicio, posicaoInicio), DefinirDestino(chegada, posicaoChegada),DefinirTransporte(transporte));
	}

	public Trajeto trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws JsonParseException, JsonMappingException, IOException{

		Trajeto trajeto = new Trajeto();
		trajeto.setInicio(inicio);
		trajeto.setChegada(chegada);
		trajeto.setTransporteUsado(transporteUsado);
		trajeto.criarLineString();

		return trajeto;

	}

}
