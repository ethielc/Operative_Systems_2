package problema2;

public class Ordenamiento extends Thread {
    int[] arreglo;
    int bajo;
    int alto;
    Ordenamiento(int[] arreglo, int bajo, int alto) {
        this.arreglo = arreglo;
        this.bajo = bajo;
        this.alto = alto;
    }
    public void run() {
        int i = bajo;
        int j = alto;
        int aux;
        int particion = arreglo[(bajo + alto)/2];
        while (i < j) {
            while (arreglo[j] < particion) j--;
            while (arreglo[i] > particion) i++;
            if (j >= i) {
                aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
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
}