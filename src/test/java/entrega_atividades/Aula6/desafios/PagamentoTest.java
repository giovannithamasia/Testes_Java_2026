package entrega_atividades.Aula6.desafios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
        void verificarSeNaoEstaCobrandoTaxascomPagamentosViaPix(){
            Pagamento pagamentoPix = new PagamentoPix(10.0);

            double obtido = pagamentoPix.calcularTaxa();

            assertEquals(0.0,obtido,0.001);
        }
    }

    @Nested
    @DisplayName("Trabalhando com pagamento via cartao")
    @Tag("cartao")
    class TestesPagamentosCartao{

        @DisplayName("Testar valores negativos no preco")
        @ParameterizedTest(name = "Valor negativo: {0}")
        @ValueSource(doubles = {-1.0,-10.0,-100.0})
        void valorNegativoDeveLancarExcecao(double valor){
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoCartao(valor)
            );

            assertEquals("O valor deve ser maior que zero.",excecao.getMessage());
        }

        @Test
        @DisplayName("Testar se o valor é zero")
        void valorIgualZeroDeveLancarExcecao(){
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoCartao(0)
            );

            assertEquals("O valor deve ser maior que zero.",excecao.getMessage());
        }

        @Test
        @DisplayName("Testar se cobra taxa quando o pagamento for realizado via cartao")
        void valorPagamentoComTaxa(){
            Pagamento pagamentoCartao = new PagamentoCartao(10.0);

            double obtido = pagamentoCartao.calcularTaxa();

            assertEquals(0.25,obtido,0.001);
        }
    }

    @Nested
    @DisplayName("Fazer testes relacionados ao polimorfismo")
    class TestesPolimorficos{

        @Test
        @DisplayName("Verificar e confirmar calculo de taxa de pagamentos")
        void verificarResultadosDeTaxasDefinidasPorPagamento(){
            Pagamento pagamentoPix = new PagamentoPix(10.0);
            Pagamento pagamentoCartao = new PagamentoCartao(10.0);

            double taxaPix = pagamentoPix.calcularTaxa();
            double taxaPagamento = pagamentoCartao.calcularTaxa();

            assertAll(
                    () -> assertEquals(0.0,taxaPix,0.001),
                    () -> assertEquals(0.25,taxaPagamento,0.001)
            );
        }
    }
}
