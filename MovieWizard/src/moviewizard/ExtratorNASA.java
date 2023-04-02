
package moviewizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorNASA implements Extrator {
    
    public List<Conteudo> extraiConteudos (String json) {

        // To break and extract only the data that matters (title, poster, classification, etc), we'll use Regular Expressions:
        var parser = new JSONParser();

        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // Percorrer a lista de atributos para popular a lista de conteúdos:

        for (Map<String, String> atributos : listaDeAtributos) {
            String urlImagem = atributos.get("url");
            String titulo = atributos.get("title");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;

    }
    
}
