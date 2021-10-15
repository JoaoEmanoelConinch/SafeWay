package modelo.dao.formulario;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.formulario.Formulario_;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Ponto_;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.entidade.usuario.UsuarioCadastrado_;
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

	public List<Formulario> recuperarAvaliacoes(Ponto ponto) {

		Session sessao = null;
		List<Formulario> forms = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Formulario> criteria = construtor.createQuery(Formulario.class);
			Root<Formulario> raizFormulario = criteria.from(Formulario.class);

			Join<Formulario, Ponto> juncaoPonto = raizFormulario.join(Formulario_.idPonto);


			ParameterExpression<Long> IdPonto = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoPonto.get(Ponto_.ID_PONTO), IdPonto));

			forms = sessao.createQuery(criteria).setParameter(IdPonto, ponto.getIdPonto()).getResultList();

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

		return forms;
	}

	public Formulario recuperarAvaliacaoId(Formulario form) {

		Session sessao = null;
		Formulario formulario = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Formulario> criteria = construtor.createQuery(Formulario.class);
			Root<Formulario> raizFormulario = criteria.from(Formulario.class);

			ParameterExpression<Long> idForm = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizFormulario.get(Formulario_.ID_FORMULARIO), idForm));

			formulario = sessao.createQuery(criteria).setParameter(idForm, form.getIdFormulario()).getSingleResult();
			
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

		return formulario;

	}

	@Override
	public List<Formulario> recuperarAvaliacoesDoUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;
		List<Formulario> forms = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Formulario> criteria = construtor.createQuery(Formulario.class);
			Root<Formulario> raizFormulario = criteria.from(Formulario.class);

			Join<Formulario, UsuarioCadastrado> juncaoUsuario = raizFormulario.join(Formulario_.USUARIO);


			ParameterExpression<Long> IdUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoUsuario.get(UsuarioCadastrado_.ID), IdUsuario));

			forms = sessao.createQuery(criteria).setParameter(IdUsuario, usuario.getId()).getResultList();

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

		return forms;
		
	}

}
