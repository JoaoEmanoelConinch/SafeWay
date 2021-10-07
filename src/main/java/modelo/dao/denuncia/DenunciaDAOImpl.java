package modelo.dao.denuncia;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.Denuncia.Denuncia;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Ponto_;
import modelo.factory.conexao.ConexaoFactory;

public class DenunciaDAOImpl implements DenunciaDAO {

	private ConexaoFactory fabrica;

	public DenunciaDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
	public void inserirDenuncia(Denuncia denuncia) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(denuncia);

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

	public void deletarDenuncia(Denuncia denuncia) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(denuncia);

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

	public void atualizarDenuncia(Denuncia denuncia) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(denuncia);

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

	public List<Denuncia> recuperarDenuncias(Ponto ponto) {

		Session sessao = null;
		List<Denuncia> forms = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Denuncia> criteria = construtor.createQuery(Denuncia.class);
			Root<Denuncia> raizDenuncia = criteria.from(Denuncia.class);

			Join<Denuncia, Ponto> juncaoPonto = raizDenuncia.join(Denuncia_.idPonto);


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

	public Denuncia recuperarDenuncia(Denuncia denuncia) {

		Session sessao = null;
		Denuncia denunciaR = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Denuncia> criteria = construtor.createQuery(Denuncia.class);
			Root<Denuncia> raizDenuncia = criteria.from(Denuncia.class);

			ParameterExpression<Long> idForm = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizDenuncia.get(Denuncia_.ID_Denuncia), idForm));

			denunciaR = sessao.createQuery(criteria).setParameter(idForm, denuncia.getIdFormulario()).getSingleResult();
			
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

		return denunciaR;

	}

}
