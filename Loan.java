
import javax.print.DocFlavor.STRING;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan extends JFrame implements ActionListener {
    JLabel label, label1, label2, label3, label4, label5;
    JTextField textField1, textField2, textField3;
    JComboBox comboBox;
    JComboBox<String> interestRate;
    JDateChooser StartDate, EndDate;
    JButton submitButton, BackButton;
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    GridBagConstraints gc2=new GridBagConstraints();

    String dbURL = "jdbc:mysql://localhost:3306/banking";
    String username = "root";
    String password = "Sad@qls1610-";
    String query = "SELECT * FROM Customer INNER JOIN loan ON customer.Customer_ID=loan.Customer_ID";
    String interestValue;

    Loan() {

        try (Connection connection = DriverManager.getConnection(dbURL, username, password);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = statement.executeQuery(query);
            // setting the size of the frame and a upto what amount of size it can be
            // resized
            setMinimumSize(new Dimension(400, 400));
            setMaximumSize(new Dimension(900, 800));
            setSize(800, 700);
            setLayout(layout);
            label = new JLabel("Welcome to Loan ");
            label1 = new JLabel("Choose User: ");
            label2 = new JLabel("Enter amount: ");
            label3 = new JLabel("Issue Date : ");
            label4 = new JLabel("End Date :");
            label5=new JLabel("Interest Rate :");
            

            textField1 = new JTextField(20);
            textField2 = new JTextField(20);
            textField3 = new JTextField(20);
            comboBox = new JComboBox();

            String[] interesString={"3%","5%","7%","10%"};
            interestRate=new JComboBox<>(interesString);
            
            
            
            // comboBox.setSelectedItem(rs.getString("First_Name"));

            StartDate = new JDateChooser();
            EndDate = new JDateChooser();

            submitButton = new JButton("Done");
            BackButton = new JButton("Home");
            submitButton.addActionListener(this);
            BackButton.addActionListener(this);

            JPanel panel=new JPanel();
            panel.setLayout(layout);
            gc2.gridx=0;
            gc2.gridy=0;
            gc2.weightx = 1.0;
            gc2.weighty = 0.0;
            panel.add(label5, gc2);

            gc2.gridx=1;
            gc2.gridy=0;
            gc2.weightx = 3.0;
            gc2.weighty = 0.0;

            panel.add(interestRate, gc2);

            
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.weightx = 1.0;
            gc.weighty = 0.0;
            gc.insets = new Insets(10, 15, 10, 15);

            addComponent(label, 0, 1, 2, 1);
            addComponent(label1, 0, 2, 1, 1);
            addComponent(comboBox, 0, 3, 1, 1);
            addComponent(label2, 1, 2, 1, 1);
            addComponent(textField1, 1, 3, 1, 1);
            
            addComponent(panel,0, 4, 1, 1);
            addComponent(label3, 0, 5, 1, 1);
            addComponent(label4, 1, 5, 1, 1);
            addComponent(StartDate, 0, 6, 1, 1);
            addComponent(EndDate, 1, 6, 1, 1);
            addComponent(submitButton, 0, 7, 1, 1);
            addComponent(BackButton, 1, 7, 1, 1);

            gc.fill = GridBagConstraints.NONE;

            // populating the values of the interest rate box 
            
            
            // populating the combobox with the values retrieved from the dataabse
            while (rs.next()) {
                String name = rs.getString("First_Name");
                String id = rs.getString("Customer_ID");
                comboBox.addItem("Customer_ID: " + id + ", " + "First_Name : " + name);
            }
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
        gc.gridx = row;
        gc.gridy = column;
        gc.gridwidth = width;
        gc.gridheight = height;
        layout.setConstraints(component, gc); // set the constraints on the component

        add(component);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            Date sDate = StartDate.getDate();
            Date eDate = EndDate.getDate();
    
            // Convert the dates to strings in the correct format
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = format.format(sDate);
            String endDateString = format.format(eDate);
            interestValue=(String) interestRate.getSelectedItem();  
    
            String query3 = "INSERT INTO loan(Issue_Date, Final_Date) Values('" + startDateString + "', '" + interestValue  + "', '" +  endDateString + "')";
    
            try {
                Connection connection2 = DriverManager.getConnection(dbURL, username, password);
                Statement statement2 = connection2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                int resultSet = statement2.executeUpdate(query3);
                System.out.println("sdjkfjk");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Loan loan = new Loan();
        loan.setVisible(true);
    }

}
