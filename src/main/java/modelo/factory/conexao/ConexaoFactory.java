package modelo.factory.conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConexaoFactory {

	public SessionFactory getConexao() {
		
		Configuration configuracao = new Configuration();

		configuracao.addAnnotatedClass(modelo.entidade.formulario.Formulario.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.Ponto.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoAvaliado.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoFavorito.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.Trajeto.class);
		configuracao.addAnnotatedClass(modelo.entidade.usuario.UsuarioCadastrado.class);

		configuracao.configure("hibernate.cfg.xml");

		ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();

		SessionFactory fabricaSessao = configuracao.buildSessionFactory(servico);

		return fabricaSessao;
		
	}
	
}
