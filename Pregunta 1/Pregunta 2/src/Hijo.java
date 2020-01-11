import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Hijo extends Thread{
	private String funcion;
	private int x;
	
	public Hijo(String funcion, int x) {
		this.funcion = funcion;
		this.x = x;
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
        System.out.println("Child thread name: "+ getName()+" /funcion: "+funcion); 
        String f1 = funcion.substring(0,4);
        String resto = funcion.substring(5);
        String resto_nuevo = resto;
        /*for(char c : resto.toCharArray()) {
        	if(Character.isDigit(c)) {
        		if(estado == "int") {
        			numero = numero + c;
        		}
        		else numero = "" + c;
        		estado = "int";
        	}
        }
         ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String infix = "3+2*(4+5)";
        System.out.println(engine.eval(infix));
        *
        *
        *
        */
        
        for(char c : resto.toCharArray()) {
    		if(c == 'x') {
    			resto_nuevo = resto_nuevo.substring(0,i) + (char)(x + '0') + resto_nuevo.substring(i+1);
    		}
    		i++;
        }
        
        System.out.println(resto_nuevo);
        
        try{
            System.out.println(eval(resto_nuevo));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    
	}
}
