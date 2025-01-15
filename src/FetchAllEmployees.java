import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FetchAllEmployees {
    public FetchAllEmployees() {
        // Frame setup
        JFrame frame = new JFrame("Fetch All Employees");
        frame.setSize(650, 400);  // Increase the size slightly for better layout
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Age", "Salary", "Phone", "Email", "Address", "Onboarded"};
        String[][] data = fetchData();

        // Create JTable to display data
        JTable table = new JTable(data, columnNames);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBackground(Color.LIGHT_GRAY);
        table.setBorder(BorderFactory.createLineBorder(new Color(255, 175, 175), 3));
        table.setGridColor(Color.BLACK);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Auto resizing columns

        JScrollPane sp = new JScrollPane(table);
        frame.getContentPane().add(sp, BorderLayout.CENTER);

        // Create Panel for Buttons with better layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center the buttons with spacing

        // Create the "Back" button
        JButton btnBack = new JButton("Back");
        styleButton(btnBack, new Color(65, 105, 225));  // Styled button
        btnBack.addActionListener(e -> {
            frame.dispose();
            EmployeeHomePage.main(null); // Go back to home page
        });
        buttonPanel.add(btnBack);

        // Create the "Close" button
        JButton btnClose = new JButton("Close");
        styleButton(btnClose, new Color(255, 69, 0));  // Styled button
        btnClose.addActionListener(e -> frame.dispose());
        buttonPanel.add(btnClose);

        // Add button panel at the bottom
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Style for buttons
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().brighter()); // Lighter on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor); // Revert to original color
            }
        });
    }

    // Fetch data from the database
    private String[][] fetchData() {
        String[][] data = new String[0][];
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybank", "root", "1234")) {
            String query = "SELECT * FROM employees";

            // Create statement with scrollable result set
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);

            // Move cursor to the last row to determine the row count
            rs.last();
            int rowCount = rs.getRow();
            data = new String[rowCount][8]; // Adding column for "Onboarded" status
            rs.beforeFirst(); // Move cursor back to the first row

            // Iterate through result set and fill data array
            int i = 0;
            while (rs.next()) {
                data[i][0] = String.valueOf(rs.getInt("id"));
                data[i][1] = rs.getString("name");
                data[i][2] = String.valueOf(rs.getInt("age"));
                data[i][3] = String.valueOf(rs.getDouble("salary"));
                data[i][4] = rs.getString("phone");
                data[i][5] = rs.getString("email");
                data[i][6] = rs.getString("address");
                data[i][7] = rs.getBoolean("onboarded") ? "Yes" : "No"; // Onboarded column
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new FetchAllEmployees();
    }
}
