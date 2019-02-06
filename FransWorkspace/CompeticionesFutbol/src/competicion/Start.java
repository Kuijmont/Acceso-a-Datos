package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	private static String persistenceType, IP, user, pass, bd, cfg;
	private static Persistencia per;
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
					// Read CFG.INI
					BufferedReader bfr = new BufferedReader(new FileReader(new File("src/utilidades/CFG.INI")));
					// Catch the type of persistence
					persistenceType = bfr.readLine().substring(17);
					// Read an Option
					bfr.readLine();
					// Catch the IP
					IP = bfr.readLine().substring(9);
					// Catch the Database
					bd = bfr.readLine().substring(10);
					// Catch the User
					user = bfr.readLine().substring(8);
					// Catch the Password
					pass = bfr.readLine().substring(9);
					// Read an Option
					bfr.readLine();
					// Catch the cfg for hibernate
					cfg = bfr.readLine().substring(11);
					bfr.close();
					System.out.println(persistenceType);
					//Leemos CFG.INI
					switch (persistenceType){
					   case "mysqlJDBC":
					      per=new PersistenciaMySQL(IP, user, pass, bd);
					      break;
					   case "hibernate": 
					      per=new PersistenciaHibernate();
					      break;
					   default: 
						   JOptionPane.showMessageDialog(null, "Se ha producido un error en la selección de servidor.", "Error", 0);
					   }
					per.conectBD();
					per.toRegister(op);
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
