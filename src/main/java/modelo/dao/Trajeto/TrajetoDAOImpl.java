package modelo.dao.Trajeto;

import org.hibernate.Session;

import modelo.entidade.mapa.Trajeto;
import modelo.factory.conexao.ConexaoFactory;

public class TrajetoDAOImpl implements TrajetoDAO {

	
	private ConexaoFactory fabrica;
	
	public TrajetoDAOImpl() {
		fabrica = new ConexaoFactory();
	}
	
	public void inserirTrajeto(Trajeto trajeto) {
		
		Session sessao = null;
		
		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();
			
			sessao.save(trajeto);
			
			sessao.getTransaction().commit();
			
		} catch (Exception erro) {
			erro.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
		
	}

	
	public void deletarTrajeto(Trajeto trajeto) {
		
		Session sessao = null;
		
		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();
			
			sessao.delete(trajeto);
			
			sessao.getTransaction().commit();
			
		} catch (Exception erro) {
			erro.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
		
	}

}
