package modelo.dao.Ponto;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.entidade.mapa.Ponto;
import modelo.factory.conexao.ConexaoFactory;

public class PontoDAOImpl implements PontoDAO{

	private ConexaoFactory fabrica;
	
	public PontoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	public void inserirPonto(Ponto ponto) {

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
	
	public void deletarPonto(Ponto ponto) {
		
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

	public List<Ponto> recuperarPontos(){
		
		Session sessao = null;
		List<Ponto> pontos = null;
		
		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();
			
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			
			CriteriaQuery<Ponto> criteria = construtor.createQuery(Ponto.class);
			Root<Ponto> raizPonto = criteria.from(Ponto.class);
			
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
