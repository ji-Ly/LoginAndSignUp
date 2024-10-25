package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hly.utils.ConnectionProvider;
import com.hly.utils.MyToys;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtRePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 800, 500);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 400, 500);
		panel_1.setBackground(new Color(0, 102, 102));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/com/hly/Images/logo.png")));
		lblNewLabel.setBounds(138, 130, 100, 118);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("copyright \u00A9 company name All rights reserved");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(69, 420, 275, 18);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Be Nice My Friend");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(83, 285, 261, 32);
		panel_1.add(lblNewLabel_2);

		JPanel panel2 = new JPanel();
		panel2.setBounds(400, 0, 400, 500);
		panel2.setBackground(new Color(255, 255, 255));
		panel.add(panel2);
		panel2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("SIGN UP");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_3.setBounds(145, 31, 120, 32);
		panel2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Full Name");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_4.setBounds(31, 82, 282, 13);
		panel2.add(lblNewLabel_4);

		txtName = new JTextField();
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtName.setBounds(31, 105, 326, 31);
		panel2.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_5.setBounds(31, 146, 282, 13);
		panel2.add(lblNewLabel_5);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtEmail.setBounds(31, 169, 326, 32);
		panel2.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_6.setBounds(31, 211, 282, 13);
		panel2.add(lblNewLabel_6);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPassword.setBounds(31, 234, 326, 31);
		panel2.add(txtPassword);

		JLabel lblNewLabel_7 = new JLabel("Re-enter password");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_7.setBounds(31, 275, 282, 13);
		panel2.add(lblNewLabel_7);

		txtRePassword = new JPasswordField();
		txtRePassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtRePassword.setBounds(31, 298, 326, 32);
		panel2.add(txtRePassword);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String email = txtEmail.getText();
				String password = new String(txtPassword.getPassword());
				String rePassword = new String(txtRePassword.getPassword());
				List<String> list = new ArrayList<String>();
				int flag = 1;
				try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection
							.prepareStatement("select * from appuser where email = ?");
					preparedStatement.setString(1, email);
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						String emaill = rs.getString("email");
						list.add(emaill);
					}

					for (String string : list) {
						if (string.equals(email)) {
							flag = 0;
							JOptionPane.showMessageDialog(null, "Email has been existed");

						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				if (MyToys.getEmail(email) == false)
					JOptionPane.showMessageDialog(null, "Email must be regrex like : xxx@gmail.com");
				if (!password.equals(rePassword))
					JOptionPane.showMessageDialog(null, "Password must be the same");

				if (password.equals(rePassword) && MyToys.getEmail(email) == true) {
					
					if(flag == 1) {
						try {
							Connection connection = ConnectionProvider.getConnection();
							PreparedStatement preparedStatement = connection
									.prepareStatement("insert into appuser(name, email, password) values (?, ?, ?)");
							preparedStatement.setString(1, name);
							preparedStatement.setString(2, email);
							preparedStatement.setString(3, password);

							preparedStatement.executeUpdate();

							JOptionPane.showMessageDialog(null, "Sign Up Successfully");

							dispose();
							new SignUp().setVisible(true);

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}

			}
		});
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSignUp.setBackground(new Color(0, 102, 102));
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBounds(31, 351, 87, 32);
		panel2.add(btnSignUp);

		JLabel lblNewLabel_8 = new JLabel("I have an account");
		lblNewLabel_8.setBounds(31, 419, 107, 13);
		panel2.add(lblNewLabel_8);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.setForeground(new Color(255, 51, 51));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(160, 406, 73, 32);
		panel2.add(btnNewButton_1);
	}
}
