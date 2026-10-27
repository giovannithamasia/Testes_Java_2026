package entrega_atividades.revisao2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

     private static List<String> livros;

     @BeforeAll
      static void configurarCadaTeste(){
         livros = new ArrayList<>();
         livros.add("Dom Casmurro");
         livros.add("O Pequeno Príncipe");
         livros.add("1984");

         System.out.println("Biblioteca preparada");
     }

     @Test
     void deveVerificarSeAListaPossuiTresLivros(){
         assertEquals(3,livros.size());

         System.out.println("Teste: quantidade de produtos.");
     }

     @Test
     void deveVerificarNaListaSePossuiOLivro1984(){
         assertTrue(livros.contains("1984"));

         System.out.println("Teste: presença do livro 1984.");
     }

     @Test
     void deveVerificarNaListaSeNaoPossuiUmLivro(){
         assertFalse(livros.contains("Livro inexistente"));

         System.out.println("Teste: não existe Livro inexistente");
     }

     @AfterAll
     static void limparAListaExibirMensagem(){
         livros.clear();

         System.out.println("Biblioteca encerrada");
     }
}
