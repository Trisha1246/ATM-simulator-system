package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String cardno;

    BalanceEnquiry(String cardno) {
        this.cardno = cardno;

        // Gradient Background
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
        card.setBounds(80, 40, 440, 280);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.add(card);

        //  Title
        JLabel heading = new JLabel("💰 Balance Enquiry");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBounds(110, 30, 300, 30);
        card.add(heading);

        //  Balance Label
        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        balanceLabel.setForeground(new Color(0, 153, 76));
        balanceLabel.setBounds(100, 100, 300, 40);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(balanceLabel);

        //  Back Button
        back = new JButton("BACK ⬅");
        back.setBounds(150, 180, 140, 45);
        styleButton(back, new Color(128, 128, 128));
        card.add(back);

        back.addActionListener(this);

        int balance = 0;

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(
                    "select * from bank where card_number = '" + cardno + "'"
            );

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  Display Balance
        balanceLabel.setText("₹ " + balance);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Button Style + Hover
    void styleButton(JButton btn, Color color) {
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
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(cardno).setVisible(true);
    }
}
