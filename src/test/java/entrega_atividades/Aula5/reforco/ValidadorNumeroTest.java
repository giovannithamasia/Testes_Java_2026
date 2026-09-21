package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorNumeroTest {

    @DisplayName("Números pares devem retornar verdadeiro")
    @ParameterizedTest(name = "Número par: {0}")
    @ValueSource(ints = {2,4,10,20,100, 1000})
    void numerosParesDevemRetornarVerdadeiro(int numero){
        assertTrue(ValidadorNumero.ehPar(numero));
    }

    @DisplayName("Números ímpares devem retornar falso")
    @ParameterizedTest(name = "Número impar: {0}")
    @ValueSource(ints = {1,3,7,15,99,101})
    void numerosImparesDevemRetornarFalso(int numero){
        assertFalse(ValidadorNumero.ehPar(numero));
    }
}
