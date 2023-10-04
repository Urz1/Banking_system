import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

class Home extends JFrame implements ActionListener {

    JButton Create_User,Clients,Deposit,Withdrawal,Transfer,Loan,Transaction_History,ImageButton;
    JToolBar toolBar;
    Home() {
        super("Banking System");// Calling the constructor of the parent class to name the frame
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setFont(new Font(getName(), ABORT, E_RESIZE_CURSOR));
         // Set the default foreground color
        UIManager.put("Label.foreground", Color.WHITE);
        
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\HP\\Desktop\\NotePad Project\\skeleton\\Banking-Courses.png");
        ImageIcon LogoImage = new ImageIcon("C:\\\\Users\\\\HP\\\\Desktop\\\\NotePad Project\\\\skeleton\\\\banking.png");
        JLabel label=new JLabel(backgroundImage);
        

        Image orginal=LogoImage.getImage();
        Image resizedImage=orginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon=new ImageIcon(resizedImage);

        // Creating a logo and adding it to the label component 
        JLabel label2=new JLabel(resizedIcon);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(label,BorderLayout.AFTER_LINE_ENDS);// inserting the image as an icon using jlabel
        getContentPane().setBackground(new Color(20, 31, 31));// changing the bgcolor of the frame

        // setting the components on the toolbar 
        toolBar=new JToolBar();
        Create_User=new JButton("Create User");
        Clients=new JButton("Clients");
        Deposit=new JButton("Deposit");
        Withdrawal=new JButton("Withdrawal");
        Transfer=new JButton("Transfer");
        Loan=new JButton("Loan");
        Transaction_History=new JButton("Transaction-History");

        Create_User.setBackground(new Color(102, 153, 153));
        Border bborder=BorderFactory.createLineBorder(new Color(102, 153, 153), 10, true);
        Create_User.setBorder(bborder);

        
        

        // adding the componets to the toobar 
        toolBar.add(label2, BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(Create_User,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(Clients,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(Deposit,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));//This line is used to insert a scattering property for the components on the toolbar 
        toolBar.add(Withdrawal,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));//This line is used to insert a scattering property for the components on the toolbar 
        toolBar.add(Transfer,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));//This line is used to insert a scattering property for the components on the toolbar 
        toolBar.add(Loan,BorderLayout.WEST);
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));//This line is used to insert a scattering property for the components on the toolbar 
        toolBar.add(Transaction_History,BorderLayout.WEST);

        // Editing the toolbar 
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(Box.createRigidArea(new Dimension(50, 0)));//This line is used to insert a scattering property for the components on the toolbar 
        toolBar.setPreferredSize(new Dimension(800,80));
        toolBar.setBackground(new Color(148, 184, 184));
        toolBar.setFloatable(false);// avoid being resizable and floatable by the user 
        
        add(toolBar, BorderLayout.NORTH);
        

        // Image image = backgroundImage.getImage();
        // Image resizedImage = image.getScaledInstance(1300, 700, Image.SCALE_SMOOTH);

        // // Create a new ImageIcon with the resized image
        // ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        // JLabel backgroundLabel = new JLabel(resizedImageIcon);

        // // Set the size and position of the label
        // backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // getContentPane().setLayout(null);

        // // Add the background label to the content pane
        // getContentPane().add(backgroundLabel);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

    }

}

public class Bank {
    public static void main(String[] args) {
        // UItemplate uItemplate=new UItemplate();
        UItemplate home = new UItemplate();
        // Home home=new Home();
    }
}
