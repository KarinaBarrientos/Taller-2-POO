import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LSB {

    BufferedImage imagenconsecreto;

    {
        try {
            imagenconsecreto = ImageIO.read(new File("imagenconsecreto.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage OperacionLSB() {
        int i = 0;

        System.out.println("descifrar");

        for (int y = 0; y < imagenconsecreto.getHeight(); y++) {
            for (int x = 0; x < imagenconsecreto.getWidth(); ) {

                int rgb = imagenconsecreto.getRGB(x, y);


                System.out.println(imagenconsecreto.getRGB(0, 0));
                System.out.println(imagenconsecreto.getRGB(1, 0));


                int LSBalpha = ((rgb >> 24) & 1);
                int LSBrojo = ((rgb >> 16) & 1);
                int LSBverde = ((rgb >> 8) & 1);
                int LSBazul = (rgb & 1);

                i++;
                x++;

            }
        }
        return imagenconsecreto;
    }

    void armadoDeMensaje() {


    }
}
