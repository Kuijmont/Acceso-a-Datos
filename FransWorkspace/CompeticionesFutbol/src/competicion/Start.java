package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {

	private JPanel contentPane;
	private static String persistenceType, server, user, password, database, cfg;
	public static Persistencia per;
	private static String op;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				
					Properties prop=new Properties();
					prop.load(new InputStreamReader(Start.class.getResourceAsStream("CFG.INI")));//CFG.INI en el mismo paquete que PrincipalGUI
					persistenceType=prop.getProperty("tipoPersistencia");

					System.out.println(persistenceType);
					//Leemos CFG.INI
					switch (persistenceType){
					   case "mysqlJDBC":
						  server = prop.getProperty("mysqlJDBC.servidor");
						  database = prop.getProperty("mysqlJDBC.basedatos");
						  user = prop.getProperty("mysqlJDBC.usuario");
						  password = prop.getProperty("mysqlJDBC.password");
						  per=new PersistenciaMySQL(server,user,password,database);
					      //per=new PersistenciaMySQL(IP, user, pass, bd);
					      break;
					   case "hibernate": 
					      per=new PersistenciaHibernate(prop.getProperty("hibernate.archivoCFG"));
					      break;
					   default: 
						   JOptionPane.showMessageDialog(null, "Se ha producido un error en la selección de servidor.", "Error", 0);
					   }
					per.conectBD();
					
				} catch (Exception e) {
					e.getMessage();
				}
			}
		});
	}
		

	/**
	 * Create the frame.
	 */
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 448, 21);
		contentPane.add(menuBar);
		
		JMenu menuMaintenance = new JMenu("Mantenimiento");
		menuBar.add(menuMaintenance);
		
		JMenuItem itemTeam = new JMenuItem("Equipos");
		itemTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				op = "Equipo";
				Equipos ven = new Equipos();
				ven.setVisible(true);  
			}
		});
		menuMaintenance.add(itemTeam);
		
		JMenuItem itemPositions = new JMenuItem("Posiciones");
		menuMaintenance.add(itemPositions);
		
		JMenuItem itemCompetitions = new JMenuItem("Competiciones");
		menuMaintenance.add(itemCompetitions);
		
		JMenu menuManagement = new JMenu("Gestión de jugadores");
		menuBar.add(menuManagement);
		
		JMenu menuCompetition = new JMenu("Competición");
		menuBar.add(menuCompetition);
	}
}
