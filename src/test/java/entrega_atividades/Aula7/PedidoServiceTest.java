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

    @Mock
    private PagamentoGateway pagamentoGatewayMock;

    @Mock
    private PedidoRepository pedidoRepositoryMock;

    @Mock
    private EmailService emailServiceMock;

    @Test
    @DisplayName("1. Dummy: Testa recusa de pagamento sem precisar usar o EmailService")
    void testDummy() {
        // Stub do gateway para recusar
        PagamentoGateway gatewayRecusa = (id, valor) -> false;
        PedidoRepository repoIgnorado = new PedidoRepositoryFake();

        // Dummy: passado apenas para preencher o construtor, não é acionado quando o pagamento falha
        EmailService dummyEmail = new DummyEmailService();

        PedidoService service = new PedidoService(gatewayRecusa, repoIgnorado, dummyEmail);
        Pedido pedido = new Pedido("P01", 100.0, "cliente@email.com");

        boolean resultado = service.finalizarPedido(pedido);

        assertFalse(resultado);
        assertFalse(pedido.isPago());
    }

    @Test
    @DisplayName("2. Stub: Resposta fixa para simular Gateway de Pagamento Aprovado")
    void testStub() {
        // Stub manual retornando resposta fixa "true"
        PagamentoGateway stubGatewayAprovado = (id, valor) -> true;

        EmailServiceSpy spyEmail = new EmailServiceSpy();
        PedidoRepositoryFake fakeRepo = new PedidoRepositoryFake();

        PedidoService service = new PedidoService(stubGatewayAprovado, fakeRepo, spyEmail);
        Pedido pedido = new Pedido("P02", 250.0, "teste@email.com");

        boolean resultado = service.finalizarPedido(pedido);

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

        // Verifica se o objeto realmente persistiu no estado da Fake
        Pedido pedidoSalvo = fakeRepo.buscarPorId("P03");
        assertNotNull(pedidoSalvo);
        assertEquals(500.0, pedidoSalvo.getValorTotal());
    }

    @Test
    @DisplayName("4. Spy: Registra se o e-mail foi realmente enviado")
    void testSpy() {
        PagamentoGateway stubGateway = (id, valor) -> true;
        PedidoRepositoryFake fakeRepo = new PedidoRepositoryFake();
        EmailServiceSpy spyEmail = new EmailServiceSpy(); // Spy observador

        PedidoService service = new PedidoService(stubGateway, fakeRepo, spyEmail);
        Pedido pedido = new Pedido("P04", 150.0, "spy@email.com");

        service.finalizarPedido(pedido);

        // Verificação do estado registrado no Spy
        assertEquals(1, spyEmail.getQuantidadeEmailsEnviados());
        assertTrue(spyEmail.contemEmailPara("spy@email.com"));
    }

    @Test
    @DisplayName("5. Mock: Verifica se nenhuma interacao ocorreu se o pagamento falhar")
    void testMockComMockito() {
        // Configuração das expectativas do Mockito
        when(pagamentoGatewayMock.cobrar("P05", 300.0)).thenReturn(false);

        PedidoService service = new PedidoService(pagamentoGatewayMock, pedidoRepositoryMock, emailServiceMock);
        Pedido pedido = new Pedido("P05", 300.0, "mock@email.com");

        boolean resultado = service.finalizarPedido(pedido);

        assertFalse(resultado);
        // Verificação comportamental (Mocks): Garante que NENHUM e-mail foi enviado e NENHUM pedido foi salvo
        verify(pedidoRepositoryMock, never()).salvar(any());
        verify(emailServiceMock, never()).enviarEmail(anyString(), anyString());
    }
}