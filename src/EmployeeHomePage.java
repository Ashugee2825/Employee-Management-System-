import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class EmployeeHomePage {
	 
    private String userRole;

	public EmployeeHomePage() {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.RED);
        
        // Dropdown Menu for Profile and Logout
        String[] profileOptions = {"Logged in as: " + userRole, "Profile", "Logout"};
        JComboBox<String> profileDropdown = new JComboBox<>(profileOptions);
        profileDropdown.setBounds(10, 10, 139, 24);
        profileDropdown.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        profileDropdown.setBackground(Color.WHITE);

        profileDropdown.addActionListener(e -> {
            String selectedOption = (String) profileDropdown.getSelectedItem();
            if ("Profile".equals(selectedOption)) {
                JOptionPane.showMessageDialog(frame, "Displaying profile details for: " + userRole, "Profile", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Logout".equals(selectedOption)) {
                int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "You have been logged out!", "Logout", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new Login(); // Redirect to the login page
                }
            }
        });

        frame.getContentPane().add(profileDropdown);
        
        
        // Title Label
        JLabel titleLabel = new JLabel("WELCOME TO EMPLOYEE MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setBounds(100, 45, 386, 18);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        frame.getContentPane().add(titleLabel);
        
        // Dropdown Menu at Bottom
        String[] options = {
                "Select an Option",
                "1) Attendance",
                "2) Payroll Management",
                "3) Performance Management",
                "4) Training and Development",
                "5) Analytics and Reporting",
                "6) Compliance Management",
                "7) Document Management"
        };

        JComboBox<String> dropdownMenu = new JComboBox<>(options);
        dropdownMenu.setBounds(396, 225, 178, 24);
        dropdownMenu.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        dropdownMenu.setBackground(Color.WHITE);

        // Action Listener for Dropdown
        dropdownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropdownMenu.getSelectedItem();
                if (!"Select an Option".equals(selectedOption)) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "You selected: " + selectedOption,
                            "Option Selected",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // Example functionality for selected options
                    switch (selectedOption) {
                        case "1) Attendance":
                            // Add Attendance Management functionality here
                            JOptionPane.showMessageDialog(frame, "Attendance module will open soon!", "Attendance", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "2) Payroll Management":
                            // Add Payroll Management functionality here
                            JOptionPane.showMessageDialog(frame, "Payroll Management module will open soon!", "Payroll Management", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "3) Performance Management":
                            // Add Performance Management functionality here
                            JOptionPane.showMessageDialog(frame, "Performance Management module will open soon!", "Performance Management", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "4) Training and Development":
                            // Add Training and Development functionality here
                            JOptionPane.showMessageDialog(frame, "Training module will open soon!", "Training and Development", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "5) Analytics and Reporting":
                            // Add Analytics and Reporting functionality here
                            JOptionPane.showMessageDialog(frame, "Analytics module will open soon!", "Analytics and Reporting", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "6) Compliance Management":
                            // Add Compliance Management functionality here
                            JOptionPane.showMessageDialog(frame, "Compliance module will open soon!", "Compliance Management", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "7) Document Management":
                            // Add Document Management functionality here
                            JOptionPane.showMessageDialog(frame, "Document Management module will open soon!", "Document Management", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        frame.getContentPane().add(dropdownMenu);

        frame.setVisible(true);
        
        
        
        
        
     // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(525, 11, 49, 22);
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "You have been logged out!", "Logout", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new Login(); // Redirect to the login page
            }
        });

        frame.getContentPane().add(logoutButton);
        
        

        // Fetch All Employees Button
        JButton fetchAllBtn = createButton("FETCH ALL EMPLOYEES", 30, 80, e -> {
            frame.dispose();
            new FetchAllEmployees();
        });
        frame.getContentPane().add(fetchAllBtn);

        JButton fetchByIdBtn = createButton("FETCH EMPLOYEE BY ID", 330, 80, e -> {
            frame.dispose();
            new SearchEmployee();
        });
        
        frame.getContentPane().add(fetchByIdBtn);
        
        // Delete Employee Button
        JButton deleteBtn = createButton("DELETE EMPLOYEE", 30, 160, e -> {
            frame.dispose();
            new DeleteEmployee();
        });
        
        
        // Update Employee Button
        frame.getContentPane().add(deleteBtn);
        JButton updateBtn = createButton("UPDATE EMPLOYEE DETAILS", 330, 160, e -> {
            frame.dispose();
            new UpdateEmployee(); // Pass reference of EmployeeHomePage to UpdateEmployee constructor


        	
      //      JOptionPane.showMessageDialog(frame, "Update Employee feature not implemented.");
        });
        frame.getContentPane().add(updateBtn);
       
        // Onboard New Employee Button
        JButton onboardBtn = createButton("ONBOARD NEW EMPLOYEE", 160, 240, e -> {
            frame.dispose();
            new onboardEmployee();
        });
        frame.getContentPane().add(onboardBtn);

        frame.setVisible(true);
    }

    private static JButton createButton(String text, int x, int y, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 50);
        button.setBackground(new Color(65, 105, 225));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.addActionListener(action);
        return button;
    }

    public static void main(String[] args) {
        new EmployeeHomePage();
    }
}

class SearchEmployee {
    public SearchEmployee() {
        JFrame frame = new JFrame("Search Employee");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(34, 45, 65));

        JButton btnSearch = new JButton("Search Employee by ID");
        btnSearch.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnSearch.setBackground(new Color(65, 105, 225));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnSearch.addActionListener(e -> {
            frame.dispose();
            new EmployeeById();
        });
        frame.add(btnSearch);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnBack.setBackground(new Color(65, 105, 225));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnBack.addActionListener(e -> {
            frame.dispose();
            EmployeeHomePage.main(null);
        });
        frame.getContentPane().add(btnBack);

        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnClose.setBackground(new Color(255, 69, 0));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnClose.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(btnClose);

        frame.setVisible(true);
    }
}

class DeleteEmployee {
    public DeleteEmployee() {
        JFrame frame = new JFrame("Delete Employee");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(6, 1, 10, 10));
        frame.getContentPane().setBackground(new Color(34, 45, 65));

        JLabel lblEnterId = new JLabel("Enter Employee ID:", SwingConstants.CENTER);
        lblEnterId.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblEnterId.setForeground(Color.WHITE);
        frame.add(lblEnterId);

        JTextField txtId = new JTextField();
        frame.add(txtId);

        JButton btnFetch = new JButton("Fetch Details");
        btnFetch.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnFetch.setBackground(new Color(65, 105, 225));
        btnFetch.setForeground(Color.WHITE);
        btnFetch.setFocusPainted(false);
        btnFetch.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnFetch.addActionListener(e -> fetchEmployeeDetails(txtId.getText(), frame));
        frame.add(btnFetch);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnDelete.addActionListener(e -> deleteEmployee(txtId.getText(), frame));
        frame.add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnBack.setBackground(new Color(65, 105, 225));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnBack.addActionListener(e -> {
            frame.dispose();
            EmployeeHomePage.main(null);
        });
        frame.add(btnBack);

        frame.setVisible(true);
    }

    private void fetchEmployeeDetails(String empId, JFrame frame) {
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an Employee ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234")) {
            String query = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(empId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                StringBuilder details = new StringBuilder();
                details.append("ID: ").append(rs.getInt("id")).append("\n");
                details.append("Name: ").append(rs.getString("name")).append("\n");
                details.append("Salary: ").append(rs.getDouble("salary")).append("\n");
                details.append("Email: ").append(rs.getString("email")).append("\n");
                details.append("Address: ").append(rs.getString("address")).append("\n");

                JOptionPane.showMessageDialog(frame, details.toString(), "Employee Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No employee found with ID: " + empId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error fetching employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee(String empId, JFrame frame) {
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an Employee ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234")) {
            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(empId));
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No employee found with ID: " + empId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class onboardEmployee {
    public onboardEmployee() {
        JFrame frame = new JFrame("Onboard New Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBackground(new Color(34, 45, 65));

        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(10);
        JTextField salaryField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(10);
        JTextField addressField = new JTextField(10);

        panel.add(createLabel("Employee ID:"));
        panel.add(idField);
        panel.add(createLabel("Name:"));
        panel.add(nameField);
        panel.add(createLabel("Age:"));
        panel.add(ageField);
        panel.add(createLabel("Salary:"));
        panel.add(salaryField);
        panel.add(createLabel("Phone:"));
        panel.add(phoneField);
        panel.add(createLabel("Email:"));
        panel.add(emailField);
        panel.add(createLabel("Address:"));
        panel.add(addressField);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnBack.setBackground(new Color(65, 105, 225));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnBack.addActionListener(e -> {
            frame.dispose();
            new EmployeeHomePage();
        });

        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnClose.setBackground(new Color(255, 69, 0));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btnClose.addActionListener(e -> System.exit(0));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(new Color(34, 45, 65));
        btnPanel.add(btnBack);
        btnPanel.add(btnClose);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        int result = JOptionPane.showConfirmDialog(
                frame, panel, "Enter New Employee Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String empId = idField.getText().trim();
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String salary = salaryField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String address = addressField.getText().trim();

            if (empId.isEmpty() || name.isEmpty() || age.isEmpty() || salary.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234");
                String query = "INSERT INTO employees (id, name, age, salary, phone, email, address, onboarded) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(empId));
                ps.setString(2, name);
                ps.setInt(3, Integer.parseInt(age));
                ps.setDouble(4, Double.parseDouble(salary));
                ps.setString(5, phone);
                ps.setString(6, email);
                ps.setString(7, address);
                ps.setInt(8, 1);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee onboarded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to onboard employee!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                con.close();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input format!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error onboarding employee!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
        label.setForeground(Color.WHITE);
        return label;
        
     
            
      
    }
    
}   
