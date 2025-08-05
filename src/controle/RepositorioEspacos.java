package controle;

import entidades.Espaco;
import excecoes.FalhaPersistenciaException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RepositorioEspacos implements Serializable {
    private Map<String, Espaco> espacos = new HashMap<>();

    public void adicionarEspaco(Espaco espaco) {
        espacos.put(espaco.getId(), espaco);
    }

    public Espaco buscarEspaco(String id) {
        return espacos.get(id);
    }

    public void removerEspaco(String id) {
        espacos.remove(id);
    }

    public Map<String, Espaco> listarEspacos() {
        return espacos;
    }

    public void salvarArquivo(String caminho) throws FalhaPersistenciaException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(espacos);
        } catch (IOException e) {
            throw new FalhaPersistenciaException("Erro ao salvar espacos: " + e.getMessage());
        }
    }

    public void carregarArquivo(String caminho) throws FalhaPersistenciaException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            espacos = (Map<String, Espaco>) ois.readObject();
        } catch (FileNotFoundException e) {
            espacos = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new FalhaPersistenciaException("Erro ao carregar espacos: " + e.getMessage());
        }
    }
}
