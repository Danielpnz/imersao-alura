import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;


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

        // CRIA NOVA IMAGEM COM A LARGURA ORIGINAL
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // COPIAR A IMAGEM ORIGINAL PARA NOVA IMAGEM (EM MEMORIA)
        Graphics2D graphics =  (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // CONFIGURAR FONTE
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // ESCREVER UMA FRASE NA NOVA IMAGEM
        String text = "TROPZERA";

        // CENTRALIZAR FRASE
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(text, graphics);
        int larguraText = (int) retangulo.getWidth();

        int posicaoX = (largura - larguraText) /2;
        int posicaoY = novaAltura - 100;

        graphics.drawString(text, posicaoX, novaAltura - 100);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, fonte, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoX, posicaoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setColor(Color.BLACK);
        graphics.setStroke(outlineStroke);
        graphics.setClip(outline);
        



        // ESCREVER A NOVA IMAGEM EM UM ARQUIVO
        ImageIO.write(novaImagem, "png", new File ("saida/" + nomeArquivo));

    }
}
