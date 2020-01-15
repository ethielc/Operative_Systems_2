import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;  


public class Padre{
	
	/*
	List<String> Leertexto()
    Parametros: No tiene parametros.
    Descripcion:
        - Crea una lista del texto "funciones.txt".
    Retorno: Retorna la lista.
    */
	static List<String> Leertexto() {
		List<String> list = new ArrayList<String>();
        File file = new File("funciones.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return list;
	}

	public static void main(String[] args) {
		List<String> texto = new ArrayList<String>();
		List<Hijo> Lista_hijos = new ArrayList<Hijo>();
		texto = Leertexto();
		
		int n = Integer.parseInt(texto.get(0));
		System.out.println("Funciones ingresadas!");
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese valor para x: ");
        int x = input.nextInt();
        input.close();
        
        for(int i = 0; i<n ; i++) {
            Hijo child= new Hijo(texto.get(i+1),x);
            child.setName("Hijo "+i);
            Lista_hijos.add(child);
        }
        for(int i=n-1; i>-1; i--) {
            Lista_hijos.get(i).start();
            try {
            	for(int j=n-1;j>i-1;j--) {
            		Lista_hijos.get(j).join();            		
            	}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
	}

}
