package ejercicioUno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class EjercicioPrueba extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRuta;
	private JTextField textFieldBytes;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnBuscar;
	private JTextArea textAreaResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjercicioPrueba frame = new EjercicioPrueba();
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
	public EjercicioPrueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeleccionarCarpeta = new JButton("Seleccionar Carpeta");
		btnSeleccionarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File fichero = seleccionarCarpeta();
					btnBuscar.setEnabled(true);
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Mayores");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(313, 72, 114, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Menores");
		rdbtnNewRadioButton_1.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(313, 104, 114, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JCheckBox chckbxIncluirOcultos = new JCheckBox("Incluir ocultos");
		chckbxIncluirOcultos.setBounds(446, 72, 129, 23);
		contentPane.add(chckbxIncluirOcultos);
		
		JCheckBox chckbxIncluirSubcarpetas = new JCheckBox("Incluir subcarpetas");
		chckbxIncluirSubcarpetas.setBounds(446, 104, 162, 23);
		contentPane.add(chckbxIncluirSubcarpetas);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero = new File(textFieldRuta.getText());;
				String tamaño=textFieldBytes.getText();
				int bytes=0;
				char criterio='+';
				boolean incluirOcultos=false;
				boolean incluirSubcarpetas =false;
				try {
					bytes = Integer.parseInt(tamaño);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error de tamanio de bytes", "Asegurese de haber escrito correctamente el tamanio. \n"
							+ e2.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				if (rdbtnNewRadioButton.isSelected()) {
					criterio='+';
				} else {
					criterio='-';
				}
				if (chckbxIncluirOcultos.isSelected()) {
					incluirOcultos=true;
				} else {
					incluirOcultos=false;
				}
				if (chckbxIncluirSubcarpetas.isSelected()) {
					incluirSubcarpetas=true;
				} else {
					incluirSubcarpetas=false;
				}
				ArrayList<File> ArchivosFiltrados = buscarArchivosPorTamanio (fichero, bytes, criterio, incluirOcultos,incluirSubcarpetas);
				String texto = "";
				for (int i = 0; i < ArchivosFiltrados.size(); i++) {
					texto =texto+ ArchivosFiltrados.get(i).getAbsolutePath() +" ("+ArchivosFiltrados.get(i).length()+")\n";
				}
				textAreaResultado.setText(texto);
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

	protected ArrayList<File> buscarArchivosPorTamanio(File fichero, int bytes, char criterio, boolean incluirOcultos,
			boolean incluirSubcarpetas) {
		File[] lista= fichero.listFiles();
		ArrayList <File> archivosFiltro = new ArrayList<File>();
		if (criterio=='+' && incluirOcultos==true && incluirSubcarpetas==true) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isFile()) {
					archivosFiltro.add(lista[i]);
				}
				if (lista[i].length()>bytes && (!lista[i].isHidden()) && lista[i].isFile()) {
					archivosFiltro.add(lista[i]);
				}
				if (lista[i].length()>bytes && (!lista[i].isHidden()) && lista[i].isDirectory()) {
					archivosFiltro.add(lista[i]);
					buscarArchivosPorTamanio(lista[i], bytes, criterio, incluirOcultos, incluirSubcarpetas);
				}
			
				}
			}
		if (criterio=='+' && incluirOcultos==true && incluirSubcarpetas==false) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isFile()) {
					archivosFiltro.add(lista[i]);
				}
				if (lista[i].length()>bytes && (!lista[i].isHidden()) && lista[i].isFile()) {
					archivosFiltro.add(lista[i]);
				}
				if (lista[i].length()>bytes && (!lista[i].isHidden()) && lista[i].isDirectory()) {
					archivosFiltro.add(lista[i]);
				}
			}
		}
		if (criterio=='+' && incluirOcultos==false && incluirSubcarpetas==false) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isDirectory()) {
						archivosFiltro.add(lista[i]);
					}
				}
			}
		if (criterio=='+' && incluirOcultos==false && incluirSubcarpetas==true) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()>bytes && lista[i].isHidden() && lista[i].isDirectory()) {
						archivosFiltro.add(lista[i]);
						buscarArchivosPorTamanio(lista[i], bytes, criterio, incluirOcultos, incluirSubcarpetas);
					}
				}
			}
			
		if (criterio=='-' && incluirOcultos==true && incluirSubcarpetas==true) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()<bytes && (!lista[i].isHidden()) && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()<bytes && (!lista[i].isHidden()) && lista[i].isDirectory()) {
						archivosFiltro.add(lista[i]);
						buscarArchivosPorTamanio(lista[i], bytes, criterio, incluirOcultos, incluirSubcarpetas);
					}
				
				}
			}
		if (criterio=='-' && incluirOcultos==true && incluirSubcarpetas==false) {
			for (int i = 0; i < lista.length; i++) {
				if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()<bytes && (!lista[i].isHidden()) && lista[i].isFile()) {
						archivosFiltro.add(lista[i]);
					}
				if (lista[i].length()<bytes && (!lista[i].isHidden()) && lista[i].isDirectory()) {
						archivosFiltro.add(lista[i]);
					}
				}
			}
			if (criterio=='-' && incluirOcultos==false && incluirSubcarpetas==false) {
				for (int i = 0; i < lista.length; i++) {
					if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isFile()) {
							archivosFiltro.add(lista[i]);
						}
					if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isDirectory()) {
							archivosFiltro.add(lista[i]);
						}
					}
				}
			if (criterio=='-' && incluirOcultos==false && incluirSubcarpetas==true) {
				for (int i = 0; i < lista.length; i++) {
					if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isFile()) {
							archivosFiltro.add(lista[i]);
						}
					if (lista[i].length()<bytes && lista[i].isHidden() && lista[i].isDirectory()) {
							archivosFiltro.add(lista[i]);
							buscarArchivosPorTamanio(lista[i], bytes, criterio, incluirOcultos, incluirSubcarpetas);
						}
					}
				}
				
			

		return archivosFiltro;
		}
		

	protected File seleccionarCarpeta() {
		File fichero = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int seleccion = fileChooser.showOpenDialog(contentPane);
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
		   fichero = fileChooser.getSelectedFile();
		   textFieldRuta.setText(fichero.getAbsolutePath());
		   
		}
		return fichero;
		
	}
}
