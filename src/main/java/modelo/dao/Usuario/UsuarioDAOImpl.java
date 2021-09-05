package modelo.dao.Usuario;

import org.hibernate.Session;

import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.factory.conexao.ConexaoFactory;

public class UsuarioDAOImpl implements UsuarioDAO {

	private ConexaoFactory fabrica;
	
	public UsuarioDAOImpl() {
		fabrica = new ConexaoFactory();	
	}

	public void inserirUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {
//<==>
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

}
