package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField panTextField, aadharTextField;
    JRadioButton seniorYes, seniorNo, accYes, accNo;
    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;
    JButton next;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;

        //  Gradient Background
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(102, 126, 234),
                        0, getHeight(),
                        new Color(118, 75, 162));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        // Card Panel
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(80, 30, 680, 650);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.add(card);

        //  Step Indicator
        JLabel step = new JLabel("Step 2 of 3");
        step.setFont(new Font("Segoe UI", Font.BOLD, 14));
        step.setBounds(520, 10, 120, 20);
        card.add(step);

        //  Title
        JLabel title = new JLabel("Additional Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBounds(200, 40, 300, 30);
        card.add(title);

        int y = 100;

        //  Religion
        card.add(label("Religion", 50, y));
        religionBox = combo(new String[]{"Hindu", "Muslim", "Christian", "Sikh", "Other"}, 250, y);
        card.add(religionBox);

        // Category
        y += 50;
        card.add(label("Category", 50, y));
        categoryBox = combo(new String[]{"General", "OBC", "SC", "ST", "Other"}, 250, y);
        card.add(categoryBox);

        //  Income
        y += 50;
        card.add(label("Income", 50, y));
        incomeBox = combo(new String[]{"<1,50,000", "<2,50,000", "<5,00,000", "Above 5,00,000"}, 250, y);
        card.add(incomeBox);

        //  Education
        y += 50;
        card.add(label("Education", 50, y));
        educationBox = combo(new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate"}, 250, y);
        card.add(educationBox);

        //  Occupation
        y += 50;
        card.add(label("Occupation", 50, y));
        occupationBox = combo(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired"}, 250, y);
        card.add(occupationBox);

        //  PAN
        y += 50;
        card.add(label("PAN Number", 50, y));
        panTextField = field(250, y);
        card.add(panTextField);

        // Aadhaar
        y += 50;
        card.add(label("Aadhaar Number", 50, y));
        aadharTextField = field(250, y);
        card.add(aadharTextField);

        //  Senior Citizen
        y += 50;
        card.add(label("Senior Citizen", 50, y));

        seniorYes = radio("Yes", 250, y);
        seniorNo = radio("No", 330, y);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        card.add(seniorYes);
        card.add(seniorNo);

        //  Existing Account
        y += 50;
        card.add(label("Existing Account", 50, y));

        accYes = radio("Yes", 250, y);
        accNo = radio("No", 330, y);

        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(accYes);
        accGroup.add(accNo);

        card.add(accYes);
        card.add(accNo);

        // NEXT BUTTON
        y += 70;
        next = new JButton("NEXT ➜");
        next.setBounds(250, y, 160, 45);
        next.setBackground(new Color(102, 126, 234));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 15));
        next.setFocusPainted(false);
        addHover(next);
        next.addActionListener(this);
        card.add(next);

        setSize(850, 750);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Label
    JLabel label(String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 150, 25);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return l;
    }

    //  ComboBox
    JComboBox<String> combo(String[] items, int x, int y) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBounds(x, y, 300, 30);
        return c;
    }

    // TextField with focus effect
    JTextField field(int x, int y) {
        JTextField f = new JTextField();
        f.setBounds(x, y, 300, 30);

        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                f.setBorder(BorderFactory.createLineBorder(new Color(102, 126, 234), 2));
            }

            public void focusLost(FocusEvent e) {
                f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        });

        return f;
    }

    //  Radio Button
    JRadioButton radio(String text, int x, int y) {
        JRadioButton r = new JRadioButton(text);
        r.setBounds(x, y, 80, 30);
        r.setBackground(Color.WHITE);
        return r;
    }

    //  Hover Effect
    void addHover(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(btn.getBackground().darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(102, 126, 234));
            }
        });
    }

    // PAN VALIDATION
    public boolean isValidPAN(String pan) {
        return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
    }

    // Aadhaar VALIDATION
    public boolean isValidAadhaar(String aadhar) {
        return aadhar.matches("\\d{12}");
    }

    public void actionPerformed(ActionEvent ae) {

        String pan = panTextField.getText().toUpperCase();
        panTextField.setText(pan);

        String aadhar = aadharTextField.getText();

        if (!isValidPAN(pan)) {
            JOptionPane.showMessageDialog(null, "Invalid PAN (ABCDE1234F)");
            return;
        }

        if (!isValidAadhaar(aadhar)) {
            JOptionPane.showMessageDialog(null, "Invalid Aadhaar (12 digits)");
            return;
        }

        try {
            Conn c = new Conn();

            String query = "insert into signuptwo values('" + formno + "','" +
                    religionBox.getSelectedItem() + "','" +
                    categoryBox.getSelectedItem() + "','" +
                    incomeBox.getSelectedItem() + "','" +
                    educationBox.getSelectedItem() + "','" +
                    occupationBox.getSelectedItem() + "','" +
                    pan + "','" + aadhar + "','" +
                    (seniorYes.isSelected() ? "Yes" : "No") + "','" +
                    (accYes.isSelected() ? "Yes" : "No") + "')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Page 2 Completed ✅");

            setVisible(false);
            new SignupThree(formno);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
