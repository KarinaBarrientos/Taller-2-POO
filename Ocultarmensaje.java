import java.awt.*;
import java.awt.image.BufferedImage;

public class Ocultarmensaje {
    BufferedImage imagen;
    int[][] bits;

    public Ocultarmensaje(BufferedImage imagen, int[][] bits) {
        this.imagen = imagen;
        this.bits = bits;
    }

    public void operacion() {

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                for (int i = 0; i < bits.length; i++) {
                    for (int j = 0; j < bits[i].length; j++) {

                        int bit = bits[i][j];
                        int rgb = imagen.getRGB(x, y);
                        int LSBrojo = ((rgb >> 16) & 254) | bit;
                        int LSBverde = ((rgb >> 8) & 254) | bit;
                        int LSBazul = (rgb & 254) | bit;

                        rgb = (LSBrojo << 16) | (LSBverde << 8) | LSBazul;
                        Color color = Color.getColor(String.valueOf(rgb));
                        imagen.setRGB(x, y, rgb);

                        System.out.println(bits[i][j]);
                        System.out.println("rojo" + Integer.toBinaryString(LSBrojo));
                        System.out.println("verde" + Integer.toBinaryString(LSBverde));
                        System.out.println("azul" + Integer.toBinaryString(LSBazul));

                    }
                }
            }
        }
    }
}
