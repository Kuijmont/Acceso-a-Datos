package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Start extends JFrame {

	private JPanel contentPane;
	private static String persistenceType, IP, user, pass, bd;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
					conect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static void conect() throws Exception{
			BufferedReader bfr = new BufferedReader(new FileReader(new File("src/utilidades/CFG.INI")));
			persistenceType = bfr.readLine();
			bfr.readLine();
			IP = bfr.readLine();
			bd = bfr.readLine();
			user = bfr.readLine();
			pass = bfr.readLine();
			bfr.close();
			//Leemos CFG.INI
			Persistencia per = null;
			switch (persistenceType){
			   case "mysqlJDBC":
			      per=new PersistenciaMySQL();
			      break;
			   /*case "hibernate": 
			      per=new PersistenciaHibernate(...);
			      break;*/
			   default: //ERROR
			   }
			per.conectBD(IP, user, pass, bd);
			
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
