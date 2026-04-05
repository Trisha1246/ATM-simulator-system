package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdraw, fastCash, miniStatement, pinChange, balanceEnquiry, exit, netBanking;
    String cardno;

    Transactions(String cardno) {
        this.cardno = cardno;

        setTitle("ATM Dashboard");

        //  Gradient Background
        JPanel background = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(36, 198, 220),
                        getWidth(), getHeight(),
                        new Color(81, 74, 157));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        //  Main Card
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(650, 420));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        background.add(card);

        //  Header Panel
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel welcome = new JLabel("Welcome 👋");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel cardLabel = new JLabel("Card: XXXX-XXXX-XXXX-" + cardno.substring(cardno.length() - 4));
        cardLabel.setForeground(Color.GRAY);

        JPanel left = new JPanel(new GridLayout(2,1));
        left.setOpaque(false);
        left.add(welcome);
        left.add(cardLabel);

        JLabel heading = new JLabel("Select Your Transaction", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));

        header.add(left, BorderLayout.WEST);
        header.add(heading, BorderLayout.CENTER);

        card.add(header, BorderLayout.NORTH);

        //  Button Grid Panel
        JPanel grid = new JPanel(new GridLayout(4, 2, 15, 15));
        grid.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        grid.setOpaque(false);

        deposit = createButton("Deposit", new Color(0, 153, 102));
        withdraw = createButton("Withdraw", new Color(204, 0, 0));
        fastCash = createButton("Fast Cash", new Color(255, 153, 0));
        miniStatement = createButton("Mini Statement", new Color(102, 0, 204));
        pinChange = createButton("PIN Change", new Color(0, 102, 204));
        balanceEnquiry = createButton("Balance", new Color(0, 153, 153));
        netBanking = createButton("Net Banking", new Color(153, 102, 0));
        exit = createButton("Exit", Color.DARK_GRAY);

        grid.add(deposit);
        grid.add(withdraw);
        grid.add(fastCash);
        grid.add(miniStatement);
        grid.add(pinChange);
        grid.add(balanceEnquiry);
        grid.add(netBanking);
        grid.add(exit);

        card.add(grid, BorderLayout.CENTER);

        //  Footer
        JLabel status = new JLabel("🔒 Secure Banking Session Active");
        status.setForeground(Color.GRAY);
        status.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(status, BorderLayout.SOUTH);

        // Actions
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        fastCash.addActionListener(this);
        miniStatement.addActionListener(this);
        pinChange.addActionListener(this);
        balanceEnquiry.addActionListener(this);
        netBanking.addActionListener(this);
        exit.addActionListener(this);

        setSize(800, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Modern Button
    JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Hover effect
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

        if (ae.getSource() == deposit) navigate(new Deposit(cardno));
        else if (ae.getSource() == withdraw) navigate(new Withdraw(cardno));
        else if (ae.getSource() == balanceEnquiry) navigate(new BalanceEnquiry(cardno));
        else if (ae.getSource() == fastCash) navigate(new FastCash(cardno));
        else if (ae.getSource() == miniStatement) navigate(new MiniStatement(cardno));
        else if (ae.getSource() == pinChange) navigate(new PinChange(cardno));
        else if (ae.getSource() == netBanking) navigate(new NetBanking(cardno));
        else if (ae.getSource() == exit) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    void navigate(JFrame frame) {
        setVisible(false);
        frame.setVisible(true);
    }
}
