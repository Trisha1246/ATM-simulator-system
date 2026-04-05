package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    JCheckBox showPin;

    Login() {

        setTitle("ATM LOGIN");

        // Gradient Background
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // ✅ FIX
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(58, 123, 213),
                        0, getHeight(),
                        new Color(58, 213, 178));
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
        card.setBounds(150, 50, 500, 370);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.add(card);

        //  Title
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Segoe UI", Font.BOLD, 26));
        text.setBounds(120, 30, 300, 40);
        card.add(text);

        // Card Number
        JLabel cardno = new JLabel("Card Number");
        cardno.setBounds(50, 110, 150, 25);
        card.add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(200, 110, 220, 35);
        styleField(cardTextField);
        cardTextField.setToolTipText("Enter 16-digit card number");
        card.add(cardTextField);

        //  PIN
        JLabel pin = new JLabel("PIN");
        pin.setBounds(50, 170, 150, 25);
        card.add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(200, 170, 220, 35);
        styleField(pinTextField);
        card.add(pinTextField);

        //  Show PIN
        showPin = new JCheckBox("Show PIN");
        showPin.setBounds(200, 210, 120, 20);
        showPin.setBackground(Color.WHITE);
        card.add(showPin);

        showPin.addActionListener(e -> {
            pinTextField.setEchoChar(showPin.isSelected() ? (char) 0 : '*');
        });

        //  Buttons
        login = createButton("LOGIN", 60, 270, new Color(58, 123, 213));
        clear = createButton("CLEAR", 190, 270, Color.GRAY);
        signup = createButton("SIGN UP", 320, 270, new Color(58, 213, 178));

        card.add(login);
        card.add(clear);
        card.add(signup);

        login.addActionListener(this);
        clear.addActionListener(this);
        signup.addActionListener(this);

        //️ Enter Key
        getRootPane().setDefaultButton(login);

        setSize(800, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.BOLD, 14));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(58, 123, 213), 2));
            }

            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        });
    }

    //  Button Styling
    JButton createButton(String text, int x, int y, Color color) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 120, 40);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
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

        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        }

        else if (ae.getSource() == login) {

            String cardno = cardTextField.getText().trim();
            String pin = new String(pinTextField.getPassword()).trim();

            //  Validation
            if (!cardno.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(null, "Enter valid 16-digit Card Number");
                return;
            }

            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                return;
            }

            try {
                Conn c = new Conn();

                String query = "select * from signupthree where card_number = '" 
                                + cardno + "' and pin = '" + pin + "'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Transactions(cardno).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN ❌");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        
        new Login();
    }
}
