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
import javax.swing.JOptionPane;
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
	static Persistencia per = Start.per;
	private static JTextField textTeam;
	private static JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Equipos frame = new Equipos();
				frame.setVisible(true);
			}
		});
	}

	
	/**
	 * Create the frame.
	 * @param per2 
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		fillComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(91, 35, 273, 24);
		contentPane.add(comboBox);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultButton();
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

	protected void consultButton() {
		// Check if the Team selected at ComboBox exists
		 String item = comboBox.getSelectedItem().toString();
		 try {
			if(item != "") { 
				if(per.toSelectATeam(item)) {
					textTeam.setText(item);
					textTeam.grabFocus();
				}else {
					boolean create =per.confirmQuestion(principal, "Crear el equipo",
							"El equipo seleccionado no existe.\n ¿Quieres crearlo?");
					if(create) {
						Equipo team = new Equipo(comboBox.getSelectedItem().toString());
						per.toRegisterATeam(team);
						textTeam.setText(item);
						textTeam.grabFocus();
						comboBox.addItem(item);
					}else {
						per.infoMessage(principal, "Cancelar", "Se ha cancelado la creación.");
						comboBox.setSelectedIndex(0);
						comboBox.grabFocus();
					}
				}
			}else {
				per.infoMessage(principal, "Mensaje", "Debe introducir un nombre para el equipo");
				comboBox.grabFocus();
			}
		} catch (SQLException e1) {
			per.notifyError(principal, "Error", e1, "No existe ese equipo");
		}
		 
		 
		
	}


	private  void fillComboBox() {
		try {
			ArrayList<Equipo> team = per.toListTeams();
			for (int i = 0; i < team.size(); i++) {
				comboBox.addItem(team.get(i).getNombre());
			}
		} catch (SQLException e1) {
			per.notifyError(null, "Error", e1, "Error al listar equipos");
		}
	}

}
