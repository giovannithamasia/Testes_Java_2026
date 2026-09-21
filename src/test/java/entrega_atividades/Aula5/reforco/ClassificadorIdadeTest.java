package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassificadorIdadeTest {

    @DisplayName("Idades válidas devem retornar a classificação correta")
    @ParameterizedTest(name = "Idade: {0} anos -> Categoria: {1}")
    @CsvSource({
            "0,CRIANCA",
            "5,CRIANCA",
            "12,CRIANCA",
            "13,ADOLESCENTE",
            "17,ADOLESCENTE",
            "18,ADULTO",
            "30,ADULTO",
            "59,ADULTO",
            "60,IDOSO",
            "80,IDOSO"
    })
    void verificarClassifacoesIdadesValidas(int idade,String esperado){
        String obtido = ClassificadorIdade.classificar(idade);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Idades negativas devem ser rejeitadas com erro")
    @ParameterizedTest(name = "Tentativa com idade inválida: {0}")
    @ValueSource(ints = {-1,-5,-20,-100})
    void deveLancarExcecaoQuandoIdadeForInvalida(int idade){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorIdade.classificar(idade)
        );

        assertEquals("Idade não pode ser negativa.",
                excecao.getMessage());
    }
}
