package entrega_atividades.Aula7;

import java.util.ArrayList;
import java.util.List;

// [O QUE É]: DUBLÊ TIPO SPY (Espião).
// [PARA A APRESENTAÇÃO]: "O Spy grava as chamadas que recebe.
// Ele não envia e-mails reais,
// apenas anota em uma lista o que foi enviado para que
// possamos verificar depois no teste."
class EmailServiceSpy implements EmailService {

    // Lista em memória que armazena os registros de e-mails enviados
    private final List<String> emailsEnviados = new ArrayList<>();

    @Override
    public void enviarEmail(String destinatario, String mensagem) {
        // Guarda a informação concatenada na lista
        emailsEnviados.add(destinatario + ":" + mensagem);
    }

    // Método de consulta do Spy: Quantos e-mails foram gravados?
    public int getQuantidadeEmailsEnviados() {
        return emailsEnviados.size();
    }


    // Método de consulta do Spy: Algum e-mail foi para este cliente?
    // [EXPLICAÇÃO TÉCNICA - STREAM E ANYMATCH]:
    // - .stream(): Converte a lista em uma esteira de processamento de dados.
    // - .anyMatch(...): Varre a lista e retorna TRUE se
    // encontrar PELO MENOS UM elemento que cumpra a condição.
    // - e -> e.startsWith(email): Expressão Lambda que checa se o
    // item começa com o e-mail pesquisado.
    public boolean contemEmailPara(String email) {
        return emailsEnviados.stream().anyMatch(e -> e.startsWith(email));
    }
}