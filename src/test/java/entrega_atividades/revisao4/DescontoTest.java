package entrega_atividades.revisao4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescontoTest {

    @ParameterizedTest(name = "preço inválido: {0}")
    @ValueSource(doubles = {-0.01, -1.0, -100.0})
    void precoNegativoDeveLancarExcecao(double preco) {
        int percentual = 10;

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Desconto.calcular(preco, percentual)
        );

        assertEquals("O preço não pode ser negativo.", excecao.getMessage());
    }

    @ParameterizedTest(name = "caso {index}: R$ {0} - {1}% deve resultar em R$ {2}")
    @CsvSource({
            "100.00, 10, 90.00",
            "200.00, 25, 150.00",
            "80.00, 0, 80.00",
            "50.00, 100, 0.00"
    })
    void calcularDeveAplicarPercentual(double preco, int percentual, double esperado) {
        double resultado = Desconto.calcular(preco, percentual);

        assertEquals(esperado, resultado, 0.001);
    }
}
