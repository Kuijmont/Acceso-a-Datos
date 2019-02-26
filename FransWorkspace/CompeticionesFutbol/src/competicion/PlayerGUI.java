package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tablas.Equipo;
import tablas.Jugador;
import tablas.Posicion;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PlayerGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxTeam;
	private JButton btnConsultTeam;
	InitGUI principal;
	static Persistencia per = InitGUI.per;
	private ArrayList<Equipo> teamList;
	private ArrayList<Jugador> playerList;
	private ArrayList<Posicion> posList;
	private JLabel lblJugadores;
	private JButton btnConsultPlayer;
	private JComboBox comboBoxPlayers;
	private JLabel lblJugador;
	private JLabel lblNDeLicencia;
	private JLabel lblNombreDelJugador;
	private JLabel lblEquipoDelJugador;
	private JTextField textLicencia;
	private JTextField textName;
	private JTextField textTeam;
	Equipo e = null;
	Jugador player = null;
	Posicion pos = null;
	private JLabel lblDorsalDelJugador;
	private JTextField textDorsal;
	private JComboBox comboBoxPos;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerGUI frame = new PlayerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerGUI() {
		setTitle("Jugadores de un equipo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Equipo:");
		label.setBounds(30, 36, 70, 15);
		contentPane.add(label);

		comboBoxTeam = new JComboBox();
		fillComboBoxTeam();
		comboBoxTeam.setBounds(102, 32, 152, 24);
		contentPane.add(comboBoxTeam);

		btnConsultTeam = new JButton("Consultar");
		btnConsultTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultTeam();
			}
		});
		btnConsultTeam.setBounds(292, 31, 117, 25);
		contentPane.add(btnConsultTeam);

		lblJugadores = new JLabel("Jugadores:");
		lblJugadores.setBounds(30, 93, 83, 15);
		contentPane.add(lblJugadores);

		comboBoxPlayers = new JComboBox();
		comboBoxPlayers.setEnabled(false);
		
		comboBoxPlayers.setBounds(118, 88, 136, 24);
		contentPane.add(comboBoxPlayers);

		btnConsultPlayer = new JButton("Consultar");
		btnConsultPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultPlayer();
			}
		});
		btnConsultPlayer.setEnabled(false);
		btnConsultPlayer.setBounds(292, 88, 117, 25);
		contentPane.add(btnConsultPlayer);

		lblJugador = new JLabel("Ficha del jugador");
		lblJugador.setBounds(151, 145, 136, 15);
		contentPane.add(lblJugador);

		lblNDeLicencia = new JLabel("Nº de Licencia:");
		lblNDeLicencia.setBounds(71, 184, 117, 15);
		contentPane.add(lblNDeLicencia);

		lblNombreDelJugador = new JLabel("Nombre del jugador:");
		lblNombreDelJugador.setBounds(30, 211, 166, 15);
		contentPane.add(lblNombreDelJugador);

		lblEquipoDelJugador = new JLabel("Equipo del jugador:");
		lblEquipoDelJugador.setBounds(40, 238, 148, 15);
		contentPane.add(lblEquipoDelJugador);

		JLabel lblPosicinDelJugador = new JLabel("Posición del jugador:");
		lblPosicinDelJugador.setBounds(30, 265, 152, 15);
		contentPane.add(lblPosicinDelJugador);

		textLicencia = new JTextField();
		textLicencia.setEnabled(false);
		textLicencia.setBounds(206, 182, 114, 19);
		contentPane.add(textLicencia);
		textLicencia.setColumns(10);

		textName = new JTextField();
		textName.setEnabled(false);
		textName.setBounds(206, 209, 114, 19);
		contentPane.add(textName);
		textName.setColumns(10);

		textTeam = new JTextField();
		textTeam.setEnabled(false);
		textTeam.setBounds(206, 236, 114, 19);
		contentPane.add(textTeam);
		textTeam.setColumns(10);

		lblDorsalDelJugador = new JLabel("Dorsal del jugador:");
		lblDorsalDelJugador.setBounds(40, 292, 142, 15);
		contentPane.add(lblDorsalDelJugador);

		textDorsal = new JTextField();
		textDorsal.setEnabled(false);
		textDorsal.setBounds(206, 290, 114, 19);
		contentPane.add(textDorsal);
		textDorsal.setColumns(10);

		comboBoxPos = new JComboBox();
		fillComboBoxPosition();
		comboBoxPos.setBounds(206, 260, 114, 24);
		contentPane.add(comboBoxPos);

		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPlayer();
			}
		});
		btnCrear.setBounds(40, 329, 95, 25);
		contentPane.add(btnCrear);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(169, 329, 117, 25);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(308, 329, 117, 25);
		contentPane.add(btnEliminar);

	}

	protected void createPlayer() {
		
		for (int i = 0; i < posList.size(); i++) {
			
			if(comboBoxPos.getSelectedItem().toString().equals(posList.get(i).getDescripcion())) {
				 pos = new Posicion(posList.get(i).getDescripcion());
				 pos.setId(posList.get(i).getId());
			}
		}
		
		if (textLicencia.getText() != null && textName.getText() != null && textDorsal.getText() != null) {
			 player = new Jugador(Integer.valueOf(textLicencia.getText()), e,
					pos, textName.getText(),
					Integer.valueOf(textDorsal.getText()));
			 boolean create = per.confirmQuestion(principal, "Crear el jugador",
						"El jugador seleccionado no existe.\n ¿Quieres crearlo?");
				
				if (create) {
					per.toRegisterAPlayer(player);

					comboBoxPlayers.setEnabled(false);

					comboBoxPlayers.addItem(player.getNombre());
					
				} else {
					per.infoMessage(principal, "Cancelar", "Se ha cancelado la creación.");

				}
				
				textDorsal.setText(null);
				textLicencia.setText(null);
				textName.setText(null);
		} else {
			per.infoMessage(principal, "Información", "Debe rellenar todos los campos.");
		}
		
		
	}

	protected void consultPlayer() {
		// Check if the Team selected at ComboBox exists
		String item = comboBoxPlayers.getSelectedItem().toString();

		textTeam.setText(comboBoxTeam.getSelectedItem().toString());
		textDorsal.setEnabled(true);
		textLicencia.setEnabled(true);
		textName.setEnabled(true);
		if (item != "") {
			if (per.toSelectAPlayer(item)) {
				// MODIFY PLAYER
				for (int i = 0; i < playerList.size(); i++) {
					if (comboBoxTeam.getSelectedItem().toString().equals(playerList.get(i).getEquipo().getNombre())) {
						player = new Jugador();
						player.setLicencia(playerList.get(i).getLicencia());
						player.setNombre(playerList.get(i).getNombre());
						player.setEquipo(playerList.get(i).getEquipo());
						player.setPosicion(playerList.get(i).getPosicion());
						player.setDorsal(playerList.get(i).getDorsal());

					}
				}
				int license = player.getLicencia();
				textLicencia.setText(Integer.toString(license));
				String name = player.getNombre();
				textName.setText(name);
				String team = player.getEquipo().getNombre();
				textTeam.setText(team);
				String position = player.getPosicion().getDescripcion();
				for (int i = 0; i < posList.size(); i++) {
					if (posList.get(i).getDescripcion().equals(position))
						comboBoxPos.setSelectedItem(position);
				}

				int dorsal = player.getDorsal();
				textDorsal.setText(Integer.toString(dorsal));

				btnConsultPlayer.setEnabled(false);
				comboBoxPlayers.setEnabled(false);
				playerList = per.listPlayersOfTeam(e);

			} else {

			}
		} else {
			per.infoMessage(principal, "Mensaje", "Debe introducir un nombre para el jugador");
			comboBoxTeam.grabFocus();
		}

	}

	protected void consultTeam() {
		
		String item = comboBoxTeam.getSelectedItem().toString();
		try {
			if (per.toSelectATeam(item)) {
				comboBoxTeam.setEnabled(false);
				btnConsultTeam.setEnabled(false);

				for (int i = 0; i < teamList.size(); i++) {
					if (comboBoxTeam.getSelectedItem().toString().equals(teamList.get(i).getNombre())) {
						e = new Equipo(teamList.get(i).getNombre());
						e.setId(teamList.get(i).getId());
					}
				}
				btnConsultPlayer.setEnabled(true);
				comboBoxPlayers.setEnabled(true);
				playerList = per.listPlayersOfTeam(e);
				fillComboBoxPlayer();
				textTeam.setText(comboBoxTeam.getSelectedItem().toString());
				textDorsal.setEnabled(true);
				textLicencia.setEnabled(true);
				textName.setEnabled(true);
			}

		} catch (SQLException e1) {
			per.notifyError(principal, "Error", e1, "No existe ese equipo");
		}

	}

	private void fillComboBoxTeam() {
		try {
			teamList = per.toListTeams();
			for (int i = 0; i < teamList.size(); i++) {
				comboBoxTeam.addItem(teamList.get(i).getNombre());

			}
		} catch (SQLException e1) {
			per.notifyError(null, "Error", e1, "Error al listar equipos");
		}
	}

	private void fillComboBoxPlayer() {
		for (int i = 0; i < playerList.size(); i++) {
			comboBoxPlayers.addItem(playerList.get(i).getNombre());
		}
	}

	private void fillComboBoxPosition() {
		posList = per.listPositions();
		for (int i = 0; i < posList.size(); i++) {
			comboBoxPos.addItem(posList.get(i).getDescripcion());
		}
	}
}
