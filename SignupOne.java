package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, emailTextField,
            addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dateChooser;

    SignupOne() {

        setTitle("Signup - Page 1");

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
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        //  Card Panel
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setPreferredSize(new Dimension(650, 900));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //  Scroll Pane
        JScrollPane scrollPane = new JScrollPane(card);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane, BorderLayout.CENTER);

        //  Step Indicator
        JLabel step = new JLabel("Step 1 of 3");
        step.setBounds(520, 10, 120, 20);
        step.setFont(new Font("Segoe UI", Font.BOLD, 14));
        card.add(step);

        //  Form Number
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setBounds(120, 40, 400, 30);
        formno.setFont(new Font("Segoe UI", Font.BOLD, 20));
        card.add(formno);

        JLabel title = new JLabel("Personal Details");
        title.setBounds(200, 80, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        card.add(title);

        int y = 140;

        //  Name
        card.add(label("👤 Name", 50, y));
        nameTextField = field(250, y);
        card.add(nameTextField);

        //  Father's Name
        y += 50;
        card.add(label("👨 Father's Name", 50, y));
        fnameTextField = field(250, y);
        card.add(fnameTextField);

        //  DOB
        y += 50;
        card.add(label("🎂 Date of Birth", 50, y));
        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, y, 300, 30);
        card.add(dateChooser);

        //  Gender
        y += 50;
        card.add(label("🚻 Gender", 50, y));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        genderPanel.setBounds(250, y, 300, 30);
        genderPanel.setBackground(Color.WHITE);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBackground(Color.WHITE);
        female.setBackground(Color.WHITE);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        genderPanel.add(male);
        genderPanel.add(female);
        card.add(genderPanel);

        //  Email
        y += 50;
        card.add(label("📧 Email", 50, y));
        emailTextField = field(250, y);
        card.add(emailTextField);

        // Marital Status
        y += 50;
        card.add(label("💍 Marital Status", 50, y));

        JPanel maritalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        maritalPanel.setBounds(250, y, 350, 30);
        maritalPanel.setBackground(Color.WHITE);

        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");
        other = new JRadioButton("Other");

        married.setBackground(Color.WHITE);
        unmarried.setBackground(Color.WHITE);
        other.setBackground(Color.WHITE);

        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(other);

        maritalPanel.add(married);
        maritalPanel.add(unmarried);
        maritalPanel.add(other);
        card.add(maritalPanel);

        //  Address
        y += 50;
        card.add(label("🏠 Address", 50, y));
        addressTextField = field(250, y);
        card.add(addressTextField);

        // City
        y += 50;
        card.add(label("🌆 City", 50, y));
        cityTextField = field(250, y);
        card.add(cityTextField);

        //  State
        y += 50;
        card.add(label("🏙 State", 50, y));
        stateTextField = field(250, y);
        card.add(stateTextField);

        //  PIN
        y += 50;
        card.add(label("📍 PIN Code", 50, y));
        pinTextField = field(250, y);
        card.add(pinTextField);

        //  NEXT BUTTON
        y += 70;
        next = new JButton("NEXT ➜");
        next.setBounds(250, y, 160, 45);
        next.setBackground(new Color(0, 102, 204));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 15));
        next.setFocusPainted(false);
        addHover(next);
        next.addActionListener(this);
        card.add(next);

        setSize(850, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Label
    JLabel label(String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 180, 25);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return l;
    }

    //  TextField
    JTextField field(int x, int y) {
        JTextField f = new JTextField();
        f.setBounds(x, y, 300, 30);
        f.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                f.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            }

            public void focusLost(FocusEvent e) {
                f.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        });

        return f;
    }

    // Hover Effect
    void addHover(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(btn.getBackground().darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0, 102, 204));
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {

        String formno = "" + random;

        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required");
            return;
        }

        if (!emailTextField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(null, "Invalid Email");
            return;
        }

        if (!pinTextField.getText().matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "PIN must be 6 digits");
            return;
        }

        try {
            Conn c = new Conn();

            String query = "insert into signup values('" + formno + "','" +
                    nameTextField.getText() + "','" +
                    fnameTextField.getText() + "','" +
                    ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText() + "','" +
                    (male.isSelected() ? "Male" : "Female") + "','" +
                    emailTextField.getText() + "','" +
                    (married.isSelected() ? "Married" : unmarried.isSelected() ? "Unmarried" : "Other") + "','" +
                    addressTextField.getText() + "','" +
                    cityTextField.getText() + "','" +
                    pinTextField.getText() + "','" +
                    stateTextField.getText() + "')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Page 1 Completed ✅");

            setVisible(false);
            new SignupTwo(formno);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
