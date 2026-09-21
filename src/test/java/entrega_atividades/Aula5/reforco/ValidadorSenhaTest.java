package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidadorSenhaTest {

    @DisplayName("Validação de requisitos mínimos de segurança da senha")
    @ParameterizedTest(name = "Senha: {0} -> Válida? {1}")
    @CsvSource({
            "Senha123,true",
            "Teste2026,true",
            "senha123,false",
            "SENHAAAA,false",
            "Abc1,false",
            "12345678,false",
            "Joaosilva1,true",
            "drx1,false",
            "Pierryzzk2,true"
    })
    void verificarSenhaValida(String senha,boolean esperado){
        boolean obtido = ValidadorSenha.senhaValida(senha);

        assertEquals(esperado,obtido);
    }
}
