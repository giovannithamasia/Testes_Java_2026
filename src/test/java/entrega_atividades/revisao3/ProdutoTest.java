package entrega_atividades.revisao3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProdutoTest {

    @Test
    @DisplayName("Calcular valor total em estoque com sucesso")
    void calcularValorEstoqueSucesso() {
        Produto produto = new Produto("Bala", 25.0, 4);

        double resultado = produto.calcularValorEstoque();

        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Calcular preço com desconto com sucesso")
    void calcularPrecoComDescontoSucesso() {
        Produto produto = new Produto("Bola", 80.0, 1);

        double resultado = produto.calcularPrecoComDesconto(25.0);

        assertEquals(60.0, resultado, 0.001);
    }

    @Test
    @DisplayName("Construtor deve rejeitar nome nulo ou em branco")
    void construtorDeveRejeitarNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("", 10.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto("   ", 10.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto(null, 10.0, 5));
    }

    @Test
    @DisplayName("Construtor deve rejeitar preço menor ou igual a zero")
    void construtorDeveRejeitarPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Bala", 0.0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Bala", -10.0, 5));
    }

    @Test
    @DisplayName("Construtor deve rejeitar quantidade negativa")
    void construtorDeveRejeitarQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Bala", 10.0, -1));
    }

    @Test
    @DisplayName("Calcular desconto deve rejeitar percentual fora do intervalo 0-100")
    void calcularPrecoComDescontoDeveRejeitarPercentualInvalido() {
        Produto produto = new Produto("Bola", 80.0, 1);

        assertThrows(IllegalArgumentException.class, () -> produto.calcularPrecoComDesconto(-1.0));
        assertThrows(IllegalArgumentException.class, () -> produto.calcularPrecoComDesconto(100.1));
    }

    @Test
    @DisplayName("Validar todos os atributos e valor em estoque com assertAll")
    void validarAtributosEValorEstoqueComAssertAll() {
        Produto produto = new Produto("Caderno", 15.0, 10);

        assertAll("Validação completa do produto",
                () -> assertEquals("Caderno", produto.getNome()),
                () -> assertEquals(15.0, produto.getPreco(), 0.001),
                () -> assertEquals(10, produto.getQuantidade()),
                () -> assertEquals(150.0, produto.calcularValorEstoque(), 0.001)
        );
    }
}