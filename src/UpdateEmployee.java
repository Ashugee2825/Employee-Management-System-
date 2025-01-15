import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JCheckBox cbName, cbAge, cbSalary, cbAddress, cbPhone, cbEmail, cbOnboarded;
    JTextField tfempId, tfage, tfaddress, tfphone, tfemail, tfsalary, tfonboarded;
    JLabel lblname, lblempId;
    JButton search, add, back, close;
    String empId;

    UpdateEmployee() {
        getContentPane().setBackground(new Color(30, 45, 90));  // Dark background color
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(200, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);  // White text
        add(heading);

        // Employee ID to search
        JLabel labelEmpId = new JLabel("Enter Employee ID:");
        labelEmpId.setBounds(50, 100, 180, 30);
        labelEmpId.setFont(new Font("Arial", Font.PLAIN, 20));
        labelEmpId.setForeground(Color.WHITE);  // White text
        add(labelEmpId);

        tfempId = new JTextField();
        tfempId.setBounds(220, 100, 150, 30);
        tfempId.setFont(new Font("Arial", Font.PLAIN, 18));
        tfempId.setBackground(Color.LIGHT_GRAY);
        add(tfempId);

        search = new JButton("Search");
        search.setBounds(400, 100, 150, 35);
        search.addActionListener(this);
        search.setFont(new Font("Arial", Font.BOLD, 16));
        search.setBackground(new Color(70, 130, 180));  // Steel Blue background
        search.setForeground(Color.BLACK);  // Black text
        search.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        search.setFocusPainted(false);
        add(search);

        // Name label and field
        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("Arial", Font.PLAIN, 20));
        labelname.setForeground(Color.WHITE);  // White text
        add(labelname);

        cbName = new JCheckBox("Update");
        cbName.setBounds(400, 150, 70, 30);
        cbName.setFont(new Font("Arial", Font.PLAIN, 15));
        cbName.setForeground(Color.WHITE);  // White text
        add(cbName);

        lblname = new JLabel();
        lblname.setBounds(220, 150, 150, 30);
        lblname.setFont(new Font("Arial", Font.PLAIN, 18));
        lblname.setForeground(Color.WHITE);
        add(lblname);

        // Age
        JLabel labelage = new JLabel("Age:");
        labelage.setBounds(50, 200, 150, 30);
        labelage.setFont(new Font("Arial", Font.PLAIN, 20));
        labelage.setForeground(Color.WHITE);  // White text
        add(labelage);

        cbAge = new JCheckBox("Update");
        cbAge.setBounds(400, 200, 70, 30);
        cbAge.setFont(new Font("Arial", Font.PLAIN, 15));
        cbAge.setForeground(Color.WHITE);  // White text
        add(cbAge);

        tfage = new JTextField();
        tfage.setBounds(220, 200, 150, 30);
        tfage.setFont(new Font("Arial", Font.PLAIN, 18));
        tfage.setBackground(Color.LIGHT_GRAY);
        add(tfage);

        // Salary
        JLabel labelsalary = new JLabel("Salary:");
        labelsalary.setBounds(50, 250, 150, 30);
        labelsalary.setFont(new Font("Arial", Font.PLAIN, 20));
        labelsalary.setForeground(Color.WHITE);  // White text
        add(labelsalary);

        cbSalary = new JCheckBox("Update");
        cbSalary.setBounds(400, 250, 70, 30);
        cbSalary.setFont(new Font("Arial", Font.PLAIN, 15));
        cbSalary.setForeground(Color.WHITE);  // White text
        add(cbSalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(220, 250, 150, 30);
        tfsalary.setFont(new Font("Arial", Font.PLAIN, 18));
        tfsalary.setBackground(Color.LIGHT_GRAY);
        add(tfsalary);

        // Phone
        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(50, 300, 150, 30);
        labelphone.setFont(new Font("Arial", Font.PLAIN, 20));
        labelphone.setForeground(Color.WHITE);  // White text
        add(labelphone);

        cbPhone = new JCheckBox("Update");
        cbPhone.setBounds(400, 300, 70, 30);
        cbPhone.setFont(new Font("Arial", Font.PLAIN, 15));
        cbPhone.setForeground(Color.WHITE);  // White text
        add(cbPhone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 300, 150, 30);
        tfphone.setFont(new Font("Arial", Font.PLAIN, 18));
        tfphone.setBackground(Color.LIGHT_GRAY);
        add(tfphone);

        // Email
        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(50, 350, 150, 30);
        labelemail.setFont(new Font("Arial", Font.PLAIN, 20));
        labelemail.setForeground(Color.WHITE);  // White text
        add(labelemail);

        cbEmail = new JCheckBox("Update");
        cbEmail.setBounds(400, 350, 70, 30);
        cbEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        cbEmail.setForeground(Color.WHITE);  // White text
        add(cbEmail);

        tfemail = new JTextField();
        tfemail.setBounds(220, 350, 150, 30);
        tfemail.setFont(new Font("Arial", Font.PLAIN, 18));
        tfemail.setBackground(Color.LIGHT_GRAY);
        add(tfemail);

        // Address
        JLabel labeladdress = new JLabel("Address:");
        labeladdress.setBounds(50, 400, 150, 30);
        labeladdress.setFont(new Font("Arial", Font.PLAIN, 20));
        labeladdress.setForeground(Color.WHITE);  // White text
        add(labeladdress);

        cbAddress = new JCheckBox("Update");
        cbAddress.setBounds(400, 400, 70, 30);
        cbAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        cbAddress.setForeground(Color.WHITE);  // White text
        add(cbAddress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 400, 150, 30);
        tfaddress.setFont(new Font("Arial", Font.PLAIN, 18));
        tfaddress.setBackground(Color.LIGHT_GRAY);
        add(tfaddress);

        // Onboarded status
        JLabel labelonboarded = new JLabel("Onboarded:");
        labelonboarded.setBounds(50, 450, 150, 30);
        labelonboarded.setFont(new Font("Arial", Font.PLAIN, 20));
        labelonboarded.setForeground(Color.WHITE);  // White text
        add(labelonboarded);

        cbOnboarded = new JCheckBox("Update");
        cbOnboarded.setBounds(400, 450, 70, 30);
        cbOnboarded.setFont(new Font("Arial", Font.PLAIN, 15));
        cbOnboarded.setForeground(Color.WHITE);  // White text
        add(cbOnboarded);

        tfonboarded = new JTextField();
        tfonboarded.setBounds(220, 450, 150, 30);
        tfonboarded.setFont(new Font("Arial", Font.PLAIN, 18));
        tfonboarded.setBackground(Color.LIGHT_GRAY);
        add(tfonboarded);

        // Employee ID (just to display the ID)
        JLabel labelempId = new JLabel("Employee ID:");
        labelempId.setBounds(50, 500, 150, 30);
        labelempId.setFont(new Font("Arial", Font.PLAIN, 20));
        labelempId.setForeground(Color.WHITE);
        add(labelempId);

        this.lblempId = new JLabel();
        this.lblempId.setBounds(220, 500, 150, 30);
        this.lblempId.setFont(new Font("Arial", Font.PLAIN, 18));
        this.lblempId.setForeground(Color.WHITE);
        add(this.lblempId);

        // Buttons for Update, Back, and Close
        add = new JButton("Update Details");
        add.setBounds(250, 600, 180, 40);
        add.addActionListener(this);
        add.setFont(new Font("Arial", Font.BOLD, 16));
        add.setBackground(new Color(70, 130, 180));  // Steel Blue background
        add.setForeground(Color.BLACK);  // Black text
        add.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add.setFocusPainted(false);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 600, 150, 40);
        back.addActionListener(this);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(new Color(70, 130, 180));  // Steel Blue background
        back.setForeground(Color.BLACK);  // Black text
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        back.setFocusPainted(false);
        add(back);

        close = new JButton("Close");
        close.setBounds(650, 600, 150, 40);
        close.addActionListener(this);
        close.setFont(new Font("Arial", Font.BOLD, 16));
        close.setBackground(new Color(255, 69, 0));  // Red background
        close.setForeground(Color.BLACK);  // Black text
        close.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        close.setFocusPainted(false);
        add(close);

        setSize(900, 750); // Adjusted height for checkboxes and labels
        setLocation(300, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            empId = tfempId.getText().trim();
            if (empId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an Employee ID.");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM employees WHERE id = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, empId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    lblname.setText(rs.getString("name"));
                    tfage.setText(rs.getString("age"));
                    tfsalary.setText(rs.getString("salary"));
                    tfphone.setText(rs.getString("phone"));
                    tfemail.setText(rs.getString("email"));
                    tfaddress.setText(rs.getString("address"));
                    tfonboarded.setText(rs.getString("onboarded"));
                    this.lblempId.setText(rs.getString("id"));
                } else {
                    JOptionPane.showMessageDialog(null, "No employee found with the given ID.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            try {
                Conn conn = new Conn();
                String query = "UPDATE employees SET ";
                boolean hasUpdates = false;

                if (cbName.isSelected()) {
                    query += "name = ?, ";
                    hasUpdates = true;
                }
                if (cbAge.isSelected()) {
                    query += "age = ?, ";
                    hasUpdates = true;
                }
                if (cbSalary.isSelected()) {
                    query += "salary = ?, ";
                    hasUpdates = true;
                }
                if (cbPhone.isSelected()) {
                    query += "phone = ?, ";
                    hasUpdates = true;
                }
                if (cbEmail.isSelected()) {
                    query += "email = ?, ";
                    hasUpdates = true;
                }
                if (cbAddress.isSelected()) {
                    query += "address = ?, ";
                    hasUpdates = true;
                }
                if (cbOnboarded.isSelected()) {
                    query += "onboarded = ?, ";
                    hasUpdates = true;
                }

                if (hasUpdates) {
                    // Remove trailing comma and space
                    query = query.substring(0, query.length() - 2); 
                }

                query += " WHERE id = ?";

                PreparedStatement ps = conn.c.prepareStatement(query);
                int index = 1;

                if (cbName.isSelected()) {
                    ps.setString(index++, lblname.getText());
                }
                if (cbAge.isSelected()) {
                    ps.setString(index++, tfage.getText());
                }
                if (cbSalary.isSelected()) {
                    ps.setString(index++, tfsalary.getText());
                }
                if (cbPhone.isSelected()) {
                    ps.setString(index++, tfphone.getText());
                }
                if (cbEmail.isSelected()) {
                    ps.setString(index++, tfemail.getText());
                }
                if (cbAddress.isSelected()) {
                    ps.setString(index++, tfaddress.getText());
                }
                if (cbOnboarded.isSelected()) {
                    ps.setString(index++, tfonboarded.getText());
                }

                ps.setString(index, empId);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Details updated successfully");
                    setVisible(false);
                    new EmployeeHomePage(); // Navigate back to home page
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update employee details.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new EmployeeHomePage(); // Navigate back to home page
        } else if (ae.getSource() == close) {
            System.exit(0); // Close the application
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee();
    }
}
