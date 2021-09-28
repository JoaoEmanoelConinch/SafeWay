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

	public void inserirPontoFav(PontoFavorito pontoFav) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(pontoFav);

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

	public void deletarPontoFav(PontoFavorito pontoFav) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(pontoFav);

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

	public void atualizarPontoFav(PontoFavorito pontoFav) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(pontoFav);

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

	public PontoFavorito recuperarPontoFavId(PontoFavorito ponto) {

		Session sessao = null;
		PontoFavorito pontoFav = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoFavorito> criteria = construtor.createQuery(PontoFavorito.class);
			Root<PontoFavorito> raizPontoFav = criteria.from(PontoFavorito.class);

			ParameterExpression<Long> idPontoFav = construtor.parameter(Long.class);
			criteria.where(construtor.equal(raizPontoFav.get(PontoFavorito_.ID_PONTO), idPontoFav));

			pontoFav = sessao.createQuery(criteria).setParameter(idPontoFav, ponto.getId()).getSingleResult();

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

		return pontoFav;

	}
	
	public List<PontoFavorito> recuperarPontoFavoritoUsuario(UsuarioCadastrado usuario){
		
		Session sessao = null;
		List<PontoFavorito> pontos = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<PontoFavorito> criteria = construtor.createQuery(PontoFavorito.class);
			Root<PontoFavorito> raizPontoFav = criteria.from(PontoFavorito.class);

			Join<PontoFavorito, UsuarioCadastrado> juncaoUsuario = raizPontoFav.join(PontoFavorito_.usuario);


			ParameterExpression<Long> idUsuario = construtor.parameter(Long.class);
			criteria.where(construtor.equal(juncaoUsuario.get(UsuarioCadastrado_.ID), idUsuario));

			pontos = sessao.createQuery(criteria).setParameter(idUsuario, usuario.getId()).getResultList();

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
	
}
