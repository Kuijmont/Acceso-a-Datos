package ej1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class InterfazUsuarioGUI extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnEmail;

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
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(22, 222, 117, 25);
		contentPane.add(btnMostrar);
		
		JRadioButton rdbtnDefecto = new JRadioButton("Por Defecto");
		rdbtnDefecto.setBounds(22, 72, 124, 23);
		contentPane.add(rdbtnDefecto);
		
		JRadioButton rdbtnCp = new JRadioButton("Por CP");
		rdbtnCp.setBounds(21, 99, 149, 23);
		contentPane.add(rdbtnCp);
		
		rdbtnEmail = new JRadioButton("Por Email");
		rdbtnEmail.setBounds(22, 126, 149, 23);
		contentPane.add(rdbtnEmail);
		
		JRadioButton rdbtnNombre = new JRadioButton("Por Nombre");
		rdbtnNombre.setBounds(22, 153, 149, 23);
		contentPane.add(rdbtnNombre);
		
		JRadioButton rdbtnPais = new JRadioButton("Por País");
		rdbtnPais.setBounds(22, 180, 149, 23);
		contentPane.add(rdbtnPais);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(22, 12, 117, 25);
		contentPane.add(btnConectar);
	}
}
