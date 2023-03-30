import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoNasa implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){

        // EXTRAIR DADOS IMPORTANTE (TITULO , POSTER E CLASSIFICAÇÃO)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json); 

        List<Conteudo> conteudos = new ArrayList<>();
        
        // POPULAR A LISTA DE CONTEUDOS
        for (Map<String, String> atributos : listaDeAtributos){
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
        

    }
}
