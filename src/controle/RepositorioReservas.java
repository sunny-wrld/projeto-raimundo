package controle;

import entidades.Reserva;
import excecoes.FalhaPersistenciaException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RepositorioReservas implements Serializable {
    private Map<String, Reserva> reservas = new HashMap<>();

    public void adicionarReserva(Reserva reserva) {
        reservas.put(reserva.getId(), reserva);
    }

    public Reserva buscarReserva(String id) {
        return reservas.get(id);
    }

    public void removerReserva(String id) {
        reservas.remove(id);
    }

    public Map<String, Reserva> listarReservas() {
        return reservas;
    }

    public void salvarArquivo(String caminho) throws FalhaPersistenciaException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(reservas);
        } catch (IOException e) {
            throw new FalhaPersistenciaException("Erro ao salvar reservas: " + e.getMessage());
        }
    }

    public void carregarArquivo(String caminho) throws FalhaPersistenciaException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            reservas = (Map<String, Reserva>) ois.readObject();
        } catch (FileNotFoundException e) {
            reservas = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new FalhaPersistenciaException("Erro ao carregar reservas: " + e.getMessage());
        }
    }
}