import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;


public class FabricaDeFigurinhas {
    

    public void cria (InputStream inputStream, String nomeArquivo) throws IOException{

        // LEITURA DA IMAGEM
            // InputStream inputStream = new FileInputStream(new File ("entrada/filme.jpg"));
            //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg") . openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // CRIA NOVA IMAGEM EM MEMORIA COM TRANSPARENCIA E COM NOVO TAMANHO
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // COPIAR A IMAGEM ORIGINAL PARA NOVA IMAGEM (EM MEMORIA)
        Graphics2D graphics =  (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // CONFIGURAR FONTE
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 86);
        graphics.setColor(Color.YELLOW);

        graphics.setFont(fonte);

        // ESCREVER UMA FRASE NA NOVA IMAGEM
        graphics.drawString("TOPZERA", 150, novaAltura -75);

        // ESCREVER A NOVA IMAGEM EM UM ARQUIVO
        ImageIO.write(novaImagem, "png", new File ("saida/" + nomeArquivo));

    }
}
