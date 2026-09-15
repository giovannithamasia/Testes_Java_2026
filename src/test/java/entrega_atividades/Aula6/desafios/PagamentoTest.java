package entrega_atividades.Aula6.desafios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Hierarquia de pagamentos")
class PagamentoTest {

    @Nested
    @DisplayName("Trabalhando com pagamento via pix")
    class TestesPagamentoPix{

        @DisplayName("Testar valores negativos no preco")
        @ParameterizedTest(name = "Valor negativo: {0}")
        @ValueSource(doubles = {-1.0,-10.0,-100.0})
        void valorNegativoDeveLancarExcecao(double valor){

            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(valor)
            );

            assertEquals("O valor deve ser maior que zero.",excecao.getMessage());
        }

        @Test
        @DisplayName("Testar se o valor é zero")
        void valorIgualZeroDeveLancarExcecao(){
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(0)
            );

            assertEquals("O valor deve ser maior que zero.",excecao.getMessage());
        }

        @Test
        @DisplayName("Testar se não cobra taxa quando o pagamento for realizado via pix")
        void valorPagamentoSemTaxas(){
            Pagamento pagamentoPix = new PagamentoPix(10.00);

            double esperado = pagamentoPix.calcularTaxa();

            assertEquals(pagamentoPix.getValor(),esperado,0.001);
        }
    }

}
