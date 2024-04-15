/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.mascotapp.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mascotapp.desktop.controller.MascotAppController;
import com.mascotapp.desktop.model.MascotAppModel;

public class MascotAppView extends JFrame implements Observer {
	
	private final String titleApp = "MascotApp";
	private final int windowSizeWidth = 640;
	private final int windowSizeHeight = 480;
	
	JTextField searchField;
	JButton searchButton;
	
    private JList<String> resultList;
    private DefaultListModel<String> listModel;

    MascotAppModel mascotAppCore;
    MascotAppController mascotAppController;

    public MascotAppView() {
        this.initialize();
    }

    private void initialize() {
    	initWindow();       
    }

    public void init() {
        this.setVisible(true);
    }
    
    private void initWindow() {
    	setTitle(titleApp);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(windowSizeWidth, windowSizeHeight));
        pack();
        centerWindow();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        JLabel searchLabel = new JLabel("Buscar mascota:");
        searchPanel.add(searchLabel, BorderLayout.WEST);

        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);

        searchButton = new JButton("Buscar");
        searchPanel.add(searchButton, BorderLayout.EAST);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(resultList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void centerWindow() {
    	setLocationRelativeTo(null);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        List<String> results = (List<String>) arg;
        setResults(results);
    }
    
    public void setResults(List<String> results) {
        listModel.clear();
        for (String result : results) {
            listModel.addElement(result);
        }
    }
    
    public String getSearchQuery() {
        return searchField.getText();
    }

	public void setSearchListener(ActionListener searchListener) {
		searchButton.addActionListener(searchListener);
	}
}
