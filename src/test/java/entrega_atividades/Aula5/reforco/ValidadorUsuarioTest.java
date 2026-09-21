package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorUsuarioTest {

    @DisplayName("Nomes com 3 ou mais caracteres devem ser aceitos")
    @ParameterizedTest(name = "Nome válido: {0}")
    @ValueSource(strings = {"Ana","Carlos","Maria","Joao123","usuario"})
    void validarNomesValidosDevemRetornaVerdadeiro(String nome){
        assertTrue(ValidadorUsuario.nomeValido(nome));
    }

    @DisplayName("Nomes nulos ou vazios devem ser rejeitados")
    @ParameterizedTest(name = "Ausência de valor: {0}")
    @NullAndEmptySource
    void valoresAusentesDevemRetornaFalso(String nome){
        assertFalse(ValidadorUsuario.nomeValido(nome));
    }

    @DisplayName("Nomes contendo apenas espaços em branco devem ser rejeitados")
    @ParameterizedTest(name = "Valor com espaços: {0}")
    @NullAndEmptySource
    @ValueSource(strings = {" ","  "})
    void nomeComEspacoDevemRetornaFalso(String nome){
        assertFalse(ValidadorUsuario.nomeValido(nome));
    }
}
