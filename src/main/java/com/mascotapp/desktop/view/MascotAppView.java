package com.mascotapp.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.entities.Match;
import com.mascotapp.core.entities.Post;
import com.mascotapp.desktop.controller.MascotAppController;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class MascotAppView extends JFrame implements Observer {
	
	private final String titleApp = "MascotApp";
	private final int windowSizeWidth = 1024;
	private final int windowSizeHeight = 600;
	
	JTextField searchField;
	JButton searchButton;
    private DefaultListModel<String> listModel;
    
    JLabel lblEncontrado;
    JPanel resultados;
    
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
        getContentPane().add(mainPanel);
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        //JLabel searchLabel = new JLabel("Buscar mascotas:");
        //searchPanel.add(searchLabel, BorderLayout.WEST);

        searchField = new JTextField();
        //searchPanel.add(searchField, BorderLayout.CENTER);

        searchButton = new JButton("Buscar Mascotas");
        searchPanel.add(searchButton, BorderLayout.EAST);
        	
        resultados = new JPanel();
        mainPanel.add(resultados, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
      
        listModel = new DefaultListModel<>();
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
        resultados.setLayout(new GridLayout(results.size(), 3, 20, 20));
       
        for (Match result: results) {
        	System.out.println(result);
        	displayItem(result);
        	//listModel.addElement(getFullText(result));
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
	
	public void displayItem(Match item) {
        JPanel lostCard = new JPanel();
        lostCard.setLayout(new BorderLayout());
		JLabel lblLost = new JLabel();
		JButton btnLost = new JButton("Ver publicación");
		btnLost.setBackground(new Color(13, 110, 253));
	    btnLost.setForeground(Color.WHITE);
        lostCard.add(lblLost, BorderLayout.NORTH);
        lostCard.add(btnLost, BorderLayout.SOUTH);
        lostCard.setBackground(new Color(192,197,193));
        lostCard.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel foundCard = new JPanel();
        foundCard.setLayout(new BorderLayout());
        JLabel lblFound = new JLabel();
        JButton btnFound = new JButton("Ver publicación");
        btnFound.setBackground(new Color(13, 110, 253));
	    btnFound.setForeground(Color.WHITE);
		foundCard.add(lblFound, BorderLayout.NORTH);
		foundCard.add(btnFound, BorderLayout.SOUTH);
		foundCard.setBackground(new Color(192,197,193));
		foundCard.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel match = new JPanel();
		match.setLayout(new BorderLayout());
		JLabel lblMatch = new JLabel();
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatch.setFont(new Font("Roboto", Font.BOLD, 36));
		lblMatch.setForeground(Color.GREEN);
		match.add(lblMatch, BorderLayout.CENTER);

		resultados.add(lostCard);
        resultados.add(match);
        resultados.add(foundCard);
        lblLost.setText(item.getLostPet().getContent());
        lblFound.setText(item.getFoundPet().getContent());
        lblMatch.setText("MATCH");
	}
}
