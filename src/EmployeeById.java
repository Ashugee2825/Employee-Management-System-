import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeById {

    public EmployeeById() {
        // Create JFrame
        JFrame frame = new JFrame("Employee By ID");
        frame.setSize(400, 450); // Adjust the size
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for better component alignment
        frame.setLocationRelativeTo(null); // Center the window

        // Set a background color
        frame.getContentPane().setBackground(new Color(34, 45, 65)); // Dark background

        // Define fonts
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        // GridBagConstraints for better alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Input fields and labels
        JLabel lblEnterId = new JLabel("Enter Employee ID:");
        lblEnterId.setFont(labelFont);
        lblEnterId.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(lblEnterId, gbc);

        // Make txtId larger for better visibility
        JTextField txtId = new JTextField();
        txtId.setFont(valueFont);
        txtId.setPreferredSize(new Dimension(200, 30)); // Set preferred size
        txtId.setBackground(new Color(255, 255, 255)); // White background for better visibility
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(txtId, gbc);

        JButton btnSearch = createButton("Search", new Color(0, 123, 255), labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(btnSearch, gbc);

        JButton btnOnboard = createButton("Onboard", new Color(34, 139, 34), labelFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(btnOnboard, gbc);

        // Labels for employee details
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(labelFont);
        lblId.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(lblId, gbc);

        JLabel valId = new JLabel();
        valId.setFont(valueFont);
        valId.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(valId, gbc);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(labelFont);
        lblName.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(lblName, gbc);

        JLabel valName = new JLabel();
        valName.setFont(valueFont);
        valName.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(valName, gbc);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(labelFont);
        lblAge.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(lblAge, gbc);

        JLabel valAge = new JLabel();
        valAge.setFont(valueFont);
        valAge.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(valAge, gbc);

        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setFont(labelFont);
        lblSalary.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(lblSalary, gbc);

        JLabel valSalary = new JLabel();
        valSalary.setFont(valueFont);
        valSalary.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(valSalary, gbc);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(labelFont);
        lblPhone.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(lblPhone, gbc);

        JLabel valPhone = new JLabel();
        valPhone.setFont(valueFont);
        valPhone.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(valPhone, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        lblEmail.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(lblEmail, gbc);

        JLabel valEmail = new JLabel();
        valEmail.setFont(valueFont);
        valEmail.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 7;
        frame.add(valEmail, gbc);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(labelFont);
        lblAddress.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(lblAddress, gbc);

        JLabel valAddress = new JLabel();
        valAddress.setFont(valueFont);
        valAddress.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 8;
        frame.add(valAddress, gbc);

        // Back and Close buttons
        JButton btnBack = createButton("Back", new Color(65, 105, 225), labelFont);
        gbc.gridx = 0;
        gbc.gridy = 9;
        frame.add(btnBack, gbc);

        JButton btnClose = createButton("Close", new Color(255, 69, 0), labelFont);
        gbc.gridx = 1;
        gbc.gridy = 9;
        frame.add(btnClose, gbc);

        // Add action listeners
        btnSearch.addActionListener(e -> {
            String empId = txtId.getText();
            if (!empId.isEmpty()) {
                fetchEmployeeById(empId, valId, valName, valAge, valSalary, valPhone, valEmail, valAddress);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter an Employee ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnOnboard.addActionListener(e -> {
            String empId = valId.getText();
            if (!empId.isEmpty()) {
                onboardEmployee(empId);
            } else {
                JOptionPane.showMessageDialog(frame, "Please search for an employee first!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnBack.addActionListener(e -> {
            frame.dispose();
            new EmployeeHomePage(); // Go back to EmployeeHomePage
        });

        btnClose.addActionListener(e -> {
            frame.dispose(); // Close the application
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text, Color color, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
        return button;
    }

    private void fetchEmployeeById(String empId, JLabel valId, JLabel valName, JLabel valAge, JLabel valSalary, JLabel valPhone, JLabel valEmail, JLabel valAddress) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234")) {
            String query = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(empId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                valId.setText(String.valueOf(rs.getInt("id")));
                valName.setText(rs.getString("name"));
                valAge.setText(String.valueOf(rs.getInt("age")));
                valSalary.setText(String.valueOf(rs.getDouble("salary")));
                valPhone.setText(rs.getString("phone"));
                valEmail.setText(rs.getString("email"));
                valAddress.setText(rs.getString("address"));
            } else {
                JOptionPane.showMessageDialog(null, "No employee found with ID: " + empId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching employee details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onboardEmployee(String empId) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234")) {
            String query = "UPDATE employees SET onboarded = 1 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(empId));
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Employee onboarded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to onboard employee!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error onboarding employee!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new EmployeeById();
    }
}
