package entrega_atividades.Aula5.reforco;

public class ClassificadorNota {

    public static String classificar(double nota){
        if (nota<0 || nota>10){
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10.");
        }

        if (nota<5){
            return "REPROVADO";
        }

        if (nota<7){
            return "RECUPERACAO";
        }

        return "APROVADO";
    }
}
