package com.mascotapp.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mascotapp.desktop.model.MascotAppModel;
import com.mascotapp.desktop.view.MascotAppView;

public class MascotAppController {
	
	private MascotAppView mascotAppView;
    private MascotAppModel mascotAppCore;

    public MascotAppController(MascotAppView mascotAppView, MascotAppModel mascotAppCore) {
        this.mascotAppView = mascotAppView;
        this.mascotAppCore = mascotAppCore;
        
        mascotAppView.setSearchListener(new SearchListener());
        mascotAppCore.addObserver(mascotAppView);
    }
	
    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = mascotAppView.getSearchQuery();
            mascotAppCore.searchPets(query);
        }
    }
}
