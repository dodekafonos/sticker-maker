
package moviewizard;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class ClienteHTTP {

    public String buscaDados(String url) {

        try {
        
            URI endereco = URI.create(url);
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();    
        
        } catch(IOException | InterruptedException e) {
            
            throw new RuntimeException(e);
            
        }
    }
    
}
