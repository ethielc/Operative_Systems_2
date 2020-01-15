package problema2;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Problema2 {
    public static void main(final String[] args) {
        
        final Scanner input = new Scanner(System.in);
        System.out.println("Inserte la cantidad de numeros que tendra el arreglo: ");
        final int largo = input.nextInt();
        int pos = 0;
        final int[] arreglo = new int[largo];
        System.out.println("Inserte los valores uno por uno: ");
        while (pos < largo) {
            arreglo[pos] = input.nextInt();
            pos++;
        }
        System.out.print("Su arreglo es: [");
        System.out.print(arreglo[0]);
        for (pos = 1; pos < largo; pos++) {
            System.out.print(",");
            System.out.print(arreglo[pos]);
        }
        System.out.print("]\n");
        final Ordenamiento xd = new Ordenamiento(arreglo, 0, largo - 1);
        xd.start();
        try {
            xd.join();
        } catch (final InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.print("Su arreglo ordenado descendentemente es: [");
        System.out.print(arreglo[0]);
        for (pos = 1; pos < largo; pos++) {
            System.out.print(",");
            System.out.print(arreglo[pos]);
        }
        System.out.print("]\n");
        input.close();
    }
}