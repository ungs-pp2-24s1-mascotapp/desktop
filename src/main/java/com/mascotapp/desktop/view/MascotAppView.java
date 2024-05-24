package com.mascotapp.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.entities.Match;
import com.mascotapp.core.entities.Post;
import com.mascotapp.core.service.socialNetwork.SocialNetworkInfo;
import com.mascotapp.desktop.controller.MascotAppController;
import java.awt.GridLayout;

@SuppressWarnings({ "serial", "deprecation" })
public class MascotAppView extends JFrame implements Observer {
	
	private final String titleApp = "MascotApp";
	private final String matchText = "← ¡MATCH! →";
	private final String fontNameMatch = "Roboto";
	private final String buttonPostText = "Ver publicación";
	private final String menuText = "Redes sociales";
	private final String buttonSearchText = "Buscar mascotas";
	private final String lostText = "PERDIDO";
	private final String foundText = "ENCONTRADO";
	
	private final int windowSizeWidth = 1200;
	private final int windowSizeHeight = 720;
	
	JButton searchButton;
    
    JPanel resultados;
    
    public MascotAppView(MascotApp core) {
    	this.setCrossPlatformJavaLookAndFeelMetal();
        this.initializeWindow();
        new MascotAppController(this, core);
    }


    public void init() {
        this.setVisible(true);
    }
    
    private void setCrossPlatformJavaLookAndFeelMetal() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        
        searchButton = new JButton(buttonSearchText);
        //Color Naranja
        searchButton.setBackground(new Color(241, 136, 5));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchButton, BorderLayout.WEST);
        searchPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
        
        resultados = new JPanel();
        mainPanel.add(resultados, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
      
        JScrollPane scrollPane = new JScrollPane(resultados, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane);
    }
    
    public void addMenu(Set<SocialNetworkInfo> socialNetworks, ActionListener actionListener) {
    	// Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Social Network"
        JMenu socialNetWorkMenu = new JMenu(menuText);
        menuBar.add(socialNetWorkMenu);
        
        //Crear opciones con check de forma genérica

        for (SocialNetworkInfo socialNetwork : socialNetworks) {
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(socialNetwork.getName());
            menuItem.addActionListener(actionListener);
            menuItem.setSelected(socialNetwork.isActive());
            socialNetWorkMenu.add(menuItem);
        }

        // Configurar la barra de menú en la ventana
        setJMenuBar(menuBar);
    }
    
    private void centerWindow() {
    	setLocationRelativeTo(null);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        @SuppressWarnings("unchecked")
		Set<Match> results = (Set<Match>) arg;
        setResults(results);
    }
    
    public void setResults(Set<Match> results) {
        resultados.removeAll();
        resultados.setLayout(new GridLayout(results.size(), 3, 0, 20));     
        
        for (Match result: results) {
        	displayItem(result);
        }
        
        resultados.revalidate();
        resultados.repaint();
    }

	public void setSearchListener(ActionListener searchListener) {
		searchButton.addActionListener(searchListener);
	}
	
	public void displayItem(Match item) {
        JPanel lostCard = new JPanel();
        lostCard.setLayout(new BorderLayout());
		
		JLabel txtLost = new JLabel(lostText);
		txtLost.setBorder(new EmptyBorder(0, 0, 10, 0));
		txtLost.setForeground(Color.RED);
		lostCard.add(txtLost, BorderLayout.NORTH);
				
		JLabel lblLost = new JLabel();
		lblLost.setBorder(new EmptyBorder(0, 0, 20, 0));
		lostCard.add(lblLost, BorderLayout.WEST);
		
		JButton btnLost = new JButton(getSeePostButtonText(item.getLostPet()));
		//Color Azul
		btnLost.setBackground(new Color(13, 110, 253));
	    btnLost.setForeground(Color.WHITE);
        
	    lostCard.add(btnLost, BorderLayout.SOUTH);
        lostCard.setBackground(new Color(192,197,193));
        lostCard.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel foundCard = new JPanel();
        foundCard.setLayout(new BorderLayout());
        
        JLabel txtFound = new JLabel(foundText);
        txtFound.setBorder(new EmptyBorder(0, 0, 10, 0));
        txtFound.setForeground(new Color(75, 170, 75));
		foundCard.add(txtFound, BorderLayout.NORTH);
		
        JLabel lblFound = new JLabel();
		lblFound.setBorder(new EmptyBorder(0, 0, 20, 0));
        foundCard.add(lblFound, BorderLayout.WEST);
        
        JButton btnFound = new JButton(getSeePostButtonText(item.getFoundPet()));
        btnFound.setBackground(new Color(13, 110, 253));
	    btnFound.setForeground(Color.WHITE);
		foundCard.add(btnFound, BorderLayout.SOUTH);
		foundCard.setBackground(new Color(192,197,193));
		foundCard.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel match = new JPanel(); 
		match.setLayout(new BorderLayout()); 
		JLabel lblMatch = new JLabel();
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER); 
		lblMatch.setFont(new Font(fontNameMatch, Font.BOLD, 36)); 
		lblMatch.setForeground(Color.WHITE);
		match.setBackground(new Color(92, 184, 92));
		match.add(lblMatch, BorderLayout.CENTER);
		 
		resultados.add(lostCard);
        resultados.add(match);
        resultados.add(foundCard);
        
        lblLost.setText(item.getLostPet().getContent());
        lblFound.setText(item.getFoundPet().getContent());
        lblMatch.setText(matchText);
        
        btnLost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                	java.awt.Desktop.getDesktop().browse(java.net.URI.create(item.getLostPet().getUrl()));
            	}
            	catch(Exception ex){
            		ex.printStackTrace();
            	}
            }
        });
        
        btnFound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                	java.awt.Desktop.getDesktop().browse(java.net.URI.create(item.getFoundPet().getUrl()));
            	}
            	catch(Exception ex){
            		ex.printStackTrace();
            	}
            }
        });
	}
	
	private String getDomain(Post post) {
		String domain = "";
		String url = post.getUrl();
        try {
            URI uri = new URI(url);
            domain = uri.getHost();
            if (domain != null && domain.startsWith("www.")) {
                domain = domain.substring(4);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain;
	}
	
	private String getSeePostButtonText(Post post) {
		return buttonPostText + " en " + getDomain(post);
	}
}