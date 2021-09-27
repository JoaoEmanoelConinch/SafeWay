package modelo.dao.Ponto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.mapa.PontoAbstrato;
import modelo.factory.conexao.ConexaoFactory;

public class PontoDAOImpl implements PontoDAO{

	private ConexaoFactory fabrica;
	
	public PontoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	public void inserirPonto(PontoAbstrato ponto) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(ponto);

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
	
	public void deletarPonto(PontoAbstrato ponto) {
		
		Session sessao = null;
		
		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();
			
			sessao.delete(ponto);
			
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

	public List<PontoAbstrato> recuperarPontos(){
		
		Session sessao = null;
		List<PontoAbstrato> pontos = null;
		
		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();
			
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			
			CriteriaQuery<PontoAbstrato> criteria = construtor.createQuery(PontoAbstrato.class);
			Root<PontoAbstrato> raizPonto = criteria.from(PontoAbstrato.class);
			
			criteria.select(raizPonto);
			
			pontos = sessao.createQuery(criteria).getResultList();
			
			sessao.getTransaction().commit();
			
		}catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return pontos;
		
		
	}
	
}
