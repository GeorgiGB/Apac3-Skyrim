package AD.APAC3;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {

// PERSONALES

    public static void Menu() {
        espacioVacio(10);
        linia(60);
        System.out.println("|----------      APAC 3. SKYRIM DATABASE AC      ----------|");
        linia(60);

        liniaart(45);
        System.out.println("Tablas : Jugadores | Facciones | Clases | Razas ");
        liniaart(45);

        System.out.println("Opciones ");
        Utilidades.linia(15);
        System.out.println("help ");
        System.out.println("quit ");
        System.out.println("show [-c] table");
        System.out.println("add table");
        System.out.println("update table ");
        System.out.println("delete table ");
    }

// FER

    public static void linia(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();

    }

    public static void liniadoble(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("=");
        System.out.println();

    }

    public static void liniaart(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("*");
        System.out.println();

    }

    public static boolean isNoZero(int n) {
        return n != 0;
    }

// LEER

    private final static BufferedReader entradaConsola = new BufferedReader(new java.io.InputStreamReader(System.in));

    public static String leerTexto(String mensaje) {
        String respuesta;
        do {
            try {
                System.out.print(mensaje);
                respuesta = entradaConsola.readLine();
            } catch (IOException ex) {
                return "";
            }
        } while (respuesta == null);
        return respuesta;
    }

    public static int leerEntero(String mensaje) {
        int n = 0;
        boolean correcto = false;
        while (!correcto) {
            try {
                n = Integer.parseInt(leerTexto(mensaje));
                correcto = true;
            } catch (NumberFormatException ex) {
                System.out.println("Tienes que introducir un número correcto");
            }
        }
        return n;
    }

    public static double leerDouble(String mensaje) {
        double n = 0.0;
        boolean correcto = false;
        while (!correcto) {
            try {
                n = Double.parseDouble(leerTexto(mensaje));
                correcto = true;
            } catch (NumberFormatException ex) {
                System.out.println("Tienes que introducir un número correcto");
            }
        }
        return n;
    }

    public static float leerFloat(String mensaje) {
        float n = 0;
        boolean correcto = false;
        while (!correcto) {
            try {
                n = Float.parseFloat(leerTexto(mensaje));
                correcto = true;
            } catch (NumberFormatException ex) {
                System.out.println("Tienes que introducir un número correcto");
            }
        }
        return n;
    }

    public static Date leerFecha(String mensaje) {
        Date fecha = new Date();
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy"); // Crear un format de data
        boolean correcto = false;
        while (!correcto) {
            try {
                String entrada = leerTexto("Format DD/MM/YYYY: "); // Entrada
                fecha = formatar.parse(entrada); // Creem una data amb la entrada en el format indicat
                correcto = true;
            } catch (NumberFormatException | ParseException ex) {
                System.out.println("Tienes que introducir una fecha correcta");
            }
        }
        return fecha;
    }

    public static boolean leerBoolean(String mensaje) {
        boolean n;
        n = Boolean.parseBoolean(leerTexto(mensaje));
        // Retornarà true si s'introdueix "true" o "TRUE" i false en qualsevol altre cas
        return n;
    }

    public static Long leerLong(String mensaje) {
        Long n = Long.valueOf(0);
        boolean existir = false;
        while (!existir) {
            try {
                n = Long.parseLong(leerTexto(mensaje));
                existir = true;
            } catch (NumberFormatException ex) {
                System.out.println("Indique una ID existente.");
            }
        }
        return n;
    }

    public static void espacioVacio(Integer n){
        for (int i = 0; i < n; i++) {
            System.out.println("");
        }
    }
}
