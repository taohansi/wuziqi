package com.taohansi.ui;
import javax.swing.*;
import java.awt.*;
import com.taohansi.login.login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.taohansi.been.user;
public class qipan extends JFrame {
    private JMenuBar bar;
    private JMenu system;
    private JMenu renren;
    private JMenuItem logingame;
    private JMenuItem renrenBlack;
    private JMenuItem renrenWhite;
    private JMenu renji;
    private JMenuItem renjiBlack;
    private JMenuItem renjiWhite;
    private draw paintui;
    public static JLabel userinfo;
    public static JLabel gameinfo;
    public static String userName;
    private login loginuser;
    private int width;
    private int high;


    public qipan(){
        super("五子棋");
        init();
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        width=(int)d.getWidth();
        high=(int)d.getHeight();
        this.setLocation((width-600)/2,(high-600)/2);
        this.setVisible(true);
        addListener();

    }
    public void init(){
        bar=new JMenuBar();
        system=new JMenu("game");
        renren=new JMenu("human-human");
        renji=new JMenu("human-computer");
        logingame=new JMenuItem("login");
        renrenBlack=new JMenuItem("black");
        renrenWhite=new JMenuItem("white");
        renjiBlack=new JMenuItem("black");
        renjiWhite=new JMenuItem("white");
        paintui=new draw();
        userinfo=new JLabel("no-user");
        userinfo.setHorizontalAlignment(JLabel.CENTER);
        gameinfo=new JLabel("no-game");
        gameinfo.setHorizontalAlignment(JLabel.CENTER);

        bar.add(system);
        system.add(logingame);
        system.add(renren);
        system.add(renji);

        renji.add(renjiBlack);
        renji.add(renjiWhite);
        renren.add(renrenBlack);
        renren.add(renrenWhite);

       // JLabel test =new JLabel("123");

        //paintui.add(test);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(bar);
        this.add(userinfo,BorderLayout.NORTH);
        this.add(gameinfo,BorderLayout.SOUTH);
        this.add(paintui,BorderLayout.CENTER);

    }
    public void addListener(){
        renrenBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintui.getGamer().setColor(1);
                paintui.newGame();
                paintui.setName(userName);
                paintui.setGameState(1);
                gameinfo.setText("it's turn to balck");
                userinfo.setText("game start!");


            }
        });
        renrenWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintui.getGamer().setColor(2);
                paintui.newGame();
                paintui.setName(userName);
                gameinfo.setText("it's turn to white");
                userinfo.setText("game start!");

            }
        });
        logingame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginuser=new login();
                loginuser.setLocation((width-loginuser.getLength())/2,(high-loginuser.getHigh())/2);

            }
        });
        renjiBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintui.getGamer().setColor(1);
                paintui.newGame();
                paintui.setName(userName);
                paintui.setGameState(2);
                gameinfo.setText("it's turn to balck");
                userinfo.setText("game start!");

            }
        });
        renjiWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintui.getGamer().setColor(2);
                paintui.newGame();
                paintui.setName(userName);
                paintui.setGameState(2);
                gameinfo.setText("it's turn to white");
                userinfo.setText("game start!");
                paintui.firstStep();
            }
        });
    }

    public draw getPaintui() {
        return paintui;
    }

    public void setPaintui(draw paintui) {
        this.paintui = paintui;
    }

    public static void main(String args[]){
        user users=new user();
        users.setName("taohansi");
        qipan test=new qipan();
        test.getPaintui().setGamer(users);
    }
}
