package com.taohansi.login;

import javax.swing.*;
import java.awt.*;
import com.taohansi.ui.qipan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame{
    private JLabel name;
    private JTextField textName;
    private int length;
    private JButton yes;
    private int high;
    private JPanel paenl;
    private JButton no;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public JTextField getTextName() {
        return textName;
    }

    public login(){
        super("登陆");
        setHigh(200);
        setLength(350);
        init();
        this.setSize(length,high);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        addListener();
    }

    public void init(){
        name= new JLabel("登陆");
        textName=new JTextField(10);
        yes=new JButton("login");
        no=new JButton("cancel");
        paenl=new JPanel();
        this.add(paenl);
        paenl.setLayout(null);
        name.setBounds(10,20,80,25);
        textName.setBounds(60,20,165,25);
        yes.setBounds(10,80,80,25);
        no.setBounds(100,80,80,25);
        paenl.add(name);
        paenl.add(textName);
        paenl.add(yes);
        paenl.add(no);

    }
    public void addListener(){
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qipan.userName=textName.getText();
                login.this.setVisible(false);
                login.this.dispose();
                qipan.userinfo.setText("user: "+qipan.userName);

            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.this.setVisible(false);
                login.this.dispose();
            }
        });
    }
}
