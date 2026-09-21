package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstacionamentoTest {

    @DisplayName("Valores cobrados por tempo de permanência")
    @ParameterizedTest(name = "Tempo: {0}h -> Valor esperado: R$ {1}")
    @CsvSource({
            "1,5.0",
            "2,10.0",
            "3,10.0",
            "4,15.0",
            "5,15.0",
            "6,15.0",
            "7,25.0",
            "8,25.0",
            "9,25.0",
            "10,25.0"
    })
    void calcularValorEstacionamento(int horas,double esperado){
        double obtido = Estacionamento.calcularValor(horas);

        assertEquals(esperado,obtido,0.001);
    }

    @DisplayName("Tempos inválidos ou negativos devem ser rejeitados")
    @ParameterizedTest(name = "Tempo inválido: {0}h")
    @ValueSource(ints = {0,-1,-5,-100})
    void valoresInvalidosDevemLancarExcecao(int horas){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Estacionamento.calcularValor(horas)
        );

        assertEquals("Tempo inválido.",
                excecao.getMessage());
    }
}
