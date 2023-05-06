import java.util.ArrayList;
import java.io.*;
public class Textoenimagen {
    public static void main(String[] args) {
        ArrayList<Integer> datosBytes = new ArrayList<>();

        try {

            FileInputStream archivo_lectura = new FileInputStream ("C:/Users/pc/Documents/NetBeansProjects/LeerImagen/src/main/java/Imagen/murtapng.png");

            boolean final_ar = false;

            while (!final_ar) {
                int byte_entrada = archivo_lectura.read();

                if (byte_entrada != -1) {

                    datosBytes.add(byte_entrada);
                }

                else {
                    final_ar = true;
                }
            }
            System.out.println(datosBytes);

            archivo_lectura.close();
        }
        catch (IOException e){}

    }
}
