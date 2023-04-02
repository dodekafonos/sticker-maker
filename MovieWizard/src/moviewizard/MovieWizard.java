
package moviewizard;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.net.URL;


public class MovieWizard {

    // Variáveis de cores para o output no console:
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[1;33m";
    public static final String PURPLE = "\u001B[1;35m";

    public static void main(String[] args) throws Exception {
        
        try {
            // Let's try to retrieve some data from the IMDB API.

            // IMDB:
            // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
//            Extrator extrator = new ExtratorIMDB();

            // NASA:
             String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
             Extrator extrator = new ExtratorNASA();

            var http = new ClienteHTTP();
            String json = http.buscaDados(url);

            List<Conteudo> conteudos = extrator.extraiConteudos(json);

            System.out.println("Esta lista contém "+ conteudos.size()+" conteúdos:\n");
            
            for (int i = 0; i < 3; i++) {

                Conteudo conteudo = conteudos.get(i);
                InputStream inputStream = new URL(conteudo.getImagem()).openStream();
                String nomeArquivo = conteudo.getTitulo() + ".png";

                var gerador = new StickerMaker();

                gerador.gerar(inputStream, nomeArquivo);
                System.out.println(PURPLE + conteudo.getTitulo() + RESET);
                System.out.println(conteudo.getImagem());
                System.out.println("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MovieWizard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieWizard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
