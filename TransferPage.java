import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;
import java.util.jar.Attributes.Name;

public class TransferPage extends JFrame implements ActionListener {
    String dbURL = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "Sad@qls1610-";
    String queryString = "select * from user";
    String Name;
    String Email;
    String AccountNumber;
    String Sex;
    String Balance;
    JComboBox<String> comboBox = new JComboBox<>();
    JTextField textField = new JTextField();

    TransferPage(String Account_Number) {
        String query = "select * from user where Account_Number=" + Account_Number;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        JLabel label = new JLabel("TRANSACTION ");
        add(label, BorderLayout.CENTER);

        try (

                Connection connection = DriverManager.getConnection(dbURL, username, password);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = statement.executeQuery(query);

            // accessing the database that's kept under that Account Number
            while (rs.next()) {
                Name = rs.getString("Name");
                Email = rs.getString("Email");
                AccountNumber = rs.getString("Account_Number");
                Sex = rs.getString("Sex");
                Balance = rs.getString("Initial_Balance");
            }

            // appending the values retireved from the database to the Table
            Object[][] data = {
                    { "AccountNumber", "Name", "Initial_Balance" },
                    { AccountNumber, Name, Balance }
            };
            String[] columnNames = { "First Name", "Last Name", "Age" };
            JTable table = new JTable(data, columnNames);
            table.setBounds(20, 70, 450, 50);
            add(table);

            // Adding account choice and amount along submit button to complete the
            // transaction

            
            JButton submitButton = new JButton("Submit ");

            submitButton.addActionListener(this);
            submitButton.setBounds(100, 200, 100, 40);
            textField.setPreferredSize(new Dimension(250, 40));
            textField.setBounds(20, 150, 200, 40);

            // Creating a Combobox to choose an account to trasfer into
            
            comboBox.setBounds(20, 250, 200, 40);
            try {
                Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rSet = statement2.executeQuery(queryString);
                while (rSet.next()) {
                    Name = rSet.getString("Name");
                    AccountNumber = rSet.getString("Account_Number");
                    comboBox.addItem(Name + ", " + AccountNumber);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // adding the components to the frame
            add(submitButton);
            add(textField);
            add(comboBox);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {

        // storing the values in the box as an array of individual elements
        String selectedItem = (String) comboBox.getSelectedItem();
        String[] words = selectedItem.split(", ");
        System.out.println(words[1]);


        // accessing the value to be transffered to the user
        String amount=textField.getText();


        // implementing transaciton while money transfer
        try {
            Connection connection2 = DriverManager.getConnection(dbURL, username, password);
            connection2.setAutoCommit(false);

            try {

                Statement statement3 = connection2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                
                statement3.executeUpdate(
                        "UPDATE `user` SET `Initial_Balance`=`Initial_Balance`+"+amount+" WHERE `Account_Number`="+words[1] );
                statement3.executeUpdate("UPDATE `user` SET `Initial_Balance`=`Initial_Balance`-"+amount+" WHERE `Account_Number`="+AccountNumber );

                connection2.commit();
            } catch (Exception exception) {
                System.out.println("sdfd");
            } finally {
                connection2.close();
            }
        } catch (SQLException e1) {

        }

    }
}
