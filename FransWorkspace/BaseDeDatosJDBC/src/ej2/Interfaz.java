package ej2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Interfaz extends JFrame {
	
	private ArrayList<String> al;
	private JPanel contentPane;
	private String IP, usu, pass;
	private PersistenciaBD p = new PersistenciaBD();
	private JComboBox comboBox;
	private JButton btnDesconectar;
	private JButton btnConectar;
	private JTextField textFieldBase;
	private JTextField textFieldTabla;
	private JButton btnBuscar;
	private JButton btnResultado;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conectar();
					estado2();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {		
					System.out.println(e.getMessage());
				}
			}
		});
		btnConectar.setBounds(46, 34, 138, 25);
		contentPane.add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.desconectarDB();
				estadoInicial();
			}
		});
		btnDesconectar.setEnabled(false);
		btnDesconectar.setBounds(213, 34, 139, 25);
		contentPane.add(btnDesconectar);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Base Datos", "Tablas", "Columnas"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(130, 71, 116, 25);
		contentPane.add(comboBox);
		
		JLabel lblListar = new JLabel("Listar:");
		lblListar.setBounds(76, 76, 70, 15);
		contentPane.add(lblListar);
		
		JLabel lblBbdd = new JLabel("BBDD");
		lblBbdd.setBounds(12, 128, 50, 15);
		contentPane.add(lblBbdd);
		
		textFieldBase = new JTextField();
		textFieldBase.setEnabled(false);
		textFieldBase.setBounds(59, 126, 91, 19);
		contentPane.add(textFieldBase);
		textFieldBase.setColumns(10);
		
		JLabel lblTabla = new JLabel("Tabla");
		lblTabla.setBounds(186, 128, 70, 15);
		contentPane.add(lblTabla);
		
		textFieldTabla = new JTextField();
		textFieldTabla.setEnabled(false);
		textFieldTabla.setBounds(234, 126, 114, 19);
		contentPane.add(textFieldTabla);
		textFieldTabla.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Base Datos")) {
					textFieldBase.setEnabled(false);
					textFieldTabla.setEnabled(false);
					btnResultado.setEnabled(true);
				}else if(comboBox.getSelectedItem().equals("Tablas")) {
					textFieldBase.setEnabled(true);	
					btnResultado.setEnabled(true);
				}else if(comboBox.getSelectedItem().equals("Columnas")) {
					textFieldBase.setEnabled(true);
					textFieldTabla.setEnabled(true);
					btnResultado.setEnabled(true);
				}
			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(280, 71, 117, 25);
		contentPane.add(btnBuscar);
		
		btnResultado = new JButton("Resultado");
		btnResultado.setEnabled(false);
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				if(comboBox.getSelectedItem().equals("Base Datos")) {
					try {
						al=p.listarBases();
						for (int i = 0; i < al.size(); i++) {
							textArea.setText(textArea.getText()+al.get(i).toString()+"\n");
						}
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				}else if(comboBox.getSelectedItem().equals("Tablas")) {
						try {
							al=p.listarTablas(textFieldBase.getText());
							for (int i = 0; i < al.size(); i++) {
								textArea.setText(textArea.getText()+al.get(i).toString()+"\n");
							}
						} catch (SQLException e1) {
							System.out.println(e1.getMessage());
						}
				}else if(comboBox.getSelectedItem().equals("Columnas")) {
					try {
						ArrayList<Columna>a = new ArrayList<>();
						a=p.listarColumnas(textFieldBase.getText(), textFieldTabla.getText());
						for (int i = 0; i < a.size(); i++) {
							textArea.setText(textArea.getText()+a.get(i).toString()+"\n");
						}
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		btnResultado.setBounds(139, 157, 117, 25);
		contentPane.add(btnResultado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 205, 424, 126);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	protected void estadoInicial() {
		btnDesconectar.setEnabled(false);
		comboBox.setEnabled(false);
		btnConectar.setEnabled(true);
		btnBuscar.setEnabled(false);
		btnResultado.setEnabled(false);
		textArea.setText(null);
		textFieldBase.setText(null);
		textFieldTabla.setText(null);
	}

	protected void estado2() {
		btnDesconectar.setEnabled(true);
		comboBox.setEnabled(true);
		btnConectar.setEnabled(false);
		btnBuscar.setEnabled(true);
		textFieldBase.setEnabled(false);
		textFieldTabla.setEnabled(false);
		btnResultado.setEnabled(false);
	}

	protected void conectar() throws Exception {
		BufferedReader bfr = new BufferedReader(new FileReader(new File("src/ej2/CFG.INI")));
			IP = bfr.readLine();
			usu = bfr.readLine();
			pass = bfr.readLine();
		bfr.close();
		p = new PersistenciaBD();
		p.conectarDB(IP, usu, pass);

	}
}
