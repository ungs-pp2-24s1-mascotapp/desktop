package com.mascotapp.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mascotapp.core.MascotApp;
import com.mascotapp.desktop.view.MascotAppView;

public class MascotAppController {
	
	private MascotAppView mascotAppView;
    private MascotApp mascotAppCore;

    public MascotAppController(MascotAppView mascotAppView, MascotApp mascotAppCore) {
        this.mascotAppView = mascotAppView;
        this.mascotAppCore = mascotAppCore;
        
        mascotAppView.setSearchListener(new SearchListener());
        mascotAppCore.addObserver(mascotAppView);
    }
	
    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = mascotAppView.getSearchQuery();
            mascotAppCore.search(query);
        }
    }
}
