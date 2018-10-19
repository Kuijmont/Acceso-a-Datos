package conversorPalabra;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comun.Utilidades;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConversorCadena extends JFrame {

	/**
	 * Realizar  un  progama  con  interfaz  gráfico como el mostrado  a  continuación  que  permita,
	 * utilizando  la  clase  para acceso directo a ficheros RandomAccessFile, 
	 * seleccionar un archivo de texto y actualizar su contenido de tal manera que 
	 * se conviertan a mayúsculas todas las ocurrencias de la cadena indicada.
	 */
	
	private JPanel contentPane;
	private JTextField textField;
	private File ruta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorCadena frame = new ConversorCadena();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); //Ventana salga centralizada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConversorCadena() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeleccionarTexto = new JButton("Seleccionar Texto");
		btnSeleccionarTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					botonSeleccionar();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		btnSeleccionarTexto.setBounds(160, 37, 207, 38);
		contentPane.add(btnSeleccionarTexto);
		
		JButton btnConvertir = new JButton("CONVERTIR");
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conversor(ruta);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConvertir.setBounds(160, 183, 207, 38);
		contentPane.add(btnConvertir);
		
		JLabel lblCadenaAConvertir = new JLabel("Cadena a convertir");
		lblCadenaAConvertir.setBounds(12, 127, 161, 15);
		contentPane.add(lblCadenaAConvertir);
		
		textField = new JTextField();
		textField.setBounds(160, 125, 249, 19);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	protected void botonSeleccionar() throws IOException {
		JFileChooser fc=new JFileChooser("/home/alumno/Pruebas");
		fc.setDialogTitle("Seleccione fichero de texto");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = fc.showOpenDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			if(fc.getSelectedFile().isFile()) {
				ruta = new File(fc.getSelectedFile().getAbsolutePath());
				
			}else {
				Utilidades.notificaError(this, "Error en la selección del fichero", null, fc.getSelectedFile().getAbsolutePath() + " no es fichero.");
			}
		}
	}

	
	//Busca las palabras y convierte
	private void conversor(File ruta) throws IOException {
		String palabra = textField.getText();
		RandomAccessFile fichero = new RandomAccessFile(ruta, "rw");
		String linea,minusc = null;
		int indice;
		String [] campos = null;
		
		while((linea = fichero.readLine())!=null) {
			String frase = "";
			StringBuilder auxBuilder;
			indice = linea.indexOf(palabra);
			 long pos = 0;
			String cadena;
			 cadena = fichero.readLine(); 
			 while(cadena!=null){
				 indice = cadena.indexOf(palabra); 
			 
				while(indice!=-1) {
					auxBuilder = new StringBuilder(cadena);
		            auxBuilder.replace(indice, indice+palabra.length(), palabra.toLowerCase());
		            cadena = auxBuilder.toString();
		            fichero.seek(pos);
		            fichero.writeBytes(cadena);
		            indice = cadena.indexOf(palabra);
				}
				pos = fichero.getFilePointer();
				cadena = fichero.readLine();
			 }
			//https://alejandromunyozsegrera.wordpress.com/2013/10/24/acceso-a-ficheros-desde-java/
//			fichero.seek(fichero.getFilePointer() - (linea.length()+1));
//			fichero.writeBytes(frase+"\n");
		
		}
			
		fichero.close();
	}
}
