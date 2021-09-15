package modelo.dao.PontoAvaliado;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoAvaliado_;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.entidade.usuario.UsuarioCadastrado_;
import modelo.factory.conexao.ConexaoFactory;

public class PontoAvaliadoDAOImpl implements PontoAvaliadoDAO {

	private ConexaoFactory fabrica;

	public PontoAvaliadoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
	public void adicionarPontoAvaliado(PontoAvaliado pontoAvaliado) {
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

	public List<PontoAvaliado> recuperarPontoAvMaiorQue(int nota) {

		Session sessao = null;
		List<PontoAvaliado> pontos = null;

		try {

//			sessao = fabrica.getConexao().openSession();
//			sessao.beginTransaction();
//
//			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
//
//			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
//			Root<PontoAvaliado> raizPonto = criteria.from(PontoAvaliado.class);
//
//			criteria.select(raizPonto).where(construtor.ge(raizPonto.get(Media_.mediaDeAvaliacao), nota));
//
//			TypedQuery<PontoAvaliado> queryPonto = sessao.createQuery(criteria);
//			pontos = queryPonto.getResultList();
//
//			sessao.getTransaction().commit();

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

	public List<PontoAvaliado> recuperarPontoAvMenorQue(int nota) {
	
		Session sessao = null;
		List<PontoAvaliado> pontos = null;

		try {

//			sessao = fabrica.getConexao().openSession();
//			sessao.beginTransaction();
//
//			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
//
//			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
//			Root<PontoAvaliado> raizPonto = criteria.from(PontoAvaliado.class);
//
//			criteria.select(raizPonto).where(construtor.lessThan(raizPonto.get(Media_.mediaDeAvaliacao), nota));
//
//			TypedQuery<PontoAvaliado> queryPonto = sessao.createQuery(criteria);
//			pontos = queryPonto.getResultList();
//
//			sessao.getTransaction().commit();

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

	public PontoAvaliado recuperarPontoAvaId(PontoAvaliado ponto) {
		
		Session sessao = null;
		PontoAvaliado pontoAv = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoAvaliado> criteria = construtor.createQuery(PontoAvaliado.class);
			Root<PontoAvaliado> raizPontoAv = criteria.from(PontoAvaliado.class);

			ParameterExpression<Long> idPontoAv = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizPontoAv.get(PontoAvaliado_.ID_PONTO), idPontoAv));

			pontoAv = sessao.createQuery(criteria).setParameter(idPontoAv, ponto.getId()).getSingleResult();
			
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

		return pontoAv;
		
	}

}
