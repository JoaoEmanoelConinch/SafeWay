package modelo.dao.Trajeto;

import modelo.entidade.mapa.Trajeto;

public interface TrajetoDAO {
    
    void inserirTrajeto(Trajeto trajeto);

    void deletarTrajeto(Trajeto trajeto);

    void atualizarTrajeto(Trajeto trajeto);

}
