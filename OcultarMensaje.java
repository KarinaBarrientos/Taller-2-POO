import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase que realiza la operación de ocultar un mensaje en una imagen utilizando la técnica LSB (Least Significant Bit).
 */
public class OcultarMensaje {
    private BufferedImage imagen;
    private List<List<Integer>> bits;

    /**
     * Constructor de la clase OcultarMensaje.
     *
     * @param imagen La imagen en la cual se ocultará el mensaje.
     * @param bits   Los bits del mensaje a ocultar.
     */
    public OcultarMensaje(BufferedImage imagen, List<List<Integer>> bits) {
        this.imagen = imagen;
        this.bits = bits;
    }

    /**
     * Realiza la operación de ocultar el mensaje en la imagen y guarda la imagen modificada en el archivo de salida.
     *
     * @param outputImagePath La ruta de la imagen de salida.
     * @return La imagen modificada con el mensaje oculto.
     */
    public BufferedImage operacion(String outputImagePath) {
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
                int LSBrojo = ((rgb >> 16) & 254);
                int LSBverde = ((rgb >> 8) & 254);
                int LSBazul = (rgb & 254);

                LSBalpha = LSBalpha | bits.get(0).get(i);
                LSBrojo = LSBrojo | bits.get(1).get(i);
                LSBverde = LSBverde | bits.get(2).get(i);
                LSBazul = LSBazul | bits.get(3).get(i);

                int rgbmod = (LSBalpha << 24) | (LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                imagen.setRGB(x, y, rgbmod);

                i++;
                x++;
            }
        }

        try {
            ImageIO.write(imagen, "png", new File(outputImagePath));
            System.out.println("Imagen modificada guardada correctamente en " + outputImagePath);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen de salida: " + e.getMessage());
        }
        return imagen;
    }
}
