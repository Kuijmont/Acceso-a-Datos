package ejercicioDos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comun.Utilidades;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Ejercicio2 extends JFrame {

	private JFrame VentanaGUI;
	private JPanel contentPane;
	private JTextField textFieldRuta;
	private JTextField textFieldFecha;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio2 frame = new Ejercicio2();
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
	public Ejercicio2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeleccionar = new JButton("Seleccionar Carpeta");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc=new JFileChooser(".");
				fc.setDialogTitle("Seleccione carpeta donde buscar");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int seleccion = fc.showOpenDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION)
				{
					textFieldRuta.setText(fc.getSelectedFile().getAbsolutePath());
					btnBuscar.setEnabled(true);
				}
			}
		});
		btnSeleccionar.setBounds(38, 28, 195, 25);
		contentPane.add(btnSeleccionar);
		
		textFieldRuta = new JTextField();
		textFieldRuta.setBounds(245, 31, 291, 19);
		contentPane.add(textFieldRuta);
		textFieldRuta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("DD-MM-AAAA");
		lblNewLabel.setBounds(38, 81, 102, 15);
		contentPane.add(lblNewLabel);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(158, 79, 114, 19);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JRadioButton rdbtnAntesDe = new JRadioButton("Antes de");
		rdbtnAntesDe.setSelected(true);
		buttonGroup.add(rdbtnAntesDe);
		rdbtnAntesDe.setBounds(280, 77, 114, 23);
		contentPane.add(rdbtnAntesDe);
		
		JRadioButton rdbtnDespuesDe = new JRadioButton("Despues de");
		buttonGroup.add(rdbtnDespuesDe);
		rdbtnDespuesDe.setBounds(280, 106, 119, 23);
		contentPane.add(rdbtnDespuesDe);
		
		JCheckBox chckbxIncluirSubcarpetas = new JCheckBox("Incluir Subcarpetas");
		chckbxIncluirSubcarpetas.setBounds(407, 106, 180, 23);
		contentPane.add(chckbxIncluirSubcarpetas);
		
		JCheckBox chckbxIncluirOcultos = new JCheckBox("Incluir Ocultos");
		chckbxIncluirOcultos.setBounds(407, 77, 129, 23);
		contentPane.add(chckbxIncluirOcultos);
		
		JLabel lblResultadosDeLa = new JLabel("Resultados de la busqueda");
		lblResultadosDeLa.setBounds(38, 152, 213, 15);
		contentPane.add(lblResultadosDeLa);
		
		JTextArea textAreaResultado = new JTextArea();
		textAreaResultado.setBounds(38, 179, 618, 127);
		contentPane.add(textAreaResultado);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaResultado.setText("");
				String fecha = textFieldFecha.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
				try {
					ArrayList<File> res=Utilidades.buscarArchivosPorFecha(new File(textFieldRuta.getText()), sdf.parse(fecha), rdbtnAntesDe.isSelected()?'+':'-', chckbxIncluirOcultos.isSelected(), chckbxIncluirSubcarpetas.isSelected());	
					if (res!=null){
						for (int i = 0; i < res.size(); i++) {
							textAreaResultado.setText(textAreaResultado.getText()+Utilidades.mostrarInfoFileTam(res.get(i))+"\n");
						}
						JOptionPane.showMessageDialog(null, res.size()+" archivos encontrados.");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					Utilidades.notificaError(VentanaGUI, "Error al buscar", e1, e1.getMessage());
				} 
			}
			}
		);
		btnBuscar.setBounds(544, 76, 117, 25);
		contentPane.add(btnBuscar);
	}
}
