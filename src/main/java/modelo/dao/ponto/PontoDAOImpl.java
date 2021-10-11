package modelo.dao.ponto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;


import modelo.entidade.formulario.Formulario;
import modelo.entidade.formulario.Formulario_;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Ponto_;
import modelo.factory.conexao.ConexaoFactory;

public class PontoDAOImpl implements PontoDAO {

	private ConexaoFactory fabrica;
	private Ponto p;

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

	public void atualizarPonto(Ponto ponto) {
		
		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(ponto);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
		
	}
	
	public List<Ponto> recuperarPontos() {

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

		} catch (Exception sqlException) {

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

	public Ponto recuperarPonto(Ponto ponto) {
		
		Session sessao = null;
		Ponto ponto1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Ponto> criteria = construtor.createQuery(Ponto.class);
			Root<Ponto> raizPonto = criteria.from(Ponto.class);

			ParameterExpression<Long> idPonto = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizPonto.get(Ponto_.ID_PONTO), idPonto));

			ponto1 = sessao.createQuery(criteria).setParameter(idPonto, ponto.getIdPonto()).getSingleResult();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return ponto1;
	}

	@Override
	public Ponto verificarPonto(Ponto ponto) {

		this.p = ponto;
		Session sessao = null;
		Ponto ponto1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Ponto> criteria = construtor.createQuery(Ponto.class);
			Root<Ponto> raizPonto = criteria.from(Ponto.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(construtor.equal(raizPonto.get(Ponto_.LATITUDE), ponto.getLatitude()));
			predicates.add(construtor.equal(raizPonto.get(Ponto_.LONGITUDE), ponto.getLongitude()));

			criteria.select(raizPonto).where(predicates.toArray(new Predicate[] {}));

			ponto1 = sessao.createQuery(criteria).getSingleResult();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return ponto1;

	}

	public Ponto recuperarPontoAvaliacoes(Ponto ponto) {
		
		Session sessao = null;
		Ponto ponto1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Ponto> criteria = construtor.createQuery(Ponto.class);
			Root<Ponto> raizContato = criteria.from(Ponto.class);

			Join<Ponto, Formulario> juncaoCliente = raizContato.join(Ponto_.avaliacoes);

			ParameterExpression<Long> idPonto = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoCliente.get(Formulario_.ID_PONTO), idPonto));

			ponto1 = sessao.createQuery(criteria).setParameter(idPonto, ponto.getIdPonto()).getSingleResult();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return ponto1;
		
	}
	
	public boolean verificarPontoExiste(Ponto ponto) {
		
		this.p = ponto;
		Session sessao = null;
		Ponto ponto1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Ponto> criteria = construtor.createQuery(Ponto.class);
			Root<Ponto> raizPonto = criteria.from(Ponto.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(construtor.equal(raizPonto.get(Ponto_.LATITUDE), ponto.getLatitude()));
			predicates.add(construtor.equal(raizPonto.get(Ponto_.LONGITUDE), ponto.getLongitude()));

			criteria.select(raizPonto).where(predicates.toArray(new Predicate[] {}));

			ponto1 = sessao.createQuery(criteria).getSingleResult();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		if(ponto1 != null)
			return true;
		
		return false;
	}
	
}
