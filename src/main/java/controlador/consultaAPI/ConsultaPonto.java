package controlador.consultaAPI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.entidade.mapa.PontoAbstrato;
import modelo.excecao.mapa.NumeroMenorQueZeroException;
import modelo.excecao.mapa.StatusInvalidoException;

public class ConsultaPonto {
	
	public ConsultaPonto() {}

	public static PontoAbstrato informatLocal(String local)
			throws StatusInvalidoException, NumeroMenorQueZeroException{
		
		return informatLocal(local, 1);
	}

	public static PontoAbstrato informatLocal(String local, int posicao)
			throws StatusInvalidoException, NumeroMenorQueZeroException{

		posicao --;

		if (posicao < 0){
			throw new NumeroMenorQueZeroException("Posição invalida");
		}
		
		String localParaURL = local.replaceAll(" ", "%20");
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);
                
		BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao).getJSONObject("geometry").getJSONArray("coordinates").get(0);
		BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao).getJSONObject("geometry").getJSONArray("coordinates").get(1);

		PontoAbstrato ponto = new PontoAbstrato();

		ponto.setLatitude(latitude.doubleValue());
		ponto.setLongitude(longitude.doubleValue());
		
		return ponto;
	}
	
	public static List<PontoAbstrato> informatLocais(String local){
		List<PontoAbstrato> pontos = new ArrayList<PontoAbstrato>();

		String localParaURL = local.replaceAll(" ", "%20");
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);

		JSONArray tamanho = jsonObject.getJSONArray("features");

		for (int i = 0; i < tamanho.length(); i++){
			PontoAbstrato ponto = new PontoAbstrato();

			BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").get(0);
			BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").get(1);

			ponto.setLatitude(latitude.doubleValue());
			ponto.setLongitude(longitude.doubleValue());

			pontos.add(ponto);

		}

		return pontos;
	}
	
}
