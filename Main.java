import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(new File("download.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LSB lsb = new LSB(imagen);
        int[][] lsba = lsb.OperacionLSB();

        for (int i = 0; i < lsba.length; i++) {
            for (int j = 0; j < lsba[i].length; j++) {
                System.out.println(lsba[i][j]);
            }
        }
    }
}
