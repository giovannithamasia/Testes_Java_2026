package entrega_atividades.Aula5.reforco;

public class ValidadorUsuario {

    public static boolean nomeValido(String nome){
        if (nome == null || nome.isBlank()) {
            return false;
        }

        return nome.length() >= 3;
    }
}
