import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JTextField tfusername;
    JPasswordField tfpassword;  // JPasswordField for secure password input
    JButton login, reset;

    Login() {
        
        // Set Frame Properties
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue background
        setLayout(null);
        
        // Title Label
        JLabel title = new JLabel("Employee Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204)); // Steel Blue color
        title.setBounds(200, 10, 200, 40);
        add(title);

        // Username Label and Text Field
        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblusername.setBounds(40, 80, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setFont(new Font("Arial", Font.PLAIN, 16));
        tfusername.setBounds(150, 80, 200, 30);
        tfusername.setBackground(Color.WHITE);
        tfusername.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tfusername);

        // Password Label and Password Field
        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpassword.setBounds(40, 130, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setFont(new Font("Arial", Font.PLAIN, 16));
        tfpassword.setBounds(150, 130, 200, 30);
        tfpassword.setBackground(Color.WHITE);
        tfpassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tfpassword);

        // Login Button
        login = new JButton("Login");
        login.setBounds(100, 200, 100, 40);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(new Color(0, 153, 76)); // Green
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        // Reset Button
        reset = new JButton("Reset");
        reset.setBounds(220, 200, 100, 40);
        reset.setFont(new Font("Arial", Font.BOLD, 16));
        reset.setBackground(new Color(204, 0, 0)); // Red
        reset.setForeground(Color.WHITE);
        reset.addActionListener(e -> {
            tfusername.setText("");
            tfpassword.setText("");
        });
        add(reset);

        // Image Section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 80, 150, 150);
        add(image);

        // Frame properties
        setSize(600, 350);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Prevent resizing
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            try {
                String username = tfusername.getText();
                String password = new String(tfpassword.getPassword());
                
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new EmployeeHomePage(); // Assuming another JFrame for Employee Home
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error connecting to the database.");
            }
        }
    }
    
    public static void main(String[] args) {
        new Login();


class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    }
}