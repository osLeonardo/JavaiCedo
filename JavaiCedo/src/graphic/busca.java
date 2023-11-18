package graphic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class busca extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTable table;

	public busca() {
		setSize(540, 540);
		setTitle("Searching");
		getContentPane().setLayout(null);
		
		JLabel lblBusca = new JLabel("Buscar:");
		lblBusca.setBounds(30, 11, 40, 25);
		getContentPane().add(lblBusca);
		
		textField = new JTextField();
		textField.setBounds(70, 11, 354, 25);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 47, 120, 25);
		getContentPane().add(textField_1);
		
		JLabel lblSearch_1 = new JLabel("Código:");
		lblSearch_1.setBounds(30, 47, 40, 25);
		getContentPane().add(lblSearch_1);
		
		JLabel lblSearch_2 = new JLabel("Cidade:");
		lblSearch_2.setBounds(200, 47, 45, 25);
		getContentPane().add(lblSearch_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(240, 47, 184, 25);
		getContentPane().add(textField_2);
		
		JButton btnProcess = new JButton((Action) null);
		btnProcess.setText("+");
		btnProcess.setBounds(240, 119, 40, 25);
		getContentPane().add(btnProcess);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(434, 11, 80, 25);
		getContentPane().add(btnSearch);
		
		JLabel lblSearch_2_1 = new JLabel("(ORIGEM)");
		lblSearch_2_1.setBounds(434, 47, 54, 25);
		getContentPane().add(lblSearch_2_1);
		
		JLabel lblSearch_2_1_1 = new JLabel("(DESTINO)");
		lblSearch_2_1_1.setBounds(434, 83, 54, 25);
		getContentPane().add(lblSearch_2_1_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(240, 83, 184, 25);
		getContentPane().add(textField_3);
		
		JLabel lblSearch_2_2 = new JLabel("Cidade:");
		lblSearch_2_2.setBounds(200, 83, 45, 25);
		getContentPane().add(lblSearch_2_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(70, 83, 120, 25);
		getContentPane().add(textField_4);
		
		JLabel lblSearch_1_1 = new JLabel("Código:");
		lblSearch_1_1.setBounds(30, 83, 40, 25);
		getContentPane().add(lblSearch_1_1);
		
		textField_6 = new JTextField();
		textField_6.setBounds(70, 119, 120, 25);
		getContentPane().add(textField_6);
		
		JLabel lblSearch_1_2 = new JLabel("Distância:");
		lblSearch_1_2.setBounds(20, 119, 50, 25);
		getContentPane().add(lblSearch_1_2);
		
		JButton btnProcessar = new JButton("Processar");
		btnProcessar.setBounds(434, 464, 80, 25);
		getContentPane().add(btnProcessar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalvar.setBounds(344, 465, 80, 25);
		getContentPane().add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 155, 504, 300);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		table.setBackground(new Color(230, 230, 230));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo Origem", "Cidade Origem", "C\u00F3digo Destino", "Cidade Destino", "Dist\u00E2ncia"
			}
		));
		table.getColumnModel().getColumn(4).setResizable(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createComponents();
	}

	private void createComponents() {}
}