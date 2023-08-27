
import javax.print.DocFlavor.STRING;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
// import java.util.Scanner;

public class CreateUser implements ActionListener {
    String dbURL = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "Sad@qls1610-";
    String query = "SELECT * FROM user";
    JButton button;
    CreateUser() {

        JFrame frame = new JFrame();
        JLabel label = new JLabel("Create New User ");
        JLabel label1 = new JLabel("First Name");
        JLabel label2 = new JLabel("Middle Name");
        JLabel label3 = new JLabel("Last Name");
        JLabel label4 = new JLabel("Email");
        JLabel label5 = new JLabel("Initial Balance ");
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();

        JTextField textField = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();
        
        button = new JButton("Create");
        button.addActionListener(this);
        

        JComboBox comboBox = new JComboBox<>();

        label.setPreferredSize(new Dimension(500, 50));

        // Integration along the panel

        panel.add(label1, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);

        panel2.add(label2, BorderLayout.WEST);
        panel2.add(textField2, BorderLayout.CENTER);

        panel3.add(label3, BorderLayout.WEST);
        panel3.add(textField3, BorderLayout.CENTER);

        panel4.add(label4, BorderLayout.WEST);
        panel4.add(textField4, BorderLayout.CENTER);

        panel5.add(label5, BorderLayout.WEST);
        panel5.add(textField5, BorderLayout.CENTER);

        panel6.add(button, BorderLayout.WEST);

        button.setPreferredSize(new Dimension(200, 40));
        textField.setPreferredSize(new Dimension(400, 40));
        textField2.setPreferredSize(new Dimension(400, 40));
        textField3.setPreferredSize(new Dimension(400, 40));
        textField4.setPreferredSize(new Dimension(400, 40));
        textField5.setPreferredSize(new Dimension(400, 40));

        // frame.getContentPane().setBackground(new Color(222, 232, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(label, BorderLayout.CENTER);
        frame.add(panel);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
            try {
                String Name;
                String Email;
                int Initial_Balance;
                String sex;
                Connection conn = DriverManager.getConnection(dbURL, username, password);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                // // Accept name
                // Scanner scn = new Scanner(System.in);

                // System.out.println("insert the name of the person ");
                // Name = scn.nextLine();

                // // accept email
                // System.out.println("Insert Email ");
                // Email = scn.nextLine();

                // // accept initial amount
                // System.out.println("Insert Balance ");
                // Initial_Balance = scn.nextInt();

                // // insert sex
                // System.out.println("Insert sex ");
                // String Sex = scn.nextLine();

                // String queryString = "INSERT INTO user (Name, Email, Initial_Balance,Sex)
                // VALUES ('" + Name + "', '"
                // + Email + "', '" + Initial_Balance + "', '" + Sex + "')";

                // int rowsAffected = stmt.executeUpdate(queryString);

            } catch (Exception exception) {

                exception.printStackTrace();
            }

        }

    }
}
