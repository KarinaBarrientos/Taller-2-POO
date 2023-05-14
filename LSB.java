import java.awt.image.BufferedImage;

public class LSB {

    BufferedImage imagen;
    int[][] lsba;

    public LSB(BufferedImage imagen) {
        this.imagen = imagen;
        this.lsba = new int[3][imagen.getWidth() * imagen.getHeight()];
    }

    public int[][] OperacionLSB() {
        int i = 0;

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                int rgb = imagen.getRGB(x, y);

                int LSBrojo = ((rgb >> 16) & 255) & 1;
                int LSBverde = ((rgb >> 8) & 255) & 1;
                int LSBazul = (rgb & 255) & 1;

                lsba[0][i] = LSBrojo;
                lsba[1][i] = LSBverde;
                lsba[2][i] = LSBazul;

                i++;
            }
        }

        return lsba;
    }
}
