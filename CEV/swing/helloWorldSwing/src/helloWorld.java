import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class helloWorld extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					helloWorld frame = new helloWorld();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame
	public helloWorld() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblMessage = new JLabel("Here will show the message!\r\n");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setBounds(60, 36, 307, 29);
		contentPane.add(lblMessage);
		
		JButton btn = new JButton("Click here");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText("Hello world");
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn.setBounds(151, 201, 131, 49);
		contentPane.add(btn);
	}
}
