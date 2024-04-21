

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
    	if(args.length == 0) {
    		throw new IllegalArgumentException("Se necesita especificar la ruta para cargar los plugins");
    	}
    		
        new Application(args[0]).init();
    }
}
