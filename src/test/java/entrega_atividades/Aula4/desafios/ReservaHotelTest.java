package entrega_atividades.Aula4.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    void reservaRecemCriadaDeveTerDadosEEstadoInicialCorretos() {
        ReservaHotel reserva = new ReservaHotel(
                "Ana Silva",
                3,
                250.0
        );

        assertAll(
                () -> assertEquals(
                        "Ana Silva",
                        reserva.getHospede()
                ),
                () -> assertEquals(
                        3,
                        reserva.getQuantidadeDiarias()
                ),
                () -> assertEquals(
                        250.0,
                        reserva.getValorDiaria(),
                        0.001
                ),
                () -> assertFalse(reserva.isConfirmada()),
                () -> assertNull(reserva.getCodigoConfirmacao())
        );
    }

    @Test
    void calcularTotalDeveMultiplicarDiariasPeloValor() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel(
                "Bruno Costa",
                4,
                180.0
        );

        // Act
        double obtido = reserva.calcularTotal();

        // Assert
        assertEquals(720.0, obtido, 0.001);
    }

    @Test
    void confirmarDeveAlterarEstadoEArmazenarCodigo() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel(
                "Carla Souza",
                2,
                300.0
        );

        // Act
        reserva.confirmar("RES-2026-001");

        // Assert
        assertAll(
                () -> assertTrue(reserva.isConfirmada()),
                () -> assertNotNull(
                        reserva.getCodigoConfirmacao()
                ),
                () -> assertEquals(
                        "RES-2026-001",
                        reserva.getCodigoConfirmacao()
                )
        );
    }

    @Test
    void hospedeNuloDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel(null, 2, 200.0)
        );

        assertEquals(
                "O hóspede é obrigatório.",
                excecao.getMessage()
        );
    }

    @Test
    void hospedeEmBrancoDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("   ", 2, 200.0)
        );

        assertEquals(
                "O hóspede é obrigatório.",
                excecao.getMessage()
        );
    }

    @Test
    void quantidadeZeroDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Diego", 0, 200.0)
        );

        assertEquals(
                "A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage()
        );
    }

    @Test
    void quantidadeNegativaDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Diego", -1, 200.0)
        );

        assertEquals(
                "A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage()
        );
    }

    @Test
    void valorZeroDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Eva", 2, 0.0)
        );

        assertEquals(
                "O valor da diária deve ser maior que zero.",
                excecao.getMessage()
        );
    }

    @Test
    void valorNegativoDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Eva", 2, -1.0)
        );

        assertEquals(
                "O valor da diária deve ser maior que zero.",
                excecao.getMessage()
        );
    }

    @Test
    void codigoNuloDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel(
                "Fábio",
                2,
                150.0
        );

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar(null)
        );

        assertEquals(
                "O código de confirmação é obrigatório.",
                excecao.getMessage()
        );
    }

    @Test
    void codigoEmBrancoDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel(
                "Fábio",
                2,
                150.0
        );

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar("   ")
        );

        assertEquals(
                "O código de confirmação é obrigatório.",
                excecao.getMessage()
        );
    }

    @Test
    void confirmarDuasVezesDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel(
                "Gabriela",
                5,
                220.0
        );
        reserva.confirmar("RES-001");

        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> reserva.confirmar("RES-002")
        );

        assertAll(
                () -> assertEquals(
                        "A reserva já está confirmada.",
                        excecao.getMessage()
                ),
                () -> assertEquals(
                        "RES-001",
                        reserva.getCodigoConfirmacao()
                )
        );
    }
}
