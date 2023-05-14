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

                        int bit = bits[j][i];
                        if (x >= 0 && x < imagen.getWidth() && y >= 0 && y < imagen.getHeight()) {
                            int rgb = imagen.getRGB(x, y);

                            int LSBrojo = ((rgb >> 16 & 254) | bits[0][i]);
                            int LSBverde = ((rgb >> 8 & 254) | (bits[1][i]));
                            int LSBazul = (rgb & 254) | bits[2][i];

                            rgb = (LSBrojo << 16) | (LSBverde << 8) | LSBazul;
                            Color color = Color.getColor(String.valueOf(rgb));

                            imagen.setRGB(x, y, rgb);

                            x++;

                            if (x == imagen.getWidth()) {
                                x = 0;
                                y++;
                            }
                        }
                    }
                }
            }
        }
    }
}
