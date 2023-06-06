import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ocultarmensaje {
    BufferedImage imagen;
    int[][] bits;

    public Ocultarmensaje(BufferedImage imagen, int[][] bits) {
        this.imagen = imagen;
        this.bits = bits;
    }

    public void operacion() {
        int i = 0;

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); ) {

                if (i >= bits[0].length) {
                    break;
                }

                int rgb = imagen.getRGB(x, y);

                System.out.println("rgb original: " + Integer.toBinaryString(rgb));

                int LSBalpha = ((rgb >> 24 & 254));
                int LSBrojo =  ((rgb >> 16 & 254));
                int LSBverde = ((rgb >> 8 & 254));
                int LSBazul =  ((rgb & 254));

                LSBalpha = LSBalpha | bits[0][i];
                LSBrojo  = LSBrojo  | bits[1][i];
                LSBverde = LSBverde | bits[2][i];
                LSBazul  = LSBazul  | bits[3][i];

                /*

                System.out.println(bits[0][i]);
                System.out.println(bits[1][i]);
                System.out.println(bits[2][i]);
                System.out.println(bits[3][i]);

                System.out.println("byte" + Integer.toBinaryString(LSBalpha));
                System.out.println("byte" + Integer.toBinaryString(LSBrojo));
                System.out.println("byte" + Integer.toBinaryString(LSBverde));
                System.out.println("byte" + Integer.toBinaryString(LSBazul));

                 */

                int rgbmod = (LSBalpha << 24 | LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                imagen.setRGB(x,y,rgbmod);

                System.out.println("i: " + i);

                i = i + 1;
                x++;

            }
        }

        File archivoSalida = new File("imagenconsecreto.png");
        try {
            ImageIO.write(imagen, "png", archivoSalida);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Imagen modificada guardada correctamente.");
    }
}
