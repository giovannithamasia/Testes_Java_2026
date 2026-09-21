package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControleAcessoTest {

    @DisplayName("Regras de acesso por idade e acompanhamento")
    @ParameterizedTest(name = "Idade: {0}, Acompanhado: {1} -> Pode entrar? {2}")
    @CsvSource({
            "15,true,false",
            "15,false,false",
            "16,true,true",
            "16,false,false",
            "17,true,true",
            "17,false,false",
            "18,true,true",
            "18,false,true",
            "25,false,true",
    })
    void fazerControleDeAcessoVerificandoIdade(int idade,boolean acompanhado,
                                               boolean esperado){
        boolean obtido = ControleAcesso.podeEntrar(idade, acompanhado);

        assertEquals(esperado,obtido);
    }
}
