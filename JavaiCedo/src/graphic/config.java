package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Action;

public class config extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public config() {
		setSize(339,190);
		setTitle("Settings");
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		criarComponentes();
	}

	public void criarComponentes (){
		
		JLabel lblSearch = new JLabel("Raiz:");
		lblSearch.setBounds(20, 11, 49, 25);
		getContentPane().add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(70, 11, 231, 25);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 47, 231, 25);
		getContentPane().add(textField_1);
		
		JLabel lblSearch_1 = new JLabel("Sucesso:");
		lblSearch_1.setBounds(20, 47, 49, 25);
		getContentPane().add(lblSearch_1);
		
		JLabel lblSearch_2 = new JLabel("Erro:");
		lblSearch_2.setBounds(20, 83, 49, 25);
		getContentPane().add(lblSearch_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(70, 83, 231, 25);
		getContentPane().add(textField_2);
		
		JButton btnProcess = new JButton((Action) null);
		btnProcess.setText("Processar");
		btnProcess.setBounds(221, 114, 80, 25);
		getContentPane().add(btnProcess);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(131, 114, 80, 25);
		getContentPane().add(btnSearch);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Rota Automática");
		chckbxNewCheckBox.setBounds(20, 115, 105, 23);
		getContentPane().add(chckbxNewCheckBox);
	}
}