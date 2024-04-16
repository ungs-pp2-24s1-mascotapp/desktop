package com.mascotapp.desktop.main;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.MascotAppFactory;
import com.mascotapp.desktop.controller.MascotAppController;
import com.mascotapp.desktop.view.MascotAppView;

public class Main {
	
	private final MascotApp mascotAppCore;
    private final MascotAppView mascotAppView;
    private final MascotAppController mascotAppController;

    public Main() throws FileNotFoundException {
        this.mascotAppCore = MascotAppFactory.createMascotApp();
        this.mascotAppView = new MascotAppView();
        this.mascotAppController = new MascotAppController(mascotAppView, mascotAppCore);
    }

    private void init() {
        SwingUtilities.invokeLater(this.mascotAppView::init);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().init();
    }

}
