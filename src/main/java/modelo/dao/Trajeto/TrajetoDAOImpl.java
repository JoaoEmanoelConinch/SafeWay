package modelo.dao.Trajeto;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;

import org.hibernate.Session;

import modelo.entidade.mapa.Trajeto;
import modelo.entidade.mapa.Trajeto_;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.entidade.usuario.UsuarioCadastrado_;
import modelo.factory.conexao.ConexaoFactory;

public class TrajetoDAOImpl implements TrajetoDAO {

	private ConexaoFactory fabrica;

	public TrajetoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
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

	@Override
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

	@Override
	public void atualizarTrajeto(Trajeto trajeto) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(trajeto);

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

	public Trajeto recuperarTrajeto(Trajeto trajeto) {

		Session sessao = null;
		Trajeto trajeto1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Trajeto> criteria = construtor.createQuery(Trajeto.class);
			Root<Trajeto> raizTrajeto = criteria.from(Trajeto.class);

			ParameterExpression<Long> idTrajeto = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizTrajeto.get(Trajeto_.ID_TRAJETO), idTrajeto)); 

			trajeto1 = sessao.createQuery(criteria).setParameter(idTrajeto, trajeto.getIdTrajeto()).getSingleResult();
			
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

		return trajeto1;

	}

	
	
}
