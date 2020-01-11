import java.io.*;
import java.util.ArrayList;
import java.util.List;  


public class Padre{
	
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
		int x = 1;

		//String funcion = texto.get(1);
        //Thread father = Thread.currentThread(); 
        //father.setName("Padre");
        //System.out.println("Father thread name: " + father.getName()+" /funcion: "+funcion);
        
        for(int i = 0; i<n ; i++) {
            Hijo child= new Hijo(texto.get(i+1),x);
            child.setName("Hijo "+i);
            Lista_hijos.add(child);
        }
        //System.out.println(n);
        for(int i=n-1; i>-1; i--) {
        	//System.out.println(Lista_hijos.get(i).getName());
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
