package entrega_atividades.revisao2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ContaBancariaTest {

    private ContaBancaria contaBancaria;

    @BeforeEach
    void configurarCadaTeste(){
        contaBancaria = new ContaBancaria("Paulo");
    }

    @Test
    void saldoInicialDeveSerCemReais(){
        assertEquals(100.00,contaBancaria.getSaldo(),0.001);
    }

    @Test
    void saldoAtualizadoAposDeposito(){
        contaBancaria.depositar(50.00);

        assertEquals(150.00,contaBancaria.getSaldo(),0.001);
    }

    @Test
    void saldoAtualizadoAposSaque(){
        contaBancaria.sacar(30.00);

        assertEquals(70.00,contaBancaria.getSaldo(),0.001);
    }

    @Test
    void conferirSaldoAposTentativaDeSaqueInvalido(){
        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> contaBancaria.sacar(200.00)
        );

        assertEquals("Saldo insuficiente",excecao.getMessage());

        assertEquals(100.00,contaBancaria.getSaldo(),0.001);
    }
}
