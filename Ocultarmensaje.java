import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Ocultarmensaje {
    BufferedImage imagen;
    List<List<Integer>> bits;

    public Ocultarmensaje(BufferedImage imagen, List<List<Integer>> bits) {
        this.imagen = imagen;
        this.bits = bits;
    }

    public void operacion() {
        int i = 0;

        int transparencia = imagen.getTransparency();

        if (transparencia == BufferedImage.TRANSLUCENT || transparencia == BufferedImage.BITMASK) {
            System.out.println("La imagen tiene transparencia.");
        } else {
            BufferedImage imagenConAlpha = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_ARGB);
            imagenConAlpha.getGraphics().drawImage(imagen, 0, 0, null);
            imagen = imagenConAlpha;
        }

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); ) {
                if (i >= bits.get(0).size()) {
                    break;
                }

                int rgb = imagen.getRGB(x, y);

                int LSBalpha = ((rgb >> 24) & 254);
                int LSBrojo  = ((rgb >> 16) & 254);
                int LSBverde = ((rgb >> 8) & 254);
                int LSBazul  = (rgb & 254);

                LSBalpha = LSBalpha | bits.get(0).get(i);
                LSBrojo  = LSBrojo  | bits.get(1).get(i);
                LSBverde = LSBverde | bits.get(2).get(i);
                LSBazul  = LSBazul  | bits.get(3).get(i);

                int rgbmod = (LSBalpha << 24) | (LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                imagen.setRGB(x, y, rgbmod);

                i++;
                x++;
            }
        }

        File archivoSalida = new File("imagenconsecreto.png");
        try {
            ImageIO.write(imagen, "png", archivoSalida);
            System.out.println("Imagen modificada guardada correctamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
