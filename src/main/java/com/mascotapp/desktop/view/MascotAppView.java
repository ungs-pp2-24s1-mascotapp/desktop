package com.mascotapp.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.entities.Match;
import com.mascotapp.core.entities.Post;
import com.mascotapp.desktop.controller.MascotAppController;

@SuppressWarnings("serial")
public class MascotAppView extends JFrame implements Observer {
	
	private final String titleApp = "MascotApp";
	private final int windowSizeWidth = 640;
	private final int windowSizeHeight = 480;
	
	JTextField searchField;
	JButton searchButton;
	
    private JList<String> resultList;
    private DefaultListModel<String> listModel;

    public MascotAppView(MascotApp core) {
        this.initializeWindow();
        new MascotAppController(this, core);
    }


    public void init() {
        this.setVisible(true);
    }
    
    private void initializeWindow() {
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
        
        //JLabel searchLabel = new JLabel("Buscar mascotas:");
        //searchPanel.add(searchLabel, BorderLayout.WEST);

        searchField = new JTextField();
        //searchPanel.add(searchField, BorderLayout.CENTER);

        searchButton = new JButton("Buscar Mascotas");
        searchPanel.add(searchButton, BorderLayout.EAST);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(resultList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void centerWindow() {
    	setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void update(Observable o, Object arg) {
        Set<Match> results = (Set<Match>) arg;
        setResults(results);
    }
    
    public void setResults(Set<Match> results) {
        listModel.clear();
        for (Match result : results) {
        	listModel.addElement(getFullText(result));
        }
    }
    
    private String getFullText(Match match) {
    	return "Post Mascota Perdida [" + getPostText(match.getLostPet()) + "]" + ", Post Mascota Encontrada [" + getPostText(match.getFoundPet()) + "]";
    }
    
    private String getPostText(Post post) {
    	return post.getContent() + ":" + post.getUrl();
    }
    
    public String getSearchQuery() {
        return searchField.getText();
    }

	public void setSearchListener(ActionListener searchListener) {
		searchButton.addActionListener(searchListener);
	}
}
