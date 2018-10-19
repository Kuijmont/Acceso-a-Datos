package ejercicioProfe;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import comun.Utilidades;

import java.awt.Font;




public class BuscarPorTamanioGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ruta;
	private JTextField tam;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMayores;
	private JTextArea textAreaRes;
	private JCheckBox chckbxIncluirOcultos;
	private JScrollPane scrollPane;
	private JFrame ventanaGUI=this;
	private JCheckBox chckbxIncluirSubcarpetas;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarPorTamanioGUI frame = new BuscarPorTamanioGUI();
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
	public BuscarPorTamanioGUI() {
		setTitle("Buscar por tama\u00F1o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Seleccionar Carpeta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonSeleccionar();
			}
		});
		btnNewButton.setBounds(10, 24, 194, 23);
		contentPane.add(btnNewButton);
		
		ruta = new JTextField();
		ruta.setEditable(false);
		ruta.setBounds(214, 25, 298, 20);
		contentPane.add(ruta);
		ruta.setColumns(10);
		
		JLabel lblTamaoEnBytes = new JLabel("Tama\u00F1o en bytes");
		lblTamaoEnBytes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTamaoEnBytes.setBounds(10, 76, 147, 14);
		contentPane.add(lblTamaoEnBytes);
		
		tam = new JTextField();
		tam.setText("0");
		tam.setBounds(153, 74, 86, 20);
		contentPane.add(tam);
		tam.setColumns(10);
		
		rdbtnMayores = new JRadioButton("Mayores");
		rdbtnMayores.setSelected(true);
		buttonGroup.add(rdbtnMayores);
		rdbtnMayores.setBounds(278, 72, 86, 23);
		contentPane.add(rdbtnMayores);
		
		JRadioButton rdbtnMenores = new JRadioButton("Menores");
		buttonGroup.add(rdbtnMenores);
		rdbtnMenores.setBounds(278, 102, 109, 23);
		contentPane.add(rdbtnMenores);
		
		chckbxIncluirOcultos = new JCheckBox("Incluir Ocultos");
		chckbxIncluirOcultos.setFont(new Font("Dialog", Font.BOLD, 12));
		chckbxIncluirOcultos.setBounds(403, 72, 136, 23);
		contentPane.add(chckbxIncluirOcultos);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonBuscar();
			}
		});
		btnBuscar.setBounds(547, 76, 108, 33);
		contentPane.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 170, 630, 266);
		contentPane.add(scrollPane);
		
		textAreaRes = new JTextArea();
		scrollPane.setViewportView(textAreaRes);
		
		JLabel lblResultadosDeLa = new JLabel("Resultados de la b\u00FAsqueda");
		lblResultadosDeLa.setBounds(25, 145, 244, 14);
		contentPane.add(lblResultadosDeLa);
		
		chckbxIncluirSubcarpetas = new JCheckBox("Incluir Subcarpetas");
		chckbxIncluirSubcarpetas.setFont(new Font("Dialog", Font.BOLD, 12));
		chckbxIncluirSubcarpetas.setBounds(403, 116, 176, 23);
		contentPane.add(chckbxIncluirSubcarpetas);
	}

	protected void botonSeleccionar() {
		JFileChooser fc=new JFileChooser(".");
		fc.setDialogTitle("Seleccione carpeta donde buscar");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int seleccion = fc.showOpenDialog(ventanaGUI);
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
			ruta.setText(fc.getSelectedFile().getAbsolutePath());
			btnBuscar.setEnabled(true);
		}
	}

	protected void botonBuscar() {
		textAreaRes.setText("");
		ArrayList<File> res=Utilidades.buscarArchivosPorTamanio(new File(ruta.getText()), Long.valueOf(tam.getText()), rdbtnMayores.isSelected()?'+':'-', chckbxIncluirOcultos.isSelected(), chckbxIncluirSubcarpetas.isSelected());	
		if (res!=null){
			for (int i = 0; i < res.size(); i++) {
				textAreaRes.setText(textAreaRes.getText()+Utilidades.mostrarInfoFileTam(res.get(i))+"\n");
			}
			JOptionPane.showMessageDialog(ventanaGUI, res.size()+" archivos encontrados.");
		}
	}
}
