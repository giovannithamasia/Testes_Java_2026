package entrega_atividades.Aula5.reforco;

public class Autenticador {

    public static boolean autenticar(String usuario, String senha, boolean ativo) {
        if (usuario == null || senha == null) {
            return false;
        }

        return usuario.equals("admin") &&
                senha.equals("Senai123") && ativo;
    }
}
