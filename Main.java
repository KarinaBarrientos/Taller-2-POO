import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BufferedImage imagen;
        try {
            imagen = ImageIO.read(new File("download.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el mensaje a ocultar: ");
        String mensaje = teclado.nextLine();

        Mensaje mensaje1 = new Mensaje(mensaje);
        int[][] bits = mensaje1.operacion();

        LSB lsb = new LSB(imagen);
        imagen = lsb.OperacionLSB();

        Ocultarmensaje mensajeoculto = new Ocultarmensaje(imagen,bits);
        mensajeoculto.operacion();

    }
}
