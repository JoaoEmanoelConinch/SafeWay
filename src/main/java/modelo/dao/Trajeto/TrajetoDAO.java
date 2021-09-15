package modelo.dao.Trajeto;

import java.util.List;

import modelo.entidade.mapa.Trajeto;

public interface TrajetoDAO {
    
    void inserirTrajeto(Trajeto trajeto);

    void deletarTrajeto(Trajeto trajeto);

    void atualizarTrajeto(Trajeto trajeto);

    List<Trajeto> recuperarTrajeto(Trajeto trajeto);
  
}
