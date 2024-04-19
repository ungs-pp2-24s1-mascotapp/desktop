package com.mascotapp.desktop.main;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.initializer.MascotAppInitializer;
import com.mascotapp.desktop.controller.MascotAppController;
import com.mascotapp.desktop.view.MascotAppView;

public class Main {
	
	private final MascotApp mascotApp;
    private final MascotAppView mascotAppView;
    private final MascotAppController mascotAppController;

    public Main(String path) throws FileNotFoundException {
        this.mascotApp = MascotAppInitializer.initializeWithJarFiles(path);
        this.mascotAppView = new MascotAppView();
        this.mascotAppController = new MascotAppController(mascotAppView, mascotApp);
    }

    private void init() {
        SwingUtilities.invokeLater(this.mascotAppView::init);
    }

    public static void main(String[] args) throws FileNotFoundException {
    	if(args.length == 0) {
    		System.out.println("Se necesita especificar la ruta para cargar los plugins");
    		return;
    	}
    		
        new Main(args[0]).init();
    }

}
