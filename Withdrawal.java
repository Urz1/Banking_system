import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class Withdrawal extends JFrame implements ActionListener {
    String dbURL = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "Sad@qls1610-";
    String queryString = "select * from user";
    String Name;
    String Email;
    String AccountNumber;
    String Sex;
    String Balance;
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    JTextField textField = new JTextField(20);
    JButton submitButton, BackButton;
    JLabel label, label2;

    Withdrawal(String AccountNumber) {
        String query = "select * from user where Account_Number=" + AccountNumber;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(layout);
        label = new JLabel("Withdrawal ");
        label2 = new JLabel("Amount:");
        // add(label, BorderLayout.CENTER);

        try (
                Connection connection = DriverManager.getConnection(dbURL, username, password);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

        ) {
            ResultSet rs = statement.executeQuery(query);

            // accessing the database that's kept under that Account Number
            while (rs.next()) {
                Name = rs.getString("Name");
                Email = rs.getString("Email");
                this.AccountNumber = rs.getString("Account_Number");
                Sex = rs.getString("Sex");
                Balance = rs.getString("Initial_Balance");
            }

            // appending the values retireved from the database to the Table
            Object[][] data = {
                    { "AccountNumber", "Name", "Initial_Balance" },
                    { this.AccountNumber, Name, Balance }
            };
            String[] columnNames = { "First Name", "Last Name", "Age" };
            JTable table = new JTable(data, columnNames);
            submitButton = new JButton("Submit ");
            BackButton = new JButton("Return ");
            submitButton.addActionListener(this);
            BackButton.addActionListener(this);

            try {
                Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rSet = statement2.executeQuery(queryString);
                while (rSet.next()) {
                    Name = rSet.getString("Name");
                    AccountNumber = rSet.getString("Account_Number");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;
            constraints.insets = new Insets(5, 15, 10, 20);

            // adding the components to the frame
            addComponent(label, 0, 0, 0, 1); // Span across 2 columns
            addComponent(table, 0, 1, 2, 1);
            addComponent(label2, 0, 2, 1, 1);
            addComponent(textField, 1, 2, 1, 1);
            
            constraints.anchor = GridBagConstraints.LINE_END;
            addComponent(BackButton, 0, 3, 1, 1);
            constraints.anchor = GridBagConstraints.LINE_START;
            addComponent(submitButton, 1, 3, 1, 1);
            

        } catch (Exception e) {

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

        // accessing the value to be transffered to the user

        if (e.getSource() == submitButton) {
            String amount = textField.getText();

            // implementing transaciton while money transfer
            try {
                Connection connection2 = DriverManager.getConnection(dbURL, username, password);
                connection2.setAutoCommit(false);

                try {

                    Statement statement3 = connection2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

                    statement3.executeUpdate("UPDATE `user` SET `Initial_Balance`=`Initial_Balance`-" + amount
                            + " WHERE `Account_Number`=" + AccountNumber);
                    System.out.println(AccountNumber);

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

}

