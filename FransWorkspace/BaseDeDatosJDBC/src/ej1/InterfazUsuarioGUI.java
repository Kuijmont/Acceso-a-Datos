package ej1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

public class InterfazUsuarioGUI extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnEmail;
	private Persistencia p = new PersistenciaBD();
	private JButton btnDesconectar;
	private JTextField textFieldNombre;
	private JTextField textFieldCP;
	private JTextField textFieldPais;
	private JTextField textFieldEmail;
	private JRadioButton rdbtnDefecto;
	private JRadioButton rdbtnCp;
	private JRadioButton rdbtnNombre;
	private JRadioButton rdbtnPais;
	private JButton btnListar;
	private JButton btnConectar;
	private JButton btnConsulta;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private String tabla, IP, usu, pass, bd;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<Persona> pers; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazUsuarioGUI frame = new InterfazUsuarioGUI();
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
	public InterfazUsuarioGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListadoOrdenadoPor = new JLabel("Listado:");
		lblListadoOrdenadoPor.setBounds(12, 49, 83, 15);
		contentPane.add(lblListadoOrdenadoPor);
		
		btnListar = new JButton("Listado");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pers = p.listadoPersonas(tabla, buttonGroup.getSelection().toString());
			}
		});
		btnListar.setEnabled(false);
		btnListar.setBounds(283, 12, 117, 25);
		contentPane.add(btnListar);
		
		rdbtnDefecto = new JRadioButton("Por Defecto");
		buttonGroup.add(rdbtnDefecto);
		rdbtnDefecto.setEnabled(false);
		rdbtnDefecto.setSelected(true);
		rdbtnDefecto.setBounds(22, 72, 124, 23);
		contentPane.add(rdbtnDefecto);
		
		rdbtnCp = new JRadioButton("Por CP");
		buttonGroup.add(rdbtnCp);
		rdbtnCp.setEnabled(false);
		rdbtnCp.setBounds(21, 99, 149, 23);
		contentPane.add(rdbtnCp);
		
		rdbtnEmail = new JRadioButton("Por Email");
		buttonGroup.add(rdbtnEmail);
		rdbtnEmail.setEnabled(false);
		rdbtnEmail.setBounds(22, 126, 149, 23);
		contentPane.add(rdbtnEmail);
		
		rdbtnNombre = new JRadioButton("Por Nombre");
		buttonGroup.add(rdbtnNombre);
		rdbtnNombre.setEnabled(false);
		rdbtnNombre.setBounds(22, 153, 149, 23);
		contentPane.add(rdbtnNombre);
		
		rdbtnPais = new JRadioButton("Por País");
		buttonGroup.add(rdbtnPais);
		rdbtnPais.setEnabled(false);
		rdbtnPais.setBounds(22, 180, 149, 23);
		contentPane.add(rdbtnPais);
		
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
		btnConectar.setBounds(22, 12, 117, 25);
		contentPane.add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setEnabled(false);
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.desconectarDB();
				estadoInicial();
			}
		});
		btnDesconectar.setBounds(151, 12, 124, 25);
		contentPane.add(btnDesconectar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(198, 76, 70, 15);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEnabled(false);
		textFieldNombre.setBounds(286, 74, 114, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CP");
		lblNewLabel.setBounds(198, 103, 72, 15);
		contentPane.add(lblNewLabel);
		
		textFieldCP = new JTextField();
		textFieldCP.setEnabled(false);
		textFieldCP.setBounds(286, 101, 114, 19);
		contentPane.add(textFieldCP);
		textFieldCP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pais");
		lblNewLabel_1.setBounds(198, 130, 70, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldPais = new JTextField();
		textFieldPais.setEnabled(false);
		textFieldPais.setBounds(286, 128, 114, 19);
		contentPane.add(textFieldPais);
		textFieldPais.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(198, 157, 70, 15);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEnabled(false);
		textFieldEmail.setBounds(286, 155, 114, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persona persona = new Persona(textFieldNombre.getText(), textFieldCP.getText(), textFieldPais.getText(), textFieldEmail.getText());
				try {
					p.guardarPersona(tabla, persona);
					estado2();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(22, 227, 117, 25);
		contentPane.add(btnGuardar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					p.borrarPersona(tabla, textFieldEmail.getText());
					estado2();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(151, 227, 117, 25);
		contentPane.add(btnBorrar);
		
		btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Persona pers;
					pers = p.consultarPersona(tabla, textFieldEmail.getText());
					textFieldNombre.setText(pers.getNombre());
					textFieldCP.setText(pers.getCP());
					textFieldEmail.setText(pers.getEmail());
					textFieldPais.setText(pers.getPais());
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnConsulta.setEnabled(false);
		btnConsulta.setBounds(283, 227, 117, 25);
		contentPane.add(btnConsulta);
	}
	
	
	protected void estado2() {
		rdbtnCp.setEnabled(true);
		rdbtnDefecto.setEnabled(true);
		rdbtnEmail.setEnabled(true);
		rdbtnNombre.setEnabled(true);
		rdbtnPais.setEnabled(true);
		textFieldPais.setEnabled(true);
		textFieldEmail.setEnabled(true);
		textFieldCP.setEnabled(true);
		textFieldNombre.setEnabled(true);
		btnListar.setEnabled(true);
		btnConectar.setEnabled(false);
		btnDesconectar.setEnabled(true);
		btnGuardar.setEnabled(true);
		btnBorrar.setEnabled(true);
		btnConsulta.setEnabled(true);
		textFieldPais.setText(null);
		textFieldEmail.setText(null);
		textFieldCP.setText(null);
		textFieldNombre.setText(null);
		
	}

	protected void estadoInicial() {
		rdbtnCp.setEnabled(false);
		rdbtnDefecto.setEnabled(false);
		rdbtnEmail.setEnabled(false);
		rdbtnNombre.setEnabled(false);
		rdbtnPais.setEnabled(false);
		textFieldPais.setEnabled(false);
		textFieldEmail.setEnabled(false);
		textFieldCP.setEnabled(false);
		textFieldNombre.setEnabled(false);
		btnListar.setEnabled(false);
		btnDesconectar.setEnabled(false);
		btnConectar.setEnabled(true);
		btnGuardar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnConsulta.setEnabled(false);
		textFieldPais.setText(null);
		textFieldEmail.setText(null);
		textFieldCP.setText(null);
		textFieldNombre.setText(null);
	}

	protected void conectar() throws Exception {
		BufferedReader bfr = new BufferedReader(new FileReader(new File("CFG.INI")));
			tabla = bfr.readLine();
			bd = bfr.readLine();
			IP = bfr.readLine();
			usu = bfr.readLine();
			pass = bfr.readLine();
		bfr.close();
		p = new PersistenciaBD();
		p.conectarDB(IP, usu, pass, bd);

	}
}
