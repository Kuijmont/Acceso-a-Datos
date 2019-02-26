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

public class TeamGUI extends JFrame {

	private JPanel contentPane;
	InitGUI principal;
	static Persistencia per = InitGUI.per;
	private static JTextField textTeam;
	private static JComboBox<String> comboBoxTeam;
	private JButton btnConsultarTeam;
	private JButton btnModDelet;
	private JButton btnVolverALos;
	private ArrayList<Equipo> teamList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TeamGUI frame = new TeamGUI();
				frame.setVisible(true);
			}
		});
	}

	
	/**
	 * Create the frame. 
	 */
	public TeamGUI() {
		setTitle("Mantenimiento de equipos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 254);
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
		textTeam.setEnabled(false);
		textTeam.setBounds(101, 116, 124, 19);
		contentPane.add(textTeam);
		textTeam.setColumns(10);
		
		JLabel label = new JLabel("Equipo:");
		label.setBounds(29, 118, 70, 15);
		contentPane.add(label);
		
		btnModDelet = new JButton("Modificar/Eliminar");
		btnModDelet.setEnabled(false);
		btnModDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modDeletButton();
			}
		});
		btnModDelet.setBounds(242, 113, 166, 25);
		contentPane.add(btnModDelet);
		
		btnVolverALos = new JButton("Volver a los equipos");
		btnVolverALos.setEnabled(false);
		btnVolverALos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxTeam.setEnabled(true);
				btnConsultarTeam.setEnabled(true);
				comboBoxTeam.setSelectedIndex(0);
				comboBoxTeam.grabFocus();
				textTeam.setText(null);
				btnVolverALos.setEnabled(false);
				btnModDelet.setEnabled(false);
				textTeam.setEnabled(false);
			}
		});
		btnVolverALos.setBounds(29, 175, 379, 25);
		contentPane.add(btnVolverALos);
		
		JLabel lblDejaEnBlanco = new JLabel("Deja en blanco para eliminar el equipo");
		lblDejaEnBlanco.setBounds(75, 89, 311, 15);
		contentPane.add(lblDejaEnBlanco);
	}

	protected void modDeletButton() {
		String team = textTeam.getText();
		Equipo team2 = new Equipo(comboBoxTeam.getSelectedItem().toString());
		if(team.equals("")) {
			per.toDeleteATeam(team2);
			comboBoxTeam.removeItem(comboBoxTeam.getSelectedItem().toString());
			btnVolverALos.grabFocus();
			btnModDelet.setEnabled(false);
			textTeam.setEnabled(false);
			textTeam.setText(null);
			btnVolverALos.setEnabled(true);
			btnVolverALos.grabFocus();
		}else {
			Equipo e = null;
			for (int i = 0; i < teamList.size(); i++) {
				
				if(comboBoxTeam.getSelectedItem().toString().equals(teamList.get(i).getNombre())) {
					 e = new Equipo(teamList.get(i).getNombre());
					 System.out.println(e.getNombre());
					 e.setId(teamList.get(i).getId());
					 System.out.println(teamList.get(i).getId());
				}
			}
			per.toModifyATeam(e,textTeam.getText());
			comboBoxTeam.removeAllItems();
			fillComboBox();
			btnModDelet.setEnabled(false);
			btnVolverALos.setEnabled(true);
			textTeam.setEnabled(false);
			textTeam.setText(null);
			btnVolverALos.grabFocus();
		}
	}


	protected void consultButton() {
		// Check if the Team selected at ComboBox exists
		 String item = comboBoxTeam.getSelectedItem().toString();
		 try {
			if(item != "") { 
				if(per.toSelectATeam(item)) {
					textTeam.setEnabled(true);
					textTeam.setText(item);
					textTeam.grabFocus();
					comboBoxTeam.setEnabled(false);
					btnConsultarTeam.setEnabled(false);
					btnModDelet.setEnabled(true);
					btnVolverALos.setEnabled(true);
				}else {
					boolean create =per.confirmQuestion(principal, "Crear el equipo",
							"El equipo seleccionado no existe.\n ¿Quieres crearlo?");
					if(create) {
						Equipo team = new Equipo(comboBoxTeam.getSelectedItem().toString());
						per.toRegisterATeam(team);
						textTeam.setEnabled(true);
						textTeam.setText(item);
						textTeam.grabFocus();
						comboBoxTeam.setEnabled(false);
						btnConsultarTeam.setEnabled(false);
						comboBoxTeam.addItem(item);
						btnModDelet.setEnabled(true);
						btnVolverALos.setEnabled(true);
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
			teamList = per.toListTeams();
			for (int i = 0; i < teamList.size(); i++) {
				comboBoxTeam.addItem(teamList.get(i).getNombre());
			}
		} catch (SQLException e1) {
			per.notifyError(null, "Error", e1, "Error al listar equipos");
		}
	}
}
