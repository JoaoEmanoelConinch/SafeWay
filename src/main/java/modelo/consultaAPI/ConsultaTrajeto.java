package modelo.consultaAPI;

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
//
//		Client client = ClientBuilder.newClient();
//		Entity<String> payload = Entity.json("{\"coordinates\":[" + inicio.TransformarVetorEmString() + ","
//		+ chegada.TransformarVetorEmString() + "],\"elevation\":\"true");
//		Response response = client
//				.target("https://api.openrouteservice.org/v2/directions/" + transporte.getDescricao() + "/geojson")
//				.request().header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
//				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
//				.header("Content-Type", "application/json; charset=utf-8").post(payload);
//
//		JSONObject jsonObject = (JSONObject) JSONObject.stringToValue(response.readEntity(String.class));
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject1 = JSONpontoDAO.readJsonFromUrl("https://api.openrouteservice.org/v2/directions/"
		+transporte.getDescricao()+"?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text&start="
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

//	public static ArrayList<ArrayList<ArrayList<PontoAvaliado>>> evitarPontos(ArrayList<PontoAvaliado> pontos,
//			Ponto inicio, Ponto chegada, MeioDeTransporte transporte) {
//		ArrayList<ArrayList<ArrayList<PontoAvaliado>>> evitar = new ArrayList<ArrayList<ArrayList<PontoAvaliado>>>();
//
//		int contador = 0;
//
//		for (int nota = 10; nota >= 0; nota--) {
//
//			for (int i = 1; i < pontos.size(); i++) {
//				if (pontos.get(i).getMediaDeAvaliacao() < nota) {
//					evitar.get(contador).get(contador).add(pontos.get(i));
//					contador++;
//				}
//			}
//
//			try {
//
//				Client client = ClientBuilder.newClient();
//				Entity<String> payload = Entity.json("{\"coordinates\":[" + inicio.TransformarVetorEmString() + ","
//						+ chegada.TransformarVetorEmString()
//						+ "],\"elevation\":\"true\",\"extra_info\":[\"steepness\",\"waycategory\",\"waytype\",\"tollways\"],\"options\":{\"avoid_polygons\":{\"type\":\"MultiPolygon\",\"coordinates\":"
//						+ evitar + "}}");
//				Response response = client
//						.target("https://api.openrouteservice.org/v2/directions/" + transporte.getDescricao()
//								+ "/geojson")
//						.request().header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
//						.header("Accept",
//								"application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
//						.header("Content-Type", "application/json; charset=utf-8").post(payload);
//
//				// evitar Pontos na varavel evitar
//
//				if (response.getStatus() != 200) {
//					throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
//				}
//
//			} catch (Exception e) {
//				evitar.removeAll(evitar);
//			}
//		}
//		return evitar;
//	}

}
