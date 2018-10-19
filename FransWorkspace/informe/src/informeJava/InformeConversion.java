package informeJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformeConversion extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDec;
	private JTextField textFieldOct;
	private JTextField textFieldHex;
	private JTextField textFieldBin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeConversion frame = new InformeConversion();
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
	public InformeConversion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroDecimal = new JLabel("Numero Decimal");
		lblNumeroDecimal.setBounds(67, 36, 122, 15);
		contentPane.add(lblNumeroDecimal);
		
		JLabel lblNumeroOctal = new JLabel("Numero Octal");
		lblNumeroOctal.setBounds(67, 73, 122, 15);
		contentPane.add(lblNumeroOctal);
		
		JLabel lblNumeroHexadecimal = new JLabel("Numero Hexadecimal");
		lblNumeroHexadecimal.setBounds(67, 111, 158, 15);
		contentPane.add(lblNumeroHexadecimal);
		
		JLabel lblNumeroBinario = new JLabel("Numero Binario");
		lblNumeroBinario.setBounds(67, 151, 122, 15);
		contentPane.add(lblNumeroBinario);
		
		textFieldDec = new JTextField();
		textFieldDec.setBounds(265, 34, 114, 19);
		contentPane.add(textFieldDec);
		textFieldDec.setColumns(10);
		
		textFieldOct = new JTextField();
		textFieldOct.setBounds(265, 71, 114, 19);
		contentPane.add(textFieldOct);
		textFieldOct.setColumns(10);
		
		textFieldHex = new JTextField();
		textFieldHex.setBounds(265, 109, 114, 19);
		contentPane.add(textFieldHex);
		textFieldHex.setColumns(10);
		
		textFieldBin = new JTextField();
		textFieldBin.setBounds(265, 149, 114, 19);
		contentPane.add(textFieldBin);
		textFieldBin.setColumns(10);
		
		JButton btnConvertirDec = new JButton("Convertir");
		btnConvertirDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dec = textFieldDec.getText();
				int decimal = Integer.valueOf(dec);
				String octal = Integer.toOctalString(decimal);
				String hexadecimal = Integer.toHexString(decimal);
				String binario = Integer.toBinaryString(decimal);
				textFieldOct.setText(octal);
				textFieldHex.setText(hexadecimal);
				textFieldBin.setText(binario);
			}
		});
		btnConvertirDec.setBounds(394, 31, 117, 25);
		contentPane.add(btnConvertirDec);
		
		JButton btnConvertirOct = new JButton("Convertir");
		btnConvertirOct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oct = textFieldOct.getText();
				int decimal = Integer.parseInt(oct,8);
				String hexadecimal = Integer.toHexString(decimal);
				String binario = Integer.toBinaryString(decimal);
				textFieldDec.setText(String.valueOf(decimal));
				textFieldHex.setText(hexadecimal);
				textFieldBin.setText(binario);
				
			}
		});
		btnConvertirOct.setBounds(394, 68, 117, 25);
		contentPane.add(btnConvertirOct);
		
		JButton btnConvertirHex = new JButton("Convertir");
		btnConvertirHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hex = textFieldHex.getText();
				int decimal = Integer.parseInt(hex,16);
				String octal = Integer.toOctalString(decimal);
				String binario = Integer.toBinaryString(decimal);
				textFieldDec.setText(String.valueOf(decimal));
				textFieldOct.setText(octal);
				textFieldBin.setText(binario);
			}
		});
		btnConvertirHex.setBounds(394, 106, 117, 25);
		contentPane.add(btnConvertirHex);
		
		JButton btnConvertirBin = new JButton("Convertir");
		btnConvertirBin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bin = textFieldBin.getText();
				int decimal = Integer.parseInt(bin,2);
				String octal = Integer.toOctalString(decimal);
				String hexadecimal = Integer.toHexString(decimal);
				textFieldDec.setText(String.valueOf(decimal));
				textFieldOct.setText(octal);
				textFieldHex.setText(hexadecimal);
			}
			
		});
		btnConvertirBin.setBounds(394, 146, 117, 25);
		contentPane.add(btnConvertirBin);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldBin.setText(null);
				textFieldDec.setText(null);
				textFieldHex.setText(null);
				textFieldOct.setText(null);
				
			}
		});
		btnLimpiar.setBounds(197, 204, 117, 25);
		contentPane.add(btnLimpiar);
	}


}
