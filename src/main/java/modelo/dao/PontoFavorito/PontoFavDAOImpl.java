package modelo.dao.PontoFavorito;

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
import modelo.entidade.mapa.PontoFavorito;
import modelo.entidade.mapa.PontoFavorito_;
import modelo.entidade.mapa.Ponto_;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.entidade.usuario.UsuarioCadastrado_;
import modelo.factory.conexao.ConexaoFactory;

public class PontoFavDAOImpl implements PontoFavoritoDAO {

	private ConexaoFactory fabrica;

	public PontoFavDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	public void inserirPontoFavorito(PontoFavorito pontoFavorito) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(pontoFavorito);

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

	public void deletarPontoFavorito(PontoFavorito pontoFavorito) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(pontoFavorito);

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

	public void atualizarPontoFavorito(PontoFavorito pontoFavorito) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(pontoFavorito);

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

	public PontoFavorito recuperarPontoFavoritoId(PontoFavorito pontoFavorito) {

		Session sessao = null;
		PontoFavorito pontoFavorito1 = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoFavorito> criteria = construtor.createQuery(PontoFavorito.class);
			Root<PontoFavorito> raizPontoFav = criteria.from(PontoFavorito.class);

			ParameterExpression<Long> idPontoFav = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizPontoFav.get(PontoFavorito_.ID_PONTO), idPontoFav));

			pontoFavorito1 = sessao.createQuery(criteria).setParameter(idPontoFav, pontoFavorito.getId()).getSingleResult();

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

		return pontoFavorito1;

	}
	
	public List<PontoFavorito> recuperarPontoFavoritoUsuario(UsuarioCadastrado usuario){
		
		Session sessao = null;
		List<PontoFavorito> pontosFavoritos = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoFavorito> criteria = construtor.createQuery(PontoFavorito.class);
			Root<PontoFavorito> raizPontoFav = criteria.from(PontoFavorito.class);

			Join<PontoFavorito, UsuarioCadastrado> juncaoUsuario = raizPontoFav.join(PontoFavorito_.usuario);


			ParameterExpression<Long> idUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoUsuario.get(UsuarioCadastrado_.ID), idUsuario));

			pontosFavoritos = sessao.createQuery(criteria).setParameter(idUsuario, usuario.getId()).getResultList();

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

		return pontosFavoritos;
		
	}
	
}
