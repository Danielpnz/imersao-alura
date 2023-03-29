import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // FAZER CONEXÃO HTTP E BUSCAR TOP 250 FILMES 
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // EXTRAIR DADOS IMPORTANTE (TITULO , POSTER E CLASSIFICAÇÃO)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body); 

        // EXIBIR E MANIPULAR OS DADOS

        var geradora = new FabricaDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL (urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            

            System.out.println(filme.get(titulo));
            System.out.println();

        }
    }
}
