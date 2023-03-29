
package moviewizard;

import java.io.IOException;
import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.*;
import java.util.*;
import java.util.logging.*;
import java.net.URL;


public class MovieWizard {
    
    // Foreground colors:
    
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[1;30m";
    public static final String RED = "\u001B[1;31m";
    public static final String GREEN = "\u001B[1;32m";
    public static final String YELLOW = "\u001B[1;33m";
    public static final String BLUE = "\u001B[1;34m";
    public static final String PURPLE = "\u001B[1;35m";
    public static final String CYAN = "\u001B[1;36m";
    public static final String WHITE = "\u001B[1;37m";
    
    // Background colors:
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws Exception {
        
        try {
            // Let's try to retrieve some data from the IMDB API.
            String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
            // We're using an alternative address as the IMDB API is unstable.
            
            URI endereco = URI.create(url);
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
            String body = response.body();
            
            // To break and extract only the data that matters (title, poster, classification, etc), we'll use Regular Expressions:
            var parser = new JSONParser();
            
            List<Map<String, String>> listaDeFilmes = parser.parse(body);
            
            System.out.println("Esta lista contém "+listaDeFilmes.size()+" filmes:\n");
            
            for (Map<String, String> filme : listaDeFilmes) {
                
                String urlImagem = filme.get("image");
                String titulo = filme.get("title");
                String nomeArquivo = titulo + ".png";
                
                InputStream inputStream = new URL(urlImagem).openStream();
                
                var gerador = new StickerMaker();
                gerador.gerar(inputStream, nomeArquivo);
                
                
                System.out.println(PURPLE + filme.get("title") + RESET);
                
                System.out.println(filme.get("image"));
                
                // Converting numeric (double) rating to rounded integer to print little stars:
                double ratingDouble = Double.parseDouble(filme.get("imDbRating"));
                double ratingFloor = Math.floor(ratingDouble);
                int rating = (int)ratingFloor;
                
                System.out.println(YELLOW + "★ ".repeat(rating) + "☆ ".repeat(10-rating) + RESET);
                
                System.out.println("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MovieWizard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieWizard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
