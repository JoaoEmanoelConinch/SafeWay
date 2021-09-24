package modelo.dao.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.mapa.PontoAvaliado;
import modelo.entidade.mapa.PontoAvaliado_;
import modelo.entidade.mapa.Trajeto;
import modelo.entidade.mapa.Trajeto_;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.entidade.usuario.UsuarioCadastrado_;
import modelo.entidade.usuario.Usuario_;
import modelo.factory.conexao.ConexaoFactory;

public class UsuarioDAOImpl implements UsuarioDAO {

	private ConexaoFactory fabrica;

	public UsuarioDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	public void inserirUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {
			
			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(usuario);

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

	public void deletarUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			
			
			sessao.delete(usuario);

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

	public void atualizarUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(usuario);

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

	public UsuarioCadastrado recuperarUsuarioId(UsuarioCadastrado usuario) {

		Session sessao = null;
		UsuarioCadastrado usuario1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<UsuarioCadastrado> criteria = construtor.createQuery(UsuarioCadastrado.class);
			Root<UsuarioCadastrado> raizUsuario = criteria.from(UsuarioCadastrado.class);

			ParameterExpression<Long> idUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizUsuario.get(UsuarioCadastrado_.ID), idUsuario));

			usuario1 = sessao.createQuery(criteria).setParameter(idUsuario, usuario.getId()).getSingleResult();
			
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

		return usuario1;

	}

	public UsuarioCadastrado recuperarUsuario(UsuarioCadastrado usuario) {
		
		Session sessao = null;
		UsuarioCadastrado usuarioRetornado = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<UsuarioCadastrado> criteria = construtor.createQuery(UsuarioCadastrado.class);
			Root<UsuarioCadastrado> raizUsuario = criteria.from(UsuarioCadastrado.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			
			predicates.add(construtor.equal(raizUsuario.get(UsuarioCadastrado_.EMAIL), usuario.getEmail()));
			predicates.add(construtor.equal(raizUsuario.get(UsuarioCadastrado_.SENHA), usuario.getSenha()));
						
			criteria.select(raizUsuario).where(predicates.toArray(new Predicate[] {}));

			usuarioRetornado = sessao.createQuery(criteria).getSingleResult();

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

		return usuarioRetornado;
		
	}
	
}
