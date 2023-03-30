import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //var url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        var url = "https://api.nasa.gov/planetary/apod?api_key=SJUGxAJJZdOGWQcHb4urlvgENTxTDZRmEfhSfkeB&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        
        // EXIBIR E MANIPULAR OS DADOS
        List<Conteudo> conteudos = extrator.extraiConteudos(json);


        var geradora = new FabricaDeFigurinhas();

        for (int i = 0; i < 3; i++) {
        
        Conteudo conteudo = conteudos.get(i);
        
            InputStream inputStream = new URL (conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo().trim().replace(":", "").concat(".png");

            geradora.cria(inputStream, nomeArquivo);



            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
        }
    }

