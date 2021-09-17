package controlador.consultaAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

public class ConsultaTrajeto {

	public static List<Ponto> criarLineString(Ponto inicio, Ponto chegada, MeioDeTransporte transporte)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {

		List<Ponto> pontosDoTrajeto = new ArrayList<Ponto>();
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject1 = JSONpontoDAO.readJsonFromUrl("https://api.openrouteservice.org/v2/directions/"
		+transporte.getDescricao()
		+"?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text&start="
		+((Double)inicio.getLatitude()).toString()+","+((Double)inicio.getLongitude()).toString()
		+"&end="+((Double)chegada.getLatitude()).toString()+","+((Double)chegada.getLongitude()).toString());

		JSONArray pontosDaAPI = jsonObject1.optJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");

		for (int i = 0; i < pontosDaAPI.length();i++){

			Ponto pontoDoTrajeto = new Ponto();

			BigDecimal latitude = (BigDecimal) pontosDaAPI.getJSONArray(i).get(0);
			BigDecimal longitude = (BigDecimal) pontosDaAPI.getJSONArray(i).get(1);

			pontoDoTrajeto.setLatitude(latitude.doubleValue());
			pontoDoTrajeto.setLongitude(longitude.doubleValue());
			
			pontosDoTrajeto.add(pontoDoTrajeto);
			
		}

		return pontosDoTrajeto;

	}

}
