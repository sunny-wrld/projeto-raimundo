package entidades;

import java.io.Serializable;

public interface ServicoAdicional extends Serializable {
    String getId();
    String getDescricao();
    double getValorTotal();
}