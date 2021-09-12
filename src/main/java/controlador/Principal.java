package controlador;

import org.json.JSONObject;

import controlador.consultaAPI.JSONpontoDAO;
import controlador.consultaAPI.JSONpontoDAOImpl;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, StatusInvalidoException{
		
                String localParaURL = "senac";
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
			"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
			+ localParaURL);
                
                Object JSONLatitude = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(0);
                Object JSONLongitude = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates").get(1);

                System.out.println(JSONLatitude);
                System.out.println(JSONLongitude);
	}
}
