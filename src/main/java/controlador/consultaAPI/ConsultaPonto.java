package controlador.consultaAPI;

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
                
		Object latitude = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(0);
		Object longitude = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(1);

		Ponto ponto = new Ponto();

		ponto.setLatitude((double) latitude);
		ponto.setLongitude((double) longitude);
		
		return null;
	}
	
	
	
}
