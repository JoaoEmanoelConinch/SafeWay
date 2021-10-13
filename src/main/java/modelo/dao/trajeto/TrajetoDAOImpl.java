package modelo.dao.trajeto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.Ponto_;
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

	public List<Trajeto> recuperarTajetosUsuario(UsuarioCadastrado usuario){
		
		Session sessao = null;
		List<Trajeto> trajetos = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Trajeto> criteria = construtor.createQuery(Trajeto.class);
			Root<Trajeto> raizPontoFav = criteria.from(Trajeto.class);

			Join<Trajeto, UsuarioCadastrado> juncaoUsuario = raizPontoFav.join(Trajeto_.usuariosCadastrados);


			ParameterExpression<Long> idUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoUsuario.get(UsuarioCadastrado_.ID), idUsuario));

			trajetos = sessao.createQuery(criteria).setParameter(idUsuario, usuario.getId()).getResultList();

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

		return trajetos;
		
	}

	
	public List<Trajeto> recuperarTrajetosUsuario(UsuarioCadastrado usuario) {
		
		Session sessao = null;
		List<Trajeto> trajetos = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Trajeto> criteria = construtor.createQuery(Trajeto.class);
			Root<Trajeto> raizTrajeto = criteria.from(Trajeto.class);

			Join<Trajeto, UsuarioCadastrado> juncaoPonto = raizTrajeto.join(Trajeto_.usuariosCadastrados);


			ParameterExpression<Long> IdUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoPonto.get(UsuarioCadastrado_.ID), IdUsuario));

			trajetos = sessao.createQuery(criteria).setParameter(IdUsuario, usuario.getId()).getResultList();

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

		return trajetos;
		
	}

	@Override
	public List<Trajeto> recuperarTrajetosUsuariosDePonto(Ponto ponto) {
		Session sessao = null;
		List<Trajeto> trajetos = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Trajeto> criteria = construtor.createQuery(Trajeto.class);
			Root<Trajeto> raizTrajeto = criteria.from(Trajeto.class);

			Join<Trajeto, Ponto> juncaoPonto = raizTrajeto.join(Trajeto_.pontos);


			ParameterExpression<Long> IdPonto = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoPonto.get(Ponto_.ID_PONTO), IdPonto));

			trajetos = sessao.createQuery(criteria).setParameter(IdPonto, ponto.getIdPonto()).getResultList();

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

		return trajetos;
	}
	
}
