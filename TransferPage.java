import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
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

    // Creating some swing components
    JComboBox<String> comboBox = new JComboBox<>();
    JTextField textField = new JTextField();

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    JLabel label, label1, label2;
    JButton submitButton,BackButton;

    TransferPage(String Account_Number) {
        String query = "select * from user where Account_Number=" + Account_Number;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // creating a label
        JLabel label = new JLabel("TRANSACTION ");
        JLabel label1 = new JLabel("Amount to be transffered :");
        JLabel label2 = new JLabel("Choose Account: ");

        setLayout(layout);

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

            // Adding account choice and amount along submit button to complete the
            submitButton = new JButton("Submit ");
            BackButton=new JButton("Back");
            submitButton.addActionListener(this);
            BackButton.addActionListener(this);

            // transaction
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
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;
            constraints.insets = new Insets(5, 15, 10, 20);



            // adding the components to the frame
            addComponent(label, 0, 0, 1, 1); // Span across 2 columns
            
            // Add the table right below the label
            addComponent(table, 0, 1, 2, 1); // Span across 2 columns

            // Add the text field and combo box side by side below the table
            addComponent(textField, 0, 3, 1, 1); // Text field on the left
            addComponent(label1, 0, 2, 1, 1);
            addComponent(comboBox, 1, 3, 1, 1); // Combo box on the right
            addComponent(label2, 1, 2, 1, 1);
            
            // Add the submit button below, centered by spanning across both columns
            addComponent(submitButton, 1, 4, 1, 1); // Span across 2 columns
            addComponent(BackButton, 0, 4, 1, 1);


           

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addComponent(Component component,
            int row,
            int column,
            int width,
            int height) {
        // Check if the component is null
        if (component == null) {
            // Print an error message and return
            System.err.println("Cannot add a null component");
            return;
        }
        constraints.gridx = row;
        constraints.gridy = column;
        constraints.gridheight = height;
        constraints.gridwidth = width;

        layout.setConstraints(component, constraints); // set the constraints on the component

        add(component);
    }

    public void actionPerformed(ActionEvent e) {

        // storing the values in the box as an array of individual elements
        String selectedItem = (String) comboBox.getSelectedItem();
        String[] words = selectedItem.split(", ");
        System.out.println(words[1]);

        // accessing the value to be transffered to the user
        String amount = textField.getText();

        // implementing transaciton while money transfer
        try {
            Connection connection2 = DriverManager.getConnection(dbURL, username, password);
            connection2.setAutoCommit(false);

            try {

                Statement statement3 = connection2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                statement3.executeUpdate(
                        "UPDATE `user` SET `Initial_Balance`=`Initial_Balance`+" + amount + " WHERE `Account_Number`="
                                + words[1]);
                statement3.executeUpdate("UPDATE `user` SET `Initial_Balance`=`Initial_Balance`-" + amount
                        + " WHERE `Account_Number`=" + AccountNumber);

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
