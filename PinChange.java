package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField oldPinField, newPinField, rePinField;
    JButton change, back;
    String cardno;

    PinChange(String cardno) {
        this.cardno = cardno;

        //  Gradient Background
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(0, 102, 204),
                        0, getHeight(),
                        new Color(0, 204, 153));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        //  Card Panel
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(100, 40, 600, 320);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.add(card);

        //  Title
        JLabel heading = new JLabel("🔐 Change PIN");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBounds(220, 20, 200, 30);
        card.add(heading);

        //  Labels
        JLabel oldPin = new JLabel("Old PIN");
        oldPin.setBounds(100, 80, 150, 25);
        card.add(oldPin);

        JLabel newPin = new JLabel("New PIN");
        newPin.setBounds(100, 130, 150, 25);
        card.add(newPin);

        JLabel rePin = new JLabel("Confirm PIN");
        rePin.setBounds(100, 180, 150, 25);
        card.add(rePin);

        //  Fields
        oldPinField = new JPasswordField();
        oldPinField.setBounds(250, 80, 200, 35);
        styleField(oldPinField);
        card.add(oldPinField);

        newPinField = new JPasswordField();
        newPinField.setBounds(250, 130, 200, 35);
        styleField(newPinField);
        card.add(newPinField);

        rePinField = new JPasswordField();
        rePinField.setBounds(250, 180, 200, 35);
        styleField(rePinField);
        card.add(rePinField);

        //  Buttons
        change = createButton("CHANGE ✔", 150, 240, new Color(0, 153, 76));
        back = createButton("⬅ BACK", 320, 240, new Color(128, 128, 128));

        card.add(change);
        card.add(back);

        change.addActionListener(this);
        back.addActionListener(this);

        setSize(800, 420);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Field Style
    void styleField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.BOLD, 16));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            }

            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        });
    }

    //  Button Style
    JButton createButton(String text, int x, int y, Color color) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 45);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(cardno).setVisible(true);
            return;
        }

        String oldPin = new String(oldPinField.getPassword()).trim();
        String newPin = new String(newPinField.getPassword()).trim();
        String rePin  = new String(rePinField.getPassword()).trim();

        //  Validation
        if (oldPin.isEmpty() || newPin.isEmpty() || rePin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        if (!newPin.equals(rePin)) {
            JOptionPane.showMessageDialog(null, "PINs do not match");
            return;
        }

        if (!newPin.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits");
            return;
        }

        try {
            Conn c = new Conn();

            //  Verify Old PIN
            String checkQuery = "select * from signupthree where card_number='" 
                    + cardno + "' and pin='" + oldPin + "'";

            ResultSet rs = c.s.executeQuery(checkQuery);

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Incorrect Old PIN ❌");
                return;
            }

            //  Update PIN
            String updateQuery = "update signupthree set pin='" + newPin +
                    "' where card_number='" + cardno + "'";

            c.s.executeUpdate(updateQuery);

            JOptionPane.showMessageDialog(null, "✅ PIN Changed Successfully");

            setVisible(false);
            new Transactions(cardno).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error changing PIN");
        }
    }
}
