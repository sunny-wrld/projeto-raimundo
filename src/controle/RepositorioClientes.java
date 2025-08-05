package controle;

import entidades.Cliente;
import excecoes.FalhaPersistenciaException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//tive problema na hora de subir os bat, recorri ao sonnet e
// vi que tunha um erro, sei ele pra corriggir todas as partes do hash, por burrice minha mesmo

public class RepositorioClientes implements Serializable {
    private Map<String, Cliente> clientes = new HashMap<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }

    public Cliente buscarCliente(String cpf) {
        return clientes.get(cpf);
    }

    public void removerCliente(String cpf) {
        clientes.remove(cpf);
    }

    public Map<String, Cliente> listarClientes() {
        return clientes;
    }

    public void salvarArquivo(String caminho) throws FalhaPersistenciaException {
        try {
            FileOutputStream arquivo = new FileOutputStream(caminho);
            ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
            gravador.writeObject(clientes);
            gravador.close();
            arquivo.close();
        } catch (IOException e) {
            throw new FalhaPersistenciaException("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public void carregarArquivo(String caminho) throws FalhaPersistenciaException {
        try {
            FileInputStream arquivo = new FileInputStream(caminho);
            ObjectInputStream leitor = new ObjectInputStream(arquivo);
            clientes = (Map<String, Cliente>) leitor.readObject();
            leitor.close();
            arquivo.close();
        } catch (FileNotFoundException e) {
            clientes = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new FalhaPersistenciaException("Erro ao carregar clientes: " + e.getMessage());
        }
    }
}
