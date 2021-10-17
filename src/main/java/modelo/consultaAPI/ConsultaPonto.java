package modelo.consultaAPI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import modelo.entidade.mapa.Ponto;
import modelo.excecao.mapa.NumeroMaiorQueLimiteException;

import modelo.excecao.mapa.NumeroMenorQueZeroException;

public class ConsultaPonto {

	public ConsultaPonto() {
	}

	public static Ponto informarLocal(String local)
			throws  NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {

		return informarLocal(local, 1);
	}

	public static Ponto informarLocal(String local, int posicao)
			throws  NumeroMenorQueZeroException, NumeroMaiorQueLimiteException {

		posicao--;

		if (posicao < 0) {
			throw new NumeroMenorQueZeroException("Posição invalida");
		}

		String localParaURL = local.replaceAll(" ", "%20");

		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();

		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
				"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf6248c4496801893e4eedb6c83eec3808dc6b&text="
						+ localParaURL);

		JSONArray pontosCompativeis = jsonObject.getJSONArray("features");

		if (posicao > pontosCompativeis.length()) {
			throw new NumeroMaiorQueLimiteException("Posição inexistente");
		}

		BigDecimal longitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao)
				.getJSONObject("geometry").getJSONArray("coordinates").get(0);
		BigDecimal latitude = (BigDecimal) jsonObject.getJSONArray("features").getJSONObject(posicao)
				.getJSONObject("geometry").getJSONArray("coordinates").get(1);

		Ponto ponto = new Ponto(latitude.doubleValue(), longitude.doubleValue());

		return ponto;
	}

	public static String informarLatLong(Ponto ponto) {

		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();

		JSONObject jsonObject = JSONpontoDAO.readJsonFromUrl(
				"https://api.openrouteservice.org/geocode/reverse?api_key=5b3ce3597851110001cf6248c4496801893e4eedb6c83eec3808dc6b&"
						+ "point.lon=" + ponto.getLongitude() + "&point.lat=" + ponto.getLatitude()
						+ "&size=1&layers=street,neighbourhood,venue");

		JSONObject LocalData = jsonObject.getJSONArray("features").getJSONObject(0).getJSONObject("properties");

		String label = (String) LocalData.get("label");

		return label;
	}

}
