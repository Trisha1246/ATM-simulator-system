package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton saving, current, fixed, recurring;
    JCheckBox atm, internet, mobile, email, cheque, eStatement;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;

        //  Gradient Background
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(0, 153, 204),
                        0, getHeight(),
                        new Color(0, 204, 153));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        //  Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(80, 30, 680, 620);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.add(cardPanel);

        //  Step Indicator
        JLabel step = new JLabel("Step 3 of 3");
        step.setFont(new Font("Segoe UI", Font.BOLD, 14));
        step.setBounds(520, 10, 120, 20);
        cardPanel.add(step);

        // Title
        JLabel title = new JLabel("Account Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(220, 40, 300, 30);
        cardPanel.add(title);

        int y = 100;

        //  Account Type
        cardPanel.add(label("Account Type", 50, y));

        saving = radio("Saving Account", 250, y);
        current = radio("Current Account", 250, y + 30);
        fixed = radio("Fixed Deposit", 250, y + 60);
        recurring = radio("Recurring Deposit", 250, y + 90);

        ButtonGroup group = new ButtonGroup();
        group.add(saving);
        group.add(current);
        group.add(fixed);
        group.add(recurring);

        cardPanel.add(saving);
        cardPanel.add(current);
        cardPanel.add(fixed);
        cardPanel.add(recurring);

        // Card Number
        y += 130;
        cardPanel.add(label("Card Number", 50, y));

        JLabel cardNo = new JLabel("XXXX-XXXX-XXXX-XXXX");
        cardNo.setBounds(250, y, 300, 25);
        cardNo.setForeground(Color.GRAY);
        cardPanel.add(cardNo);

        //  PIN
        y += 40;
        cardPanel.add(label("PIN", 50, y));

        JLabel pinNo = new JLabel("XXXX");
        pinNo.setBounds(250, y, 200, 25);
        pinNo.setForeground(Color.GRAY);
        cardPanel.add(pinNo);

        // Services
        y += 50;
        cardPanel.add(label("Services Required", 50, y));

        atm = checkbox("ATM Card", 250, y);
        internet = checkbox("Internet Banking", 400, y);
        mobile = checkbox("Mobile Banking", 250, y + 40);
        email = checkbox("Email Alerts", 400, y + 40);
        cheque = checkbox("Cheque Book", 250, y + 80);
        eStatement = checkbox("E-Statement", 400, y + 80);

        cardPanel.add(atm);
        cardPanel.add(internet);
        cardPanel.add(mobile);
        cardPanel.add(email);
        cardPanel.add(cheque);
        cardPanel.add(eStatement);

        // Buttons
        submit = createButton("SUBMIT ✔", 200, 500, new Color(0, 153, 204));
        cancel = createButton("CANCEL ✖", 360, 500, Color.GRAY);

        cardPanel.add(submit);
        cardPanel.add(cancel);

        submit.addActionListener(this);
        cancel.addActionListener(this);

        setSize(850, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Label
    JLabel label(String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 200, 25);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return l;
    }

    //  Radio
    JRadioButton radio(String text, int x, int y) {
        JRadioButton r = new JRadioButton(text);
        r.setBounds(x, y, 200, 30);
        r.setBackground(Color.WHITE);
        return r;
    }

    //  Checkbox
    JCheckBox checkbox(String text, int x, int y) {
        JCheckBox c = new JCheckBox(text);
        c.setBounds(x, y, 180, 30);
        c.setBackground(Color.WHITE);
        return c;
    }

    //  Button Hover
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

        if (ae.getSource() == submit) {

            String accountType = null;

            if (saving.isSelected()) accountType = "Saving";
            else if (current.isSelected()) accountType = "Current";
            else if (fixed.isSelected()) accountType = "Fixed";
            else if (recurring.isSelected()) accountType = "Recurring";

            if (accountType == null) {
                JOptionPane.showMessageDialog(null, "Select Account Type");
                return;
            }

            //  Generate card number + pin
            Random ran = new Random();
            String cardNumber = "504093" + (1000000000L + Math.abs(ran.nextLong() % 9000000000L));
            String pin = String.format("%04d", ran.nextInt(10000));

            String facility = "";
            if (atm.isSelected()) facility += "ATM ";
            if (internet.isSelected()) facility += "Internet ";
            if (mobile.isSelected()) facility += "Mobile ";
            if (email.isSelected()) facility += "Email ";
            if (cheque.isSelected()) facility += "Cheque ";
            if (eStatement.isSelected()) facility += "E-Statement ";

            if (facility.equals("")) {
                JOptionPane.showMessageDialog(null, "Select at least one service");
                return;
            }

            try {
                Conn c = new Conn();

                String query = "insert into signupthree values('"
                        + formno + "','" + accountType + "','" + cardNumber + "','" + pin + "','" + facility + "')";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,
                        "🎉 Account Created!\n\nCard Number: " + cardNumber + "\nPIN: " + pin);

                setVisible(false);
                new Login();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login();
        }
    }
}
