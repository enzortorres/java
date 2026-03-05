package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControleAnimal1;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroAnimal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEspecie;
	private JTextField textRaca;
	private ControleAnimal1 controleAnimal = new ControleAnimal1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAnimal frame = new CadastroAnimal();
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
	public CadastroAnimal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CADASTRO DE ANIMAL");
		lblNewLabel.setBounds(105, 10, 226, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NOME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 63, 73, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ESPÉCIE:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(25, 105, 93, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("RAÇA:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(25, 152, 73, 24);
		contentPane.add(lblNewLabel_1_2);
		
		textNome = new JTextField();
		textNome.setBounds(117, 63, 143, 24);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textEspecie = new JTextField();
		textEspecie.setColumns(10);
		textEspecie.setBounds(117, 105, 143, 24);
		contentPane.add(textEspecie);
		
		textRaca = new JTextField();
		textRaca.setColumns(10);
		textRaca.setBounds(117, 156, 143, 24);
		contentPane.add(textRaca);
		
		JButton btnCadastrarAnimal = new JButton("CADASTRAR");
		btnCadastrarAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textNome.getText();
				String especie = textEspecie.getText();
				String raca = textRaca.getText();
				
				controleAnimal.cadastrarAnimal(nome, especie, raca);
				
				// Para mostrar um alert na tela
				JOptionPane.showMessageDialog(null, "Animal cadastrado com sucesso!");
				
				textNome.setText("");
				textEspecie.setText("");
				textRaca.setText("");
			}
		});
		btnCadastrarAnimal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarAnimal.setBounds(152, 229, 124, 24);
		contentPane.add(btnCadastrarAnimal);

	}
}
