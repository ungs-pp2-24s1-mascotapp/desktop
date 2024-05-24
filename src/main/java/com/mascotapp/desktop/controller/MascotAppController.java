package com.mascotapp.desktop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBoxMenuItem;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.service.socialNetwork.SocialNetworkInfo;
import com.mascotapp.desktop.view.MascotAppView;

public class MascotAppController {
	
	private MascotAppView mascotAppView;
    private MascotApp mascotAppCore;
    private boolean isSearching;

    @SuppressWarnings("deprecation")
	public MascotAppController(MascotAppView mascotAppView, MascotApp mascotAppCore) {
        this.mascotAppView = mascotAppView;
        this.mascotAppCore = mascotAppCore;
        
        mascotAppView.setSearchListener(new SearchListener());
        mascotAppCore.addObserver(mascotAppView);
        
        Set<SocialNetworkInfo> socialNetworks = mascotAppCore.getSocialNetworks();
        mascotAppView.addMenu(socialNetworks, new MenuActionListener());
    }
	
    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (!isSearching) {
        		mascotAppCore.startService(0, 5, TimeUnit.SECONDS);
        	} else {
        		mascotAppCore.stopService();
        	}
        	isSearching = !isSearching;
        	mascotAppView.changeButtonText(isSearching);
        }
    }
    
    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
            String option = source.getText();
            boolean isSelected = source.isSelected();
            if(isSelected) mascotAppCore.activateSocialNetwork(option);
            else mascotAppCore.deactivateSocialNetwork(option);
        }
    }
}
