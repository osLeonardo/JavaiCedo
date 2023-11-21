package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import config.ConfigFile;
import files.FileProcessor;

import javax.swing.Action;

public class config extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lblRootDiretory;
	private JLabel lblSucesso;
	private JLabel lblErro;
	private JLabel lblCheck;
	private JTextField txtRootDireotry;
	private JTextField txtSucesso;
	private JTextField txtErro;
	private JButton btnSalvar;
	private JCheckBox chkRota;

	public config() {
		setSize(400,320);
		setTitle("Settings");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		criarComponentes();
	}

	public void criarComponentes (){
		lblRootDiretory = new JLabel("Directory:");
		lblRootDiretory.setBounds(10, 10, 80, 25);
		getContentPane().add(lblRootDiretory);

		txtRootDireotry = new JTextField();
		txtRootDireotry.setBounds(80, 10, 300, 25);
		getContentPane().add(txtRootDireotry);

		lblSucesso = new JLabel("Success:");
		lblSucesso.setBounds(10, 45, 95, 25);
		getContentPane().add(lblSucesso);

		txtSucesso = new JTextField();
		txtSucesso.setBounds(80, 45, 150, 25);
		getContentPane().add(txtSucesso);

		lblErro = new JLabel("Error:");
		lblErro.setBounds(10, 80, 60, 25);
		getContentPane().add(lblErro);

		txtErro = new JTextField();
		txtErro.setBounds(80, 80, 150, 25);
		getContentPane().add(txtErro);

		chkRota = new JCheckBox();
		chkRota.setBounds(80, 110, 20, 25);
		getContentPane().add(chkRota);

		lblCheck = new JLabel("Refresh");
		lblCheck.setBounds(100, 110, 120, 25);
		getContentPane().add(lblCheck);

		btnSalvar = new JButton("SALVAR");
		btnSalvar.setBounds(80, 140, 100, 25);	
		getContentPane().add(btnSalvar);

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ConfigFile configFile = new ConfigFile();
					FileProcessor fileProcessor = new FileProcessor();
					String diretory = txtRootDireotry.getText().trim();
					String success = txtSucesso.getText().trim();
					String error = txtErro.getText().trim();
					Boolean automaticDirectory = chkRota.isSelected();

					if(!automaticDirectory){
						if(diretory.length() > 0){
							configFile.createConfigFile(diretory, success, error);
						} else {
							configFile.createConfigFile();
						}
						fileProcessor.readConfigFile(ConfigFile.getConfigFilePath());
						fileProcessor.processFiles();
					} else {
						fileProcessor.processFiles();
					}

					JOptionPane.showMessageDialog(config.this,
							"Diretory: " + ConfigFile.getDestinationPath() +
									"\nSuccess: " + ConfigFile.getSuccessDirectoryName() +
									"\nError: " + ConfigFile.getFailedDirectoryName() +
									"\nRefresh: " + automaticDirectory);

					dispose();
				} catch (Exception ex){
					JOptionPane.showMessageDialog(null, "Directory not found", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}
}