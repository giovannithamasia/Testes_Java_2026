package entrega_atividades.Aula7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    // Criamos Mocks do Mockito para uso específico no Teste 5
    @Mock
    private PagamentoGateway pagamentoGatewayMock;

    @Mock
    private PedidoRepository pedidoRepositoryMock;

    @Mock
    private EmailService emailServiceMock;

    @Test
    @DisplayName("1. Dummy: Testa recusa de pagamento sem precisar usar o EmailService")
    void testDummy() {
        // [EXPLICAÇÃO TÉCNICA - LAMBDA (id, valor) -> false]:
        // É um STUB simplificado.
        // Diz: "Não importa o ID ou valor, recuse o pagamento (retorne false)".
        PagamentoGateway gatewayRecusa = (id, valor) -> false;

        PedidoRepository repoIgnorado = new PedidoRepositoryFake();

        // Passamos o DUMMY. Ele só está preenchendo o construtor.
        EmailService dummyEmail = new DummyEmailService();

        PedidoService service = new PedidoService(gatewayRecusa, repoIgnorado, dummyEmail);
        Pedido pedido = new Pedido("P01", 100.0, "cliente@email.com");

        boolean resultado = service.finalizarPedido(pedido);

        // Asserções: O resultado deve ser falso e o pedido continua não pago.
        // Se o sistema tentasse mandar e-mail,
        // o Dummy lançaria erro e o teste quebraria.
        assertFalse(resultado);
        assertFalse(pedido.isPago());
    }

    @Test
    @DisplayName("2. Stub: Resposta fixa para simular Gateway de Pagamento Aprovado")
    void testStub() {
        // STUB MANUAL: Retorna SEMPRE 'true'
        // (Pagamento aprovado) para forçar o caminho de sucesso.
        PagamentoGateway stubGatewayAprovado = (id, valor) -> true;

        EmailServiceSpy spyEmail = new EmailServiceSpy();
        PedidoRepositoryFake fakeRepo = new PedidoRepositoryFake();

        PedidoService service = new PedidoService(stubGatewayAprovado, fakeRepo, spyEmail);
        Pedido pedido = new Pedido("P02", 250.0, "teste@email.com");

        boolean resultado = service.finalizarPedido(pedido);

        // Garante que o fluxo de sucesso funcionou
        assertTrue(resultado);
        assertTrue(pedido.isPago());
    }

    @Test
    @DisplayName("3. Fake: Salva no repositório em memória e recupera o estado")
    void testFake() {
        PagamentoGateway stubGateway = (id, valor) -> true;
        PedidoRepositoryFake fakeRepo = new PedidoRepositoryFake(); // Fake usando HashMap
        EmailServiceSpy spyEmail = new EmailServiceSpy();

        PedidoService service = new PedidoService(stubGateway, fakeRepo, spyEmail);
        Pedido pedido = new Pedido("P03", 500.0, "fake@email.com");

        service.finalizarPedido(pedido);

        // [PARA A APRESENTAÇÃO]: "Validamos o FAKE buscando o pedido no
        // HashMap em memória.
        // Provamos que o repositório realmente salvou o pedido
        // P03 com valor 500.0."
        Pedido pedidoSalvo = fakeRepo.buscarPorId("P03");
        assertNotNull(pedidoSalvo);
        assertEquals(500.0, pedidoSalvo.getValorTotal());
    }

    @Test
    @DisplayName("4. Spy: Registra se o e-mail foi realmente enviado")
    void testSpy() {
        PagamentoGateway stubGateway = (id, valor) -> true;
        PedidoRepositoryFake fakeRepo = new PedidoRepositoryFake();
        EmailServiceSpy spyEmail = new EmailServiceSpy(); // Objeto Espião

        PedidoService service = new PedidoService(stubGateway, fakeRepo, spyEmail);
        Pedido pedido = new Pedido("P04", 150.0, "spy@email.com");

        service.finalizarPedido(pedido);

        // [PARA A APRESENTAÇÃO]: "Consultamos o SPY para confirmar
        // que exatamente 1 e-mail
        // foi registrado na lista interna
        // dele para o endereço 'spy@email.com'."
        assertEquals(1, spyEmail.getQuantidadeEmailsEnviados());
        assertTrue(spyEmail.contemEmailPara("spy@email.com"));
    }

    @Test
    @DisplayName("5. Mock: Verifica se nenhuma interacao ocorreu se o pagamento falhar")
    void testMockComMockito() {
        // [EXPLICAÇÃO TÉCNICA - when(...).thenReturn(...)]:
        // Configura o Mock para agir como um Stub: quando chamar cobrar com estes dados, devolva 'false'.
        when(pagamentoGatewayMock.cobrar("P05", 300.0)).thenReturn(false);

        PedidoService service = new PedidoService(pagamentoGatewayMock, pedidoRepositoryMock, emailServiceMock);
        Pedido pedido = new Pedido("P05", 300.0, "mock@email.com");

        boolean resultado = service.finalizarPedido(pedido);

        assertFalse(resultado);


        // [EXPLICAÇÃO TÉCNICA - verify(), never(), any() e anyString()]:
        // - verify(): Método do Mockito que checa COMPORTAMENTO
        //  (se métodos foram chamados ou não).
        // - never(): Garante que a quantidade de chamadas foi ZERO.
        // - any() e anyString(): Curingas (Matchers).
        // Significam "qualquer objeto" e "qualquer texto".
        // [PARA A APRESENTAÇÃO]: "Aqui o MOCK valida a
        // regra de segurança: como o pagamento falhou,
        // garantimos que o sistema NUNCA tentou salvar no banco e
        // NUNCA tentou enviar e-mail."
        verify(pedidoRepositoryMock, never()).salvar(any());
        verify(emailServiceMock, never()).enviarEmail(anyString(), anyString());
    }
}