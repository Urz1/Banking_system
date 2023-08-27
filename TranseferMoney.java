import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranseferMoney implements ActionListener {
    JFrame frame;
    JButton button;
    int count=0;

    TranseferMoney() {
        String dbURL = "jdbc:mysql://localhost:3306/banking";
        String username = "root";
        String password = "Sad@qls1610-";
        String query = "SELECT * FROM user";

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(650, 650);

        try (Connection connection = DriverManager.getConnection(dbURL, username, password);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String buttonText = rs.getString("Account_Number") + ": " + rs.getString("Name") + ": "
                        + rs.getString("Email") + ": " + rs.getString("Initial_Balance") + ": " + rs.getString("Sex");
                JPanel panel = new JPanel();
                JLabel label = new JLabel(buttonText);
                button = new JButton("Click Me");
                

                button.setActionCommand(rs.getString("Account_Number"));
                button.addActionListener(this);

                panel.setLayout(new GridLayout(1, 0));
                panel.add(label);
                panel.add(button);
                frame.add(panel);
                frame.setVisible(true);
                count++;
                
            }
            System.out.println("dafasf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Account=e.getActionCommand();
        System.out.println(Account);
        if (e.getSource()==Account){
            button.setEnabled(false);

        }
    }
}
