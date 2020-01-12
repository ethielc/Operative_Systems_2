/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2;

/**
 *
 * @author Ethiel
 */
public class Ordenamiento extends Thread {
    int[] arreglo;
    int bajo;
    int alto;
    Ordenamiento(int[] arreglo, int bajo, int alto) {
        this.arreglo = arreglo;
        this.bajo = bajo;
        this.alto = alto;
    }
    public void run(int[] arreglo, int bajo, int alto) {
        int i = bajo;
        int j = alto;
        int aux;
        int particion = arreglo[(bajo + alto)/2];
        while (i < j) {
            while (arreglo[i] > particion) i++;
            while (arreglo[j] < particion) j--;
            if (j >= i) {
                aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
            }
        }
        if (bajo < j) {
            Ordenamiento xd1 = new Ordenamiento(arreglo, bajo, j);
            xd1.start();
        }
        if (i < alto) {
            Ordenamiento xd2 = new Ordenamiento(arreglo, i, alto);
            xd2.start();
        }
    }
}