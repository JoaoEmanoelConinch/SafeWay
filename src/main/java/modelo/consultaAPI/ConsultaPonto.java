package modelo.consultaAPI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.entidade.mapa.Ponto;
import modelo.excecao.mapa.StatusInvalidoException;

public class ConsultaPonto {
	
	public ConsultaPonto() {}

	public static Ponto informatLocal(String local)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		String localParaURL = local.replaceAll(" ", "%20");
		Client client = ClientBuilder.newClient();
		Response response = client.target(
				"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
						+ localParaURL)
				.request(MediaType.TEXT_PLAIN_TYPE)
				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
				.get();

		if (response.getStatus() == 4001) {
			throw new StatusInvalidoException("Erro de valor no paremetro");
		} else if (response.getStatus() == 4002) {
			throw new StatusInvalidoException("CabeÃ§alhos de HTTP errados");
		} else if (response.getStatus() == 4003) {
			throw new StatusInvalidoException("Problemas ao prover a geometria");
		} else if (response.getStatus() == 4004) {
			throw new StatusInvalidoException("Excedeu o nÃºmero de vÃ©rtices permitidos");
		} else if (response.getStatus() != 200) {
			throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
		}

		GeoJsonObject object = new ObjectMapper().readValue(response.readEntity(String.class), GeoJsonObject.class);

		Ponto ponto = new Ponto();

		ponto.setLongLatAlt((Point) ((FeatureCollection) object).getFeatures().get(0).getGeometry());
		
		return ponto;
	}
	
	
	
}
