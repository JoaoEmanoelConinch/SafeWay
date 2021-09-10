package controlador;


import java.util.Scanner;

import org.json.JSONObject;

import controlador.consultaAPI.JSONpontoDAO;
import controlador.consultaAPI.JSONpontoDAOImpl;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException{
		
		Scanner leitor = new Scanner(System.in);

		 

        System.out.println("Digite um alimento: ");
        String alimento = leitor.nextLine();

 

        JSONpontoDAO foodDAO = new JSONpontoDAOImpl();
			
			

 

        JSONObject jsonObject = foodDAO.readJsonFromUrl(
                "https://api.edamam.com/api/food-database/v2/parser?app_id=0ade5519&app_key=765cf001c347b17087e6af924d562b33&ingr="
                        + alimento + "&nutrition-type=cooking");

 

        JSONObject food = jsonObject.getJSONArray("parsed").getJSONObject(0).getJSONObject("food");
        double lucas = food.getJSONObject("nutrients").getDouble("ENERC_KCAL");
		
	}
}
