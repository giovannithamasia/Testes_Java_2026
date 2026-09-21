package entrega_atividades.Aula5.reforco;

public class ValidadorSenha {
    public static boolean senhaValida(String senha) {
        if (senha == null) {
            return false;
        }

        return senha.length() >= 8 && senha.matches(".*[A-Z].*")
                && senha.matches(".*[0-9].*");
    }
}
