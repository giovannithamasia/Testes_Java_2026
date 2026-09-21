package entrega_atividades.Aula5.reforco;

public class Estacionamento {

    public static double calcularValor(int horas){
        if (horas <=0){
            throw new IllegalArgumentException("Tempo inválido.");
        }

        if (horas<=1){
            return 5.0;
        }

        if (horas<=3){
            return 10.0;
        }

        if (horas<=6){
            return 15.0;
        }

        return 25.0;
    }
}
