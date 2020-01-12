import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Hijo extends Thread{
	private String funcion;
	private int x;
	public static String resultado = "";
	
	public Hijo(String funcion, int x) {
		this.funcion = funcion;
		this.x = x;
	}
	
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
	
	static int eval(String eq) throws ScriptException {
	    ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = eq;
	    return (int)engine.eval(foo);
	    } 
		
	@Override
    public void run() {
		int i = 0;
        String f1 = funcion.substring(0,1);
        String resto = funcion.substring(5);
        String resto_nuevo = resto;
        
        for(char c : resto.toCharArray()) {
    		if(c == 'x') {
    			resto_nuevo = resto_nuevo.substring(0,i) + (char)(x + '0') + resto_nuevo.substring(i+1);
    		}
    		i++;
        }
        
        i=0;
        for(char c : resto.toCharArray()) {
        	if((int)c > 65 && (int)c < 122 && c!= 'x') {
    			resto_nuevo = resto_nuevo.substring(0,i) + (char)(reemFun(resto_nuevo,i) + '0') + resto_nuevo.substring(i+4);
    			i-=3;
        	}
        	i++;
        }

        try{
        	int res = eval(resto_nuevo);
            resultado = resultado + f1+(char)(res + '0');
            String name = "Hijo 0";
            if(getName().contentEquals(name)) System.out.println("El resultado es: "+(char)(res+'0'));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    
	}
}
