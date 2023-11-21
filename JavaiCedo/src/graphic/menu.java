package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class menu extends JFrame {

	private static final long serialVersionUID = 1L;

	public menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 600);
		setLocationRelativeTo(null);

		JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
		JLabel rotulo = new JLabel("Dijkstra-route-files");
		rotulo.setHorizontalAlignment(SwingConstants.CENTER);
		rotulo.setFont(new Font("Arial", Font.BOLD, 24));

		JButton botaoConfig = new JButton("Settings");
		botaoConfig.setFont(new Font("Arial", Font.PLAIN, 14));
		botaoConfig.setBackground(new Color(51, 153, 255));
		botaoConfig.setForeground(Color.WHITE);
		botaoConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new config().setVisible(true);
			}
		});

		JButton botaoSair = new JButton("Exit");
		botaoSair.setFont(new Font("Arial", Font.PLAIN, 14));
		botaoSair.setBackground(new Color(255, 51, 51));
		botaoSair.setForeground(Color.WHITE);
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton botaoRota = new JButton("Find Route");
		botaoRota.setFont(new Font("Arial", Font.PLAIN, 14));
		botaoRota.setBackground(new Color(0, 204, 102));
		botaoRota.setForeground(Color.WHITE);
		botaoRota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new busca().setVisible(true);
			}
		});

		JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
		painelBotoes.add(botaoRota);
		painelBotoes.add(botaoConfig);
		painelBotoes.add(botaoSair);

		painelPrincipal.add(rotulo, BorderLayout.CENTER);
		painelPrincipal.add(painelBotoes, BorderLayout.EAST);

		setContentPane(painelPrincipal);
	}

	public static void main(String[] args) {
		Boolean firstTime = true;
		
		SwingUtilities.invokeLater(() -> {
			menu tela = new menu();
			tela.setVisible(true);
			
			if (firstTime) {
				new config().setVisible(true);
			}
		});
	}
}

