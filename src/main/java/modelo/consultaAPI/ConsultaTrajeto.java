package modelo.consultaAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import modelo.entidade.mapa.Ponto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

public class ConsultaTrajeto {

	public static List<Ponto> criarLineString(Ponto inicio, Ponto chegada, MeioDeTransporte transporte)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException, StatusInvalidoException {

		List<Ponto> pontosDoTrajeto = new ArrayList<Ponto>();
		
		JSONpontoDAO JSONpontoDAO = new JSONpontoDAOImpl();
		
		JSONObject jsonObject1 = JSONpontoDAO.readJsonFromUrl("https://api.openrouteservice.org/v2/directions/"
		+transporte.getDescricao()+"?api_key=5b3ce3597851110001cf6248c4496801893e4eedb6c83eec3808dc6b&text&start=%20"
		+((Double)inicio.getLongitude()).toString()+",%20"+((Double)inicio.getLatitude()).toString()
		+"&end=%20"+((Double)chegada.getLongitude()).toString()+",%20"+((Double)chegada.getLatitude()).toString());

		JSONArray pontosDaAPI = jsonObject1.getJSONArray("features").getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");

		for (int i = 0; i < pontosDaAPI.length();i++){

			BigDecimal latitude = (BigDecimal) pontosDaAPI.getJSONArray(i).get(1);
			BigDecimal longitude = (BigDecimal) pontosDaAPI.getJSONArray(i).get(0);
			
			Ponto pontoDoTrajeto = new Ponto(latitude.doubleValue(),longitude.doubleValue());
			
			pontosDoTrajeto.add(pontoDoTrajeto);
			
		}

		return pontosDoTrajeto;

	}

}
