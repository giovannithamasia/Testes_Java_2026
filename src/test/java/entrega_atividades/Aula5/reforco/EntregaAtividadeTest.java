package entrega_atividades.Aula5.reforco;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntregaAtividadeTest {

    @DisplayName("Entregas antecipadas ou no horário exato ficam NO_PRAZO")
    @ParameterizedTest(name = "Atraso: {0} min, justificativa: {1} -> {2}")
    @CsvSource({
            "0,true,NO_PRAZO",
            "0,false,NO_PRAZO",
            "-1,true,NO_PRAZO",
            "-2,false,NO_PRAZO"
    })
    void atividadeEntregueDeveEstarDentroDoPrazoEstipulado(int minutosAtraso,
                                                           boolean justificativa,
                                                           String esperado){
        String obtido = EntregaAtividade.
                verificar(minutosAtraso, justificativa);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Entregas com até 10 minutos de atraso ficam com ATRASO_TOLERADO")
    @ParameterizedTest(name = "Atraso: {0} min, justificativa: {1} -> {2}")
    @CsvSource({
            "1,true,ATRASO_TOLERADO",
            "1,false,ATRASO_TOLERADO",
            "2,true,ATRASO_TOLERADO",
            "4,false,ATRASO_TOLERADO",
            "6,true,ATRASO_TOLERADO",
            "8,false,ATRASO_TOLERADO",
            "9,true,ATRASO_TOLERADO",
            "10,false,ATRASO_TOLERADO"
    })
    void verificarAtividadeEntregueEstaDentroDoAtrasoTolerado
            (int minutosAtraso, boolean justificativa, String esperado){
        String obtido = EntregaAtividade.
                verificar(minutosAtraso, justificativa);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Entregas com mais de 10 min COM justificativa vão para ANALISE_PROFESSOR")
    @ParameterizedTest(name = "Atraso: {0} min, justificativa: {1} -> {2}")
    @CsvSource({
            "11,true,ANALISE_PROFESSOR",
            "12,true,ANALISE_PROFESSOR",
            "14,true,ANALISE_PROFESSOR",
            "15,true,ANALISE_PROFESSOR",
            "20,true,ANALISE_PROFESSOR",
            "30,true,ANALISE_PROFESSOR"
    })
    void verificarAtividadeEntregueEstaEmAnaliseDoProfessor(int minutosAtraso,
                                                            boolean justificativa,
                                                            String esperado){
        String obtido = EntregaAtividade.verificar(minutosAtraso, justificativa);

        assertEquals(esperado,obtido);
    }

    @DisplayName("Entregas com mais de 10 min SEM justificativa ficam ATRASADA")
    @ParameterizedTest(name = "Atraso: {0} min, justificativa: {1} -> {2}")
    @CsvSource({
            "11,false,ATRASADA",
            "12,false,ATRASADA",
            "15,false,ATRASADA",
            "20,false,ATRASADA",
            "22,false,ATRASADA",
            "30,false,ATRASADA"
    })
    void verificarAtividadeEntregueEstaAtrasada(int minutosAtraso,
                                                boolean justificativa,
                                                String esperado){
        String obtido = EntregaAtividade.verificar(minutosAtraso, justificativa);

        assertEquals(esperado,obtido);
    }
}   