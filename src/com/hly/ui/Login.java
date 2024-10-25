package com.hly.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hly.utils.ConnectionProvider;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 800, 500);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 102, 102));
		panel_1.setBounds(0, 0, 400, 500);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/com/hly/Images/placeholder (1).png")));
		lblNewLabel_4.setBounds(75, 60, 233, 270);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("I'm Always Here With You");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe Script", Font.BOLD, 13));
		lblNewLabel_5.setBounds(115, 360, 208, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("copyright \u00A9 company name All rights reserved");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(76, 431, 247, 16);
		panel_1.add(lblNewLabel_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(400, 500));
		panel_2.setBounds(400, 0, 400, 500);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblNewLabel.setBounds(144, 69, 120, 48);
		lblNewLabel.setForeground(new Color(0, 102, 102));
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(35, 135, 330, 13);
		panel_2.add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail.setBounds(35, 158, 330, 31);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2.setBounds(35, 208, 330, 13);
		panel_2.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPassword.setBounds(35, 242, 330, 31);
		panel_2.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = new String(txtPassword.getPassword());
				HashMap<String, String> list = new HashMap<String, String>();
				try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("select * from appuser where email = ?");
					preparedStatement.setString(1, email);
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						String emaill = rs.getString("email");
						String passwordd = rs.getString("password");
						
						list.put(emaill, passwordd);
					}
					int flag = 0;
					for (Map.Entry<String, String> entry: list.entrySet()) {
						System.out.println(entry.getKey() + " " + entry.getValue());
						if(email.equals(entry.getKey()) && entry.getValue().equals(password)) {
							System.out.println(entry.getValue());
							System.out.println(password);
							flag = 1;
							JOptionPane.showMessageDialog(null, "Login successfully");
							dispose();
							new Login().setVisible(true);
						}
						
						}
					if(flag == 0) JOptionPane.showMessageDialog(null, "Account does not exist");
					
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 102, 102));
		btnLogin.setBounds(35, 298, 120, 36);
		btnLogin.setFocusable(false);
		panel_2.add(btnLogin);
		
		JLabel lblNewLabel_3 = new JLabel("I don't have an account");
		lblNewLabel_3.setBounds(35, 372, 141, 13);
		panel_2.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp().setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 51, 51));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(186, 358, 120, 36);
		panel_2.add(btnNewButton_1);
	}
}
