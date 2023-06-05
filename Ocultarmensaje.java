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

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {

                int yo = bits.length;

                if (i > bits.length) {

                }

                int rgb = imagen.getRGB(x, y);

                int LSBalpha = ((rgb >> 24 & 254));
                int LSBrojo = ((rgb >> 16 & 254));
                int LSBverde = ((rgb >> 8 & 254));
                int LSBazul = ((rgb & 254));

                LSBalpha = LSBalpha | bits[0][i];
                LSBrojo = LSBrojo | bits[1][i];
                LSBverde = LSBverde | bits[2][i];
                LSBazul = LSBazul | bits[3][i];


                System.out.println("ii" + bits[0][i]);
                System.out.println("ii" + bits[1][i]);
                System.out.println("ii" + bits[2][i]);
                System.out.println("ii" + bits[3][i]);

                System.out.println(bits[0][i]);
                System.out.println(bits[1][i]);
                System.out.println(bits[2][i]);
                System.out.println(bits[3][i]);

                System.out.println(bits[0][i]);
                System.out.println(bits[1][i]);
                System.out.println(bits[2][i]);
                System.out.println(bits[3][i]);

                System.out.println(Integer.toBinaryString(LSBalpha));
                System.out.println(Integer.toBinaryString(LSBrojo));
                System.out.println(Integer.toBinaryString(LSBverde));
                System.out.println(Integer.toBinaryString(LSBazul));


                rgb = (LSBalpha << 24 | LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                imagen.setRGB(x, y, rgb);

                System.out.println("i: " + i);

                i = i + 1;


            }
        }

        File imagenconsecreto = new File("imagenconsecreto.png");
        try {
            ImageIO.write(imagen, "png", imagenconsecreto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
