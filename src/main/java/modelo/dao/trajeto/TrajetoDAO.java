package modelo.dao.trajeto;

import java.util.List;

import modelo.entidade.mapa.Trajeto;
import modelo.entidade.usuario.UsuarioCadastrado;

public interface TrajetoDAO {
    
    void inserirTrajeto(Trajeto trajeto);

    void deletarTrajeto(Trajeto trajeto);

    void atualizarTrajeto(Trajeto trajeto);

    Trajeto recuperarTrajeto(Trajeto trajeto);
 
    List<Trajeto> recuperarTrajetosUsuario(UsuarioCadastrado usuario);
    
}
