package competicion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tablas.Equipo;
import tablas.Posicion;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PositionGUI extends JFrame {
	
	InitGUI principal;
	static Persistencia per = InitGUI.per;
	private JPanel contentPane;
	private JTextField textPosition;
	private JButton btnModDelet;
	private JButton btnConsult;
	private JComboBox comboBox;
	private JButton btnReturn;
	private ArrayList<Posicion> posList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PositionGUI frame = new PositionGUI();
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
	public PositionGUI() {
		setTitle("Mantenimiento de posiciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPosicin = new JLabel("Posición:");
		lblPosicin.setBounds(30, 38, 70, 15);
		contentPane.add(lblPosicin);
		
		comboBox = new JComboBox();
		fillComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(118, 33, 153, 24);
		contentPane.add(comboBox);
		
		btnConsult = new JButton("Consultar");
		btnConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultButton();
			}
		});
		btnConsult.setBounds(295, 33, 117, 25);
		contentPane.add(btnConsult);
		
		JLabel lblDejaEnBlanco = new JLabel("Deja en blanco para eliminar la posición");
		lblDejaEnBlanco.setBounds(65, 83, 311, 15);
		contentPane.add(lblDejaEnBlanco);
		
		JLabel lblPosicin_1 = new JLabel("Posición:");
		lblPosicin_1.setBounds(40, 115, 70, 15);
		contentPane.add(lblPosicin_1);
		
		textPosition = new JTextField();
		textPosition.setEnabled(false);
		textPosition.setColumns(10);
		textPosition.setBounds(112, 113, 124, 19);
		contentPane.add(textPosition);
		
		btnReturn = new JButton("Volver a las posiciones");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(true);
				btnConsult.setEnabled(true);
				comboBox.setSelectedIndex(0);
				comboBox.grabFocus();
				textPosition.setText(null);
				btnReturn.setEnabled(false);
				btnModDelet.setEnabled(false);
				textPosition.setEnabled(false);
			}
		});
		btnReturn.setEnabled(false);
		btnReturn.setBounds(40, 172, 379, 25);
		contentPane.add(btnReturn);
		
		btnModDelet = new JButton("Modificar/Eliminar");
		btnModDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modDeletButton();
			}
		});
		btnModDelet.setEnabled(false);
		btnModDelet.setBounds(253, 110, 166, 25);
		contentPane.add(btnModDelet);
	}
	
	protected void modDeletButton() {
		String pos = textPosition.getText();
		Posicion pos2 = new Posicion(comboBox.getSelectedItem().toString());
		if(pos.equals("")) {
			per.toDeleteAPositon(pos2);
			comboBox.removeItem(comboBox.getSelectedItem().toString());
			btnReturn.grabFocus();
			btnModDelet.setEnabled(false);
			textPosition.setEnabled(false);
			textPosition.setText(null);
			btnReturn.setEnabled(true);
			btnReturn.grabFocus();
		}else {
			Posicion e = null;
			for (int i = 0; i < posList.size(); i++) {
				
				if(comboBox.getSelectedItem().toString().equals(posList.get(i).getDescripcion())) {
					 e = new Posicion(posList.get(i).getDescripcion());
					 System.out.println(e.getDescripcion());
					 e.setId(posList.get(i).getId());
					 System.out.println(posList.get(i).getId());
				}
			}
			per.toModifyAPosition(e,textPosition.getText());
			comboBox.removeAllItems();
			fillComboBox();
			btnModDelet.setEnabled(false);
			btnReturn.setEnabled(true);
			textPosition.setEnabled(false);
			textPosition.setText(null);
			btnReturn.grabFocus();
		}
	}
	
	protected void consultButton() {
		// Check if the Position selected at ComboBox exists
		 String item = comboBox.getSelectedItem().toString();
		 
			if(item != "") { 
				if(per.toSelectAPosition(item)) {
					textPosition.setEnabled(true);
					textPosition.setText(item);
					textPosition.grabFocus();
					comboBox.setEnabled(false);
					btnConsult.setEnabled(false);
					btnModDelet.setEnabled(true);
					btnReturn.setEnabled(true);
				}else {
					boolean create =per.confirmQuestion(principal, "Crear posición",
							"La posición seleccionada no existe.\n ¿Quieres crearla?");
					if(create) {
						Posicion pos = new Posicion(comboBox.getSelectedItem().toString());
						per.toRegisterAPosition(pos);
						textPosition.setEnabled(true);
						textPosition.setText(item);
						textPosition.grabFocus();
						comboBox.setEnabled(false);
						btnConsult.setEnabled(false);
						comboBox.addItem(item);
						btnModDelet.setEnabled(true);
						btnReturn.setEnabled(true);
					}else {
						per.infoMessage(principal, "Cancelar", "Se ha cancelado la creación.");
						comboBox.setSelectedIndex(0);
						comboBox.grabFocus();
					}
				}
			}else {
				per.infoMessage(principal, "Mensaje", "Debe introducir una descripción para la posición.");
				comboBox.grabFocus();
			}
		
	}


	private  void fillComboBox() {
		posList = per.listPositions();
		for (int i = 0; i < posList.size(); i++) {
			comboBox.addItem(posList.get(i).getDescripcion());
		}
	}
}
