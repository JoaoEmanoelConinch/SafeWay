package modelo.dao.PontoAvaliado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoAvaliado_;
import modelo.entidade.mapa.Ponto_;
import modelo.factory.conexao.ConexaoFactory;

public class PontoAvaliadoDAOImpl implements PontoAvaliadoDAO {

	private ConexaoFactory fabrica;

	public PontoAvaliadoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
	public void inserirPontoAvaliado(PontoAvaliado pontoAvaliado) {
		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(pontoAvaliado);

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

	@Override
	public void deletarPontoAvaliado(PontoAvaliado pontoAvaliado) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(pontoAvaliado);

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

	@Override
	public void atualizarPontoAvaliado(PontoAvaliado pontoAvaliado) {
		
		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(pontoAvaliado);

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
	
	@Override
	public List<PontoAvaliado> recuperarPontosAvaliados() {

		Session sessao = null;
		List<PontoAvaliado> pontosAvaliados = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
			Root<PontoAvaliado> raizPonto = criteria.from(PontoAvaliado.class);

			criteria.select(raizPonto);

			pontosAvaliados = sessao.createQuery(criteria).getResultList();

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

		return pontosAvaliados;

	}

	@Override
	public List<PontoAvaliado> recuperarPontoAvaliadoMediaIgual(PontoAvaliado pontoAvaliado) {

		Session sessao = null;
		List<PontoAvaliado> pontosAvaliados = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
			Root<PontoAvaliado> raizPonto = criteria.from(PontoAvaliado.class);

			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(construtor.equal(raizPonto.get(PontoAvaliado_.mediaDeAvaliacao), pontoAvaliado.getMediaDeAvaliacao()));
			
			criteria.select(raizPonto).where(predicates.toArray(new Predicate[] {}));

			pontosAvaliados = sessao.createQuery(criteria).getResultList();

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

		return pontosAvaliados;

	}

	@Override
	public PontoAvaliado recuperarPontoAvaId(PontoAvaliado pontoAvaliado) {

		Session sessao = null;
		PontoAvaliado pontoAvaliado1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
			Root<PontoAvaliado> raizPontoAv = criteria.from(PontoAvaliado.class);

			ParameterExpression<Long> idPontoAv = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizPontoAv.get(PontoAvaliado_.ID_PONTO), idPontoAv));

			pontoAvaliado1 = sessao.createQuery(criteria).setParameter(idPontoAv, pontoAvaliado.getId()).getSingleResult();

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

		return pontoAvaliado1;

	}

	public PontoAvaliado verificarPontoAvaliado(Ponto ponto) {

		
		Session sessao = null;
		PontoAvaliado pontoAvaliado = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
			Root<PontoAvaliado> raizPontoAvaliado = criteria.from(PontoAvaliado.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(construtor.equal(raizPontoAvaliado.get(PontoAvaliado_.), ponto.getLatitude()));
			predicates.add(construtor.equal(raizPontoAvaliado.get(PontoAvaliado_.LONGITUDE), ponto.getLongitude()));

			criteria.select(raizPontoAvaliado).where(predicates.toArray(new Predicate[] {}));

			pontoAvaliado = sessao.createQuery(criteria).getSingleResult();

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

		return pontoAvaliado;

	}
	
}
