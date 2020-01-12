package problema2;

public class Ordenamiento extends Thread {
    int[] arreglo;
    int bajo;
    int alto;
    /*
    Ordenamiento(int[] arreglo, int bajo, int alto)
    Parámetros:
        - int[] arreglo: Arreglo o sub-arreglo con los valores enteros a ordenar.
        - int bajo: Inicio del arreglo o sub-arreglo.
        - int alto: Final del arreglo o sub-arreglo.
    Descripcion: Constructor de la clase Ordenamiento.
    Retorno: No tiene retorno.
    */
    Ordenamiento(int[] arreglo, int bajo, int alto) {
        this.arreglo = arreglo;
        this.bajo = bajo;
        this.alto = alto;
    }
    /*
    public void run()
    Parámetros: No tiene parámetros.
    Descripcion:
        - Encargado de crear las hebras para resolver el problema.
        - Se utiliza un pivote (partición) que va dejando todos los numeros mayores que él a la derecha y los menores a la izquierda.s
        - Con eso se crean dos particiones del arreglo, las cuales serán resueltas por las hebras (una para cada partición) hasta tener sub-arreglos de largo 1 (soluciones triviales).
    Retorno: No tiene retorno, pero modifica el arreglo original.
    */
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