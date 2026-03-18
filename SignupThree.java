package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {
    
    JRadioButton savingAcc, fixedDepositAcc, currentAcc, recurringDepositAcc;
    JCheckBox atmCard, internetBanking, mobileBanking, emailAlerts, chequeBook, eStatement, declaration;
    JButton submit, cancel;
    String formno;
    
    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null); 
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");
        
        JLabel additionalDetails = new JLabel("Page 3: Account Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(280, 40, 400, 40);
        add(additionalDetails); 
        
        JLabel accountType = new JLabel("Account Type:");
        accountType.setFont(new Font("Raleway", Font.BOLD, 18));
        accountType.setBounds(100, 100, 200, 30);
        add(accountType);
        
        savingAcc = new JRadioButton("Saving Account");
        savingAcc.setBounds(100, 140, 200, 30);
        savingAcc.setBackground(Color.WHITE);
        add(savingAcc);
        
        fixedDepositAcc = new JRadioButton("Fixed Deposit Account");
        fixedDepositAcc.setBounds(350, 140, 250, 30);
        fixedDepositAcc.setBackground(Color.WHITE);
        add(fixedDepositAcc);
        
        currentAcc = new JRadioButton("Current Account");
        currentAcc.setBounds(100, 180, 200, 30);
        currentAcc.setBackground(Color.WHITE);
        add(currentAcc);
        
        recurringDepositAcc = new JRadioButton("Recurring Deposit Account");
        recurringDepositAcc.setBounds(350, 180, 250, 30);
        recurringDepositAcc.setBackground(Color.WHITE);
        add(recurringDepositAcc);
        
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savingAcc);
        accountGroup.add(fixedDepositAcc);
        accountGroup.add(currentAcc);
        accountGroup.add(recurringDepositAcc);
        
        JLabel cardNumber = new JLabel("Card Number:");
        cardNumber.setFont(new Font("Raleway", Font.BOLD, 18));
        cardNumber.setBounds(100, 240, 200, 30);
        add(cardNumber);
        
        JLabel cardValue = new JLabel("XXXX-XXXX-XXXX-4184");
        cardValue.setFont(new Font("Raleway", Font.BOLD, 18));
        cardValue.setBounds(330, 240, 250, 30);
        add(cardValue);
        
        JLabel cardNote = new JLabel("Your 16 Digit Card Number");
        cardNote.setFont(new Font("Raleway", Font.PLAIN, 12));
        cardNote.setBounds(100, 270, 200, 20);
        add(cardNote);
        
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        pinLabel.setBounds(100, 310, 200, 30);
        add(pinLabel);
        
        JLabel pinValue = new JLabel("XXXX");
        pinValue.setFont(new Font("Raleway", Font.BOLD, 18));
        pinValue.setBounds(330, 310, 200, 30);
        add(pinValue);
        
        JLabel pinNote = new JLabel("Your 4 Digit Password");
        pinNote.setFont(new Font("Raleway", Font.PLAIN, 12));
        pinNote.setBounds(100, 340, 200, 20);
        add(pinNote);
        
        JLabel servicesLabel = new JLabel("Services Required:");
        servicesLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        servicesLabel.setBounds(100, 380, 200, 30);
        add(servicesLabel);
        
        atmCard = new JCheckBox("ATM CARD");
        atmCard.setBounds(100, 420, 200, 30);
        atmCard.setBackground(Color.WHITE);
        add(atmCard);
        
        internetBanking = new JCheckBox("Internet Banking");
        internetBanking.setBounds(350, 420, 200, 30);
        internetBanking.setBackground(Color.WHITE);
        add(internetBanking);
        
        mobileBanking = new JCheckBox("Mobile Banking");
        mobileBanking.setBounds(100, 460, 200, 30);
        mobileBanking.setBackground(Color.WHITE);
        add(mobileBanking);
        
        emailAlerts = new JCheckBox("EMAIL & SMS Alerts");
        emailAlerts.setBounds(350, 460, 200, 30);
        emailAlerts.setBackground(Color.WHITE);
        add(emailAlerts);
        
        chequeBook = new JCheckBox("Cheque Book");
        chequeBook.setBounds(100, 500, 200, 30);
        chequeBook.setBackground(Color.WHITE);
        add(chequeBook);
        
        eStatement = new JCheckBox("E-Statement");
        eStatement.setBounds(350, 500, 200, 30);
        eStatement.setBackground(Color.WHITE);
        add(eStatement);
        
        declaration = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.", false);
        declaration.setBounds(100, 540, 600, 20);
        declaration.setBackground(Color.WHITE);
        add(declaration);
        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 580, 100, 30);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 580, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 650);
        setLocation(350, 10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit) {
            String atype = null;
            if(savingAcc.isSelected()) atype = "Saving Account";
            else if(fixedDepositAcc.isSelected()) atype = "Fixed Deposit Account";
            else if(currentAcc.isSelected()) atype = "Current Account";
            else if(recurringDepositAcc.isSelected()) atype = "Recurring Deposit Account";
            
            Random ran = new Random();
            long first7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
            String cardnumber = "" + Math.abs(first7);
            
            long first3 = (ran.nextLong() % 9000L) + 1000L;
            String pin = "" + Math.abs(first3);
            
            String facility = "";
            if(atmCard.isSelected()) facility += " ATM Card";
            if(internetBanking.isSelected()) facility += " Internet Banking";
            if(mobileBanking.isSelected()) facility += " Mobile Banking";
            if(emailAlerts.isSelected()) facility += " EMAIL & SMS Alerts";
            if(chequeBook.isSelected()) facility += " Cheque Book";
            if(eStatement.isSelected()) facility += " E-Statement";
            
            try {
                Conn c = new Conn();
                String q1 = "INSERT INTO signupthree (formno, accounttype, cardnumber, pin, facility) " +
                            "VALUES ('"+formno+"', '"+atype+"', '"+cardnumber+"', '"+pin+"', '"+facility+"')";
                String q2 = "INSERT INTO login (formno, cardnumber, pin) " +
                            "VALUES ('"+formno+"', '"+cardnumber+"', '"+pin+"')";
                c.s.executeUpdate(q1);
                c.s.executeUpdate(q2);
                JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n Pin: " + pin);
                setVisible(false);
                new Transactions(pin).setVisible(true);
            } catch(Exception e) {
                System.out.println(e) ;
            }
        } else if(ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }
    
    public static void main(String args[]) {
        new SignupThree("");
    }
}