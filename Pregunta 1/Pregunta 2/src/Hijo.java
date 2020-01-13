import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Hijo extends Thread{
	private String funcion;
	private int x;
	public static String resultado = "";
	
	
	/*
	Hijo(String funcion, int x)
    Parámetros:
        - String funcion: La función asociada al Thread.
        - int x: Valor de la variable incógnita x.
    Descripcion:
        - Constructor de la clase Hijo.
    Retorno: No tiene retorno.
    */
	public Hijo(String funcion, int x) {
		this.funcion = funcion;
		this.x = x;
	}
	
	
	/*
    reemFun(String func, int i)
    Parámetros:
        - String func: La ecuación que contiene las funciones anidadas.
        - int i: La posición donde se encuentra la función anidada.
    Descripcion:
        - Busca la función y su valor en la variable estatica "resultado".
    Retorno: Retorna el valor de la función anidada encontrada.
    */
	public int reemFun(String func, int i) {
		int estado = 0;
		for(char c : resultado.toCharArray()) {
			if(estado == 1) return (int)c - 48;
			if(c == func.substring(i,i+1).charAt(0)) {
				estado = 1;
			}
		}
		return 0;
	}
	
	
	/*
    eval(String eq)
    Parámetros:
        - String eq: Ecuación matemática normal (sin funciones anidadas).
    Descripcion:
        - Calcula una ecuación matemática.
    Retorno: Retorna el valor resuelto de la ecuación.
    */
	static int eval(String eq) throws ScriptException {
	    ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = eq;
	    return (int)engine.eval(foo);
	    } 
	
	
	/*
    public void run()
    Parámetros: No tiene parámetros.
    Descripcion:
        - Encargado de crear las hebras para resolver el problema.
        - Reemplaza el x en la ecuación.
        - Posteriormente reemplaza las funciones anidadas (si existen).
        - Luego desarrolla la ecuación como una ecuación matemática normal.
        - Finalmente guarda el desarrollo en la variable estatica "resultado", de la forma funcionvalor (ej: g3).
    Retorno: No tiene retorno, pero modifica el valor de "resultado".
    */
	@Override
    public void run() {
		int i = 0;
        String f1 = funcion.substring(0,1);
        String resto = funcion.substring(5);
        String resto_nuevo = resto;
        
        for(char c : resto.toCharArray()) {//Reemplaza el x en la función
    		if(c == 'x') {
    			resto_nuevo = resto_nuevo.substring(0,i) + (char)(x + '0') + resto_nuevo.substring(i+1);
    		}
    		i++;
        }
        
        i=0;
        for(char c : resto.toCharArray()) {//Busca la función anidada para enviarla a la función "reemFun"
        	if((int)c > 65 && (int)c < 122 && c!= 'x') {
    			resto_nuevo = resto_nuevo.substring(0,i) + (char)(reemFun(resto_nuevo,i) + '0') + resto_nuevo.substring(i+4);
    			i-=3;
        	}
        	i++;
        }

        try{
        	int res = eval(resto_nuevo);
            resultado = resultado + f1+(char)(res + '0');//Agrega el valor de la función como String a la variable estatica "resultado"
            String name = "Hijo 0";
            if(getName().contentEquals(name)) System.out.println("El resultado es: "+(char)(res+'0'));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    
	}
}
