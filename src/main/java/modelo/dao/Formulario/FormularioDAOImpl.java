package modelo.dao.Formulario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.formulario.Formulario_;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoAvaliado_;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.mapa.Trajeto_;
import modelo.factory.conexao.ConexaoFactory;

public class FormularioDAOImpl implements FormularioDAO {

	private ConexaoFactory fabrica;

	public FormularioDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
	public void inserirAvaliacao(Formulario formulario) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(formulario);

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

	public void deletarAvaliacao(Formulario formulario) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(formulario);

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

	public void atualizarAvaliacao(Formulario formlario) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(formlario);

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

	public List<Formulario> recuperarAvaliacoes(PontoAvaliado ponto) {

		Session sessao = null;
		List<Formulario> formularios = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Formulario> criteria = construtor.createQuery(Formulario.class);
			Root<Formulario> raizFormulario = criteria.from(Formulario.class);

			Join<Formulario, Ponto> juncaoPonto = raizFormulario.join(Formulario_.idPontoAvaliado);

			ParameterExpression<Long> idPontoAvaliado = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoPonto.get(PontoAvaliado_.ID_PONTO), idPontoAvaliado));

			formularios = sessao.createQuery(criteria).setParameter(idPontoAvaliado, ponto.getId()).getResultList();

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

		return formularios;
	}

	public Formulario recuperarAvaliacao(Formulario form) {

		Session sessao = null;
		Formulario f = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Formulario> criteria = construtor.createQuery(Formulario.class);
			Root<Formulario> raizFormulario = criteria.from(Formulario.class);

			ParameterExpression<Long> idFormulario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizFormulario.get(Formulario_.ID_FORMULARIO), idFormulario));

			f = sessao.createQuery(criteria).setParameter(idFormulario, form.getIdFormulario()).getSingleResult();

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

		return f;

	}

}
