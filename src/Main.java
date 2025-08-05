import controle.AdministradorSistema;
import fronteira.MenuPrincipal;
import excecoes.FalhaPersistenciaException;

public class Main {
    public static void main(String[] args) {
        AdministradorSistema admin = new AdministradorSistema();
        try {
            admin.carregarTudo();
        } catch (FalhaPersistenciaException e) {
            System.out.println("erro ao carregar dados: " + e.getMessage());
        }
        MenuPrincipal menu = new MenuPrincipal(admin);
        menu.exibirMenu();
        try {
            admin.salvarTudo();
        } catch (FalhaPersistenciaException e) {
            System.out.println("erro ao salvar dados: " + e.getMessage());
        }
    }
}