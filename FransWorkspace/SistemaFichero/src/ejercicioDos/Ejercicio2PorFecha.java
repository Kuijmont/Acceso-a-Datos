package ejercicioDos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Ejercicio2PorFecha extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRuta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio2PorFecha frame = new Ejercicio2PorFecha();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ejercicio2PorFecha() {
		setTitle("Buscar por fecha de modificación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeleccionarCarpeta = new JButton("Seleccionar Carpeta");
		btnSeleccionarCarpeta.setBounds(12, 12, 177, 25);
		contentPane.add(btnSeleccionarCarpeta);
		
		textFieldRuta = new JTextField();
		textFieldRuta.setEditable(false);
		textFieldRuta.setBounds(207, 15, 375, 19);
		contentPane.add(textFieldRuta);
		textFieldRuta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 78, 70, 15);
		contentPane.add(lblNewLabel);
	}
}
