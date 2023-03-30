
package moviewizard;

import java.awt.Color;
import java.io.*;
import javax.imageio.*;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;


public class StickerMaker {
    // O objetivo desta classe é gerar uma imagem.
    
    public void gerar(InputStream inputStream, String fileName) throws Exception {
     
        // ler imagem:
        
        BufferedImage original = ImageIO.read(inputStream);
        
        // cria nova imagem em memória com transparência e novo tamanho.
        int width = original.getWidth();
        int height = original.getHeight();
        
        int newHeight = height + (height/10);
        
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        // copiar, ainda em memória, imagem original para nova.        
        Graphics2D graphics = (Graphics2D)newImage.getGraphics();
        
        graphics.drawImage(original, 0, 0, null);
        
        // criar legenda para nova imagem.
        
        // configurar a fonte usada:
        int tamanhoFonte = Math.floorDiv(width, 30);
        var fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte);
        
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        
        String legenda = "English, motherfucker. Do you speak it?";
        
        var textWidth = graphics.getFontMetrics().stringWidth(legenda);
        
        int alturaTexto = newHeight - (newHeight - height) / 2 + tamanhoFonte / 2;
        
        graphics.drawString(legenda, width/2 - textWidth/2 , alturaTexto);
        
        // escrever nova imagem em um arquivo.
        ImageIO.write(newImage, "png", new File("saida/"+fileName));
        
    }
    
}
