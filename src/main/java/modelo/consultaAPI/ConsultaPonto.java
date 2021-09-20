package modelo.consultaAPI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.entidade.mapa.Ponto;
import modelo.excecao.mapa.StatusInvalidoException;

public class ConsultaPonto {
	
	public ConsultaPonto() {}

	public static Ponto informatLocal(String local)
			throws StatusInvalidoException{
		
		String localParaURL = local.replaceAll(" ", "%20");
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);
                
		BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(0);
		BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(1);

		Ponto ponto = new Ponto();

		ponto.setLatitude(latitude.doubleValue());
		ponto.setLongitude(longitude.doubleValue());
		
		return ponto;
	}

	public static Ponto informatLocal(String local, int posicao)
			throws StatusInvalidoException{
		
		String localParaURL = local.replaceAll(" ", "%20");
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);
                
		BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao).getJSONObject("geometry").getJSONArray("coordinates").get(0);
		BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao).getJSONObject("geometry").getJSONArray("coordinates").get(1);

		Ponto ponto = new Ponto();

		ponto.setLatitude(latitude.doubleValue());
		ponto.setLongitude(longitude.doubleValue());
		
		return ponto;
	}
	
	public static List<Ponto> informatLocais(String local){
		List<Ponto> pontos = new ArrayList<Ponto>();

		String localParaURL = local.replaceAll(" ", "%20");
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);

		JSONArray tamanho = jsonObject.getJSONArray("features");

		for (int i = 0; i < tamanho.length(); i++){
			Ponto ponto = new Ponto();

			BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").get(0);
			BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").get(1);

			ponto.setLatitude(latitude.doubleValue());
			ponto.setLongitude(longitude.doubleValue());

			pontos.add(ponto);

		}

		return pontos;
	}
	
}
