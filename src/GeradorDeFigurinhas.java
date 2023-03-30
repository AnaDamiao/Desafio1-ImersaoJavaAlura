import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // LENDO A IMAGEM
        inputStream = new FileInputStream(new File("entrada/Filme.jpg"));
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // CRIA NOVA IMAGEM EM MEMÓRIA C/ TRANSPARÊNCIA E C/ UM NOVO TAMANH0
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // COPIA A IMAGEM ORIGINAL P/ UMA NOVA IMAGEM(EM MEMÓRIA)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // CONFIGURAÇÃO DE FONTE
        var fonte = new Font("Impact", Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // ESCREVE UMA FRASE NA NOVA IMAGEM
        String texto = "FILMAÇO";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraDoTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraDoTexto) /2;
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

        FontRenderContext contorno = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, contorno);
        
        Shape outline =  textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // ESCREVE A NOVA IMAGEM EM UM ARQUIVO
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));

    }
    //MAIN
    public static void main(String[] args) throws Exception {
        var gerador = new GeradorDeFigurinhas();
        gerador.cria(null, null);
    }
}