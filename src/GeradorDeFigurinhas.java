import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


import javax.imageio.ImageIO;


public class GeradorDeFigurinhas {
    
    public void cria() throws Exception{


        // LEITURA DA IMAGEM
        InputStream inputStream = new FileInputStream(new File("entrada/Filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);


        // CRIA NOVA IMAGEM EM MEMÓRIA COM TRANSPARENCIA E COM O TAMANHO NOVO
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // COPIA A IMAGEM ORIGINAL PARA NOVA IMAGEM (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        
        // ESCREVER UMA FRASE NA NOVA IMAGEM
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        graphics.drawString(texto, posicaoTextoX, novaAltura - 100);

        //CONFIG FONTE
        var fonte = new Font(Font.SERIF, Font.BOLD, 100);
        graphics.setColor(Color.GREEN);
        graphics.setFont(fonte);


        // ESCREVER A NOVA IMAGEM EM UM ARQUIVO
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
    }
    public static void main(String[] args) throws Exception {
        var geradora = new GeradorDeFigurinhas();
        geradora.cria();
    }
}
