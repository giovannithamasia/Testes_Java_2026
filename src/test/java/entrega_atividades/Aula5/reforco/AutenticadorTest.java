package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutenticadorTest {

    @DisplayName("Tentativas de Login (Múltiplos Cenários)")
    @ParameterizedTest(name = "User: {0} | Senha: {1} | Ativo: {2} -> Sucesso: {3}")
    @MethodSource("cenariosDeAutenticacao")
    void verificarAutenticador(String usuario,String senha,boolean ativo,
                               boolean esperado){
        boolean obtido = Autenticador.autenticar(usuario, senha, ativo);

        assertEquals(esperado,obtido);
    }

    static Stream<Arguments> cenariosDeAutenticacao() {
        return Stream.of(
                Arguments.of("admin","Senai123",true,true),
                Arguments.of("admin","Senai22",true,false),
                Arguments.of("user","Senai123",true,false),
                Arguments.of("admin","Senai123",false,false),
                Arguments.of(null,"Senai123",true,false),
                Arguments.of("admin",null,false,false)
        );
    }
}
