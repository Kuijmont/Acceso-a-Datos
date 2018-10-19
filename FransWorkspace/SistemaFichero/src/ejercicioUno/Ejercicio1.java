package ejercicioUno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comun.Utilidades;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Ejercicio1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRuta;
	private JTextField textFieldBytes;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnBuscar;
	private JTextArea textAreaResultado;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JCheckBox chckbxIncluirOcultos;
	private JCheckBox chckbxIncluirSubcarpetas;
	private JFrame ventanaGUI=this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio1 frame = new Ejercicio1();
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
	public Ejercicio1() {
		setTitle("Buscar por tamaño");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeleccionarCarpeta = new JButton("Seleccionar Carpeta");
		btnSeleccionarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonSeleccionar();
			}
		});
		btnSeleccionarCarpeta.setBounds(32, 24, 177, 25);
		contentPane.add(btnSeleccionarCarpeta);
		
		textFieldRuta = new JTextField();
		textFieldRuta.setEditable(false);
		textFieldRuta.setBounds(237, 27, 371, 19);
		contentPane.add(textFieldRuta);
		textFieldRuta.setColumns(10);
		
		JLabel lblTamaoEnByes = new JLabel("Tamaño en bytes");
		lblTamaoEnByes.setBounds(32, 76, 136, 15);
		contentPane.add(lblTamaoEnByes);
		
		textFieldBytes = new JTextField();
		textFieldBytes.setBounds(172, 74, 114, 19);
		contentPane.add(textFieldBytes);
		textFieldBytes.setColumns(10);
		
		rdbtnNewRadioButton = new JRadioButton("Mayores");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(313, 72, 114, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Menores");
		rdbtnNewRadioButton_1.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(313, 104, 114, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		chckbxIncluirOcultos = new JCheckBox("Incluir ocultos");
		chckbxIncluirOcultos.setBounds(446, 72, 129, 23);
		contentPane.add(chckbxIncluirOcultos);
		
		chckbxIncluirSubcarpetas = new JCheckBox("Incluir subcarpetas");
		chckbxIncluirSubcarpetas.setBounds(446, 104, 162, 23);
		contentPane.add(chckbxIncluirSubcarpetas);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonBuscar();
			}
		});
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(611, 71, 117, 25);
		contentPane.add(btnBuscar);
		
		JLabel lblResultadosDeLa = new JLabel("Resultados de la busqueda");
		lblResultadosDeLa.setBounds(32, 164, 233, 15);
		contentPane.add(lblResultadosDeLa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 191, 663, 128);
		contentPane.add(scrollPane);
		
		textAreaResultado = new JTextArea();
		scrollPane.setViewportView(textAreaResultado);
	}

	protected void botonSeleccionar() {
		JFileChooser fc=new JFileChooser(".");
		fc.setDialogTitle("Seleccione carpeta donde buscar");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int seleccion = fc.showOpenDialog(ventanaGUI);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			if(fc.getSelectedFile().isDirectory()) {
				textFieldRuta.setText(fc.getSelectedFile().getAbsolutePath());
				btnBuscar.setEnabled(true);
		
			}else {
				Utilidades.notificaError(this, "Error en la selección de la carpeta", null, fc.getSelectedFile().getAbsolutePath() + " no existe.");
				//Al saltar el error de seleccionar incorrectamente el directorio, la aplicación finaliza
				System.exit(-1);
			}
		}
	}

	protected void botonBuscar() {
		textAreaResultado.setText("");
		try {
			if(Long.valueOf(textFieldBytes.getText())<0)
				Utilidades.notificaError(this, "Error de Bytes", null, "Bytes negativos no son válidos");
			else {
				ArrayList<File> res=Utilidades.buscarArchivosPorTamanio(new File(textFieldRuta.getText()), Long.valueOf(textFieldBytes.getText()), rdbtnNewRadioButton.isSelected()?'+':'-', chckbxIncluirOcultos.isSelected(), chckbxIncluirSubcarpetas.isSelected());	
				if (res!=null){
					for (int i = 0; i < res.size(); i++) {
						textAreaResultado.setText(textAreaResultado.getText()+Utilidades.mostrarInfoFileTam(res.get(i))+"\n");
					}
					JOptionPane.showMessageDialog(ventanaGUI, res.size()+" archivos encontrados.");
				}
			}
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()+ "\n===================\nError de tamanio de bytes." , "Asegurese de haber escrito correctamente el tamanio."
					, JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
}
