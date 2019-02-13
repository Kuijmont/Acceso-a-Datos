package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tablas.Equipo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class Equipos extends JFrame {

	private JPanel contentPane;
	Start principal;
	private static JTextField textTeam;
	private static JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Equipos frame = new Equipos();
					frame.setVisible(true);
					fillComboBox();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static void fillComboBox() {
		try {
			
			ArrayList<Equipo> team = Start.per.toListTeams();
			for (int i = 0; i < team.size(); i++) {
				comboBox.addItem(team.get(i).getNombre());
			}

		} catch (SQLException e1) {
			Start.per.notifyError(null, "Error", e1, "Error al listar equipos");
		}
	}

	/**
	 * Create the frame.
	 */
	public Equipos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(29, 40, 70, 15);
		contentPane.add(lblEquipo);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(91, 35, 273, 24);
		contentPane.add(comboBox);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check if the Team selected at ComboBox exists
				 String item = comboBox.getSelectedItem().toString();
				 
				 
				 
			}
		});
		btnConsultar.setBounds(166, 71, 117, 25);
		contentPane.add(btnConsultar);
		
		textTeam = new JTextField();
		textTeam.setBounds(101, 140, 114, 19);
		contentPane.add(textTeam);
		textTeam.setColumns(10);
		
		JLabel label = new JLabel("Equipo:");
		label.setBounds(29, 142, 70, 15);
		contentPane.add(label);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(247, 137, 117, 25);
		contentPane.add(btnGuardar);
	}

	
}
