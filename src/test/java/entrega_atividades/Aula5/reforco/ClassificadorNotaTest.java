package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassificadorNotaTest {

    @DisplayName("Notas entre 0 e 4.9 devem retornar REPROVADO")
    @ParameterizedTest(name = "Nota: {0} -> {1}")
    @CsvSource({
            "4.9,REPROVADO",
            "4.2,REPROVADO",
            "3.5,REPROVADO",
            "1.0,REPROVADO",
            "0.0,REPROVADO"
    })
    void verificarAlunosReprovados(double nota,String esperado){
        String obtido = ClassificadorNota.classificar(nota);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Notas entre 5.0 e 6.9 devem classificar como RECUPERACAO")
    @ParameterizedTest(name = "Nota: {0} -> {1}")
    @CsvSource({
            "5.0,RECUPERACAO",
            "5.5,RECUPERACAO",
            "5.9,RECUPERACAO",
            "6.0,RECUPERACAO",
            "6.5,RECUPERACAO",
            "6.7,RECUPERACAO",
            "6.9,RECUPERACAO",
    })
    void verificarAlunosEmRecuperacao(double nota,String esperado){
        String obtido = ClassificadorNota.classificar(nota);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Notas entre 7.0 e 10.0 devem classificar como APROVADO")
    @ParameterizedTest(name = "Nota: {0} -> {1}")
    @CsvSource({
            "7.0,APROVADO",
            "7.5,APROVADO",
            "8.0,APROVADO",
            "8.5,APROVADO",
            "9.0,APROVADO",
            "9.5,APROVADO",
            "10.0,APROVADO",
    })
    void verificarAlunosAprovados(double nota,String esperado){
        String obtido = ClassificadorNota.classificar(nota);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Notas negativas devem ser rejeitadas")
    @ParameterizedTest(name = "Tentativa com nota inválida: {0}")
    @ValueSource(doubles = {-0.1,-1.0,-10.0,-5.0,-2.0})
    void notasNegativasDevemLancarExcecao(double nota){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorNota.classificar(nota)
        );

        assertEquals("Nota deve estar entre 0 e 10.",
                excecao.getMessage());
    }

    @DisplayName("Notas acima de 10.0 devem ser rejeitadas")
    @ParameterizedTest(name = "Tentativa com nota inválida: {0}")
    @ValueSource(doubles = {10.1,10.5,11,11.5,12.0,13.0})
    void notasMaioresQueLimiteDevemLancarExcecao(double nota){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorNota.classificar(nota)
        );

        assertEquals("Nota deve estar entre 0 e 10.",
                excecao.getMessage());
    }
}
