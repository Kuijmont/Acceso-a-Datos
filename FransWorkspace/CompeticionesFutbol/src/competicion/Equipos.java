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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Equipos extends JFrame {

	private JPanel contentPane;
	Start principal;
	static Persistencia per = Start.per;
	private static JTextField textTeam;
	private static JComboBox<String> comboBoxTeam;
	private JButton btnConsultarTeam;
	private JButton btnModDelet;
	private JLabel lblDorsal;
	private JTextField textFieldDorsal;

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
	 */
	public Equipos() {
		setTitle("Mantenimiento de equipos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(29, 40, 70, 15);
		contentPane.add(lblEquipo);
		
		comboBoxTeam = new JComboBox();
		comboBoxTeam.setModel(new DefaultComboBoxModel(new String[] {""}));
		fillComboBox();
		comboBoxTeam.setEditable(true);
		comboBoxTeam.setBounds(101, 36, 152, 24);
		contentPane.add(comboBoxTeam);
		
		btnConsultarTeam = new JButton("Consultar");
		btnConsultarTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultButton();
			}
		});
		btnConsultarTeam.setBounds(291, 35, 117, 25);
		contentPane.add(btnConsultarTeam);
		
		textTeam = new JTextField();
		textTeam.setBounds(101, 91, 124, 19);
		contentPane.add(textTeam);
		textTeam.setColumns(10);
		
		JLabel label = new JLabel("Equipo:");
		label.setBounds(29, 93, 70, 15);
		contentPane.add(label);
		
		btnModDelet = new JButton("Modificar/Eliminar");
		btnModDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modDeletButton();
			}
		});
		btnModDelet.setBounds(242, 88, 166, 25);
		contentPane.add(btnModDelet);
		
		JLabel lblJugador = new JLabel("Jugador:");
		lblJugador.setBounds(29, 187, 70, 15);
		contentPane.add(lblJugador);
		
		JComboBox comboBoxPlayer = new JComboBox();
		comboBoxPlayer.setEditable(true);
		comboBoxPlayer.setBounds(101, 182, 152, 24);
		contentPane.add(comboBoxPlayer);
		
		JButton btnConsultarPlayer = new JButton("Consultar");
		btnConsultarPlayer.setBounds(291, 182, 117, 25);
		contentPane.add(btnConsultarPlayer);
		
		lblDorsal = new JLabel("Dorsal:");
		lblDorsal.setBounds(29, 229, 70, 15);
		contentPane.add(lblDorsal);
		
		textFieldDorsal = new JTextField();
		textFieldDorsal.setBounds(101, 227, 70, 19);
		contentPane.add(textFieldDorsal);
		textFieldDorsal.setColumns(10);
		
		JLabel lblPosicin = new JLabel("Posición:");
		lblPosicin.setBounds(187, 227, 78, 15);
		contentPane.add(lblPosicin);
		
		JComboBox comboBoxPosition = new JComboBox();
		comboBoxPosition.setBounds(267, 219, 143, 24);
		contentPane.add(comboBoxPosition);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 265, 424, 2);
		contentPane.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 159, 424, 2);
		contentPane.add(separator);
		
		JButton btnVolverALos = new JButton("Volver a los equipos");
		btnVolverALos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxTeam.setEnabled(true);
				btnConsultarTeam.setEnabled(true);
				comboBoxTeam.grabFocus();
			}
		});
		btnVolverALos.setBounds(29, 122, 379, 25);
		contentPane.add(btnVolverALos);
	}

	protected void modDeletButton() {
		String team = textTeam.getText();
		Equipo team2 = new Equipo(comboBoxTeam.getSelectedItem().toString());
		if(team.equals("")) {
			per.toDelete(team2);
			comboBoxTeam.removeItem(comboBoxTeam.getSelectedItem().toString());
			comboBoxTeam.setSelectedIndex(0);
			comboBoxTeam.grabFocus();
		}else {
			per.toModify(new Equipo(team),comboBoxTeam.getSelectedItem().toString());
			comboBoxTeam.removeAllItems();
			fillComboBox();
			
		}
	}


	protected void consultButton() {
		// Check if the Team selected at ComboBox exists
		 String item = comboBoxTeam.getSelectedItem().toString();
		 try {
			if(item != "") { 
				if(per.toSelectATeam(item)) {
					textTeam.setText(item);
					textTeam.grabFocus();
					comboBoxTeam.setEnabled(false);
					btnConsultarTeam.setEnabled(false);
				}else {
					boolean create =per.confirmQuestion(principal, "Crear el equipo",
							"El equipo seleccionado no existe.\n ¿Quieres crearlo?");
					if(create) {
						Equipo team = new Equipo(comboBoxTeam.getSelectedItem().toString());
						per.toRegisterATeam(team);
						textTeam.setText(item);
						textTeam.grabFocus();
						comboBoxTeam.setEnabled(false);
						btnConsultarTeam.setEnabled(false);
						comboBoxTeam.addItem(item);
					}else {
						per.infoMessage(principal, "Cancelar", "Se ha cancelado la creación.");
						comboBoxTeam.setSelectedIndex(0);
						comboBoxTeam.grabFocus();
					}
				}
			}else {
				per.infoMessage(principal, "Mensaje", "Debe introducir un nombre para el equipo");
				comboBoxTeam.grabFocus();
			}
		} catch (SQLException e1) {
			per.notifyError(principal, "Error", e1, "No existe ese equipo");
		}
	}


	private  void fillComboBox() {
		try {
			ArrayList<Equipo> team = per.toListTeams();
			for (int i = 0; i < team.size(); i++) {
				comboBoxTeam.addItem(team.get(i).getNombre());
			}
		} catch (SQLException e1) {
			per.notifyError(null, "Error", e1, "Error al listar equipos");
		}
	}
}
