package com.taohansi.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.taohansi.been.gameData;
import com.taohansi.been.user;
import com.taohansi.been.point;
import com.taohansi.util.rules;

public class draw extends JPanel{
    private int round;
    private int x;
    private int y;
    private user gamer;
    private rules rule;
    private int tempx;
    private int tempy;
    private boolean gameStart;//true start
    private int turn;// 1 black 2white
    private int[][] location;// 1black 2 white
    private int gameState;//1 ren 2ji
    private Socket s;

    public draw(){
        this.setSize(500,500);
        location=new int [15][15];
        tempx=-1;
        tempy=-1;

        rule=new rules(location);
        setBackground(new Color(238,197,145));
        addListener();



    }
    public void check(){
        if(rule.isOver()==1) {
            qipan.gameinfo.setText("the black win!");
            gameStart=false;

        }
        if(rule.isOver()==2){
            qipan.gameinfo.setText("the white win!");
            gameStart=false;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintPanel(g);
        paintChess(g);
        paintTemp(g);



    }
    public void setx(int a){
        x=a;

    }
    public int  getx(){
       return x;
    }
    public void sety(int a){
        y=a;

    }
    public int  gety(){
        return y;
    }
    public void paintPanel(Graphics g){

        setx(75);
        sety(55);
        Graphics2D g2d=(Graphics2D)g;
        for(int i=0;i<15;i++) {
            if(i==0||i==14)
                g2d.setStroke(new BasicStroke(3f));
            addLabel(i + 1, x - 19, 30 * i + y + 3, g);
            addLabel(i + 1, 30 * i + x - 3, y - 10, g);
            g.drawLine(0 + x, 30 * i + y, 14 * 30 + x, 30 * i + y);
            g.drawLine(30 * i + x, 0 + y, 30 * i + x, 14 * 30 + y);
           g2d.setStroke(new BasicStroke());
        }
        int ovalwidth=8;
        int ovalhigh=8;
        int py=ovalwidth/2;
        g.fillOval(30*3+x-py,30*11+y-py,ovalwidth,ovalhigh);
        g.fillOval(30*3+x-py,30*3+y-py,ovalwidth,ovalhigh);
        g.fillOval(30*11+x-py,30*11+y-py,ovalwidth,ovalhigh);
        g.fillOval(30*11+x-py,30*3+y-py,ovalwidth,ovalhigh);
        g.fillOval(30*7+x-py,30*7+y-py,ovalwidth,ovalhigh);

    }
    public void addLabel(int name,int x,int y,Graphics g){
        Font font=new Font("微软雅黑",Font.BOLD,13);
        g.setFont(font);
        g.drawString(Integer.toString(name),x,y);
    }
    public void paintChess(Graphics g){
        round=25;
       for(int i=0;i<15;i++)
           for(int j=0;j<15;j++)
           if(location[i][j]==1){
              g.setColor(Color.black);
              g.fillOval(30*i+x-round/2,30*j+y-round/2,round,round);
           }
           else if(location [i][j]==2){
               g.setColor(Color.white);
               g.fillOval(30*i+x-round/2,30*j+y-round/2,round,round);

           }
    }

    public user getGamer() {
        return gamer;
    }

    public void setGamer(user gamer) {
        this.gamer = gamer;
    }
    public void newGame(){
        location=new int[15][15];
        rule=new rules(location);
        gameStart=true;
        turn=1;
    }
    public void addListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameStart==false) return ;
                if(gamer.getColor()!=turn) return ;//顺序

                int eventx=e.getX();
                int eventy=e.getY();// 转换下标

                eventx=(eventx-x+15)/30;
                eventy=(eventy-y+15)/30;
                if(eventx<0||eventx>=15||eventy<0||eventy>=15)return ;
                if(location[eventx][eventy]!=0)return;
                location[eventx][eventy]=gamer.getColor();

                repaint();//重调paint()
                changeTurn();
                if(gameState==1){
                    // ren ren
                    gameData data=new gameData();
                    data.setGamer(gamer);
                    data.setP(new point(eventx,eventy));
                    sendInfo2Sever(data);
                }else if(gameState==2) {
                    check();
                    computerNextStep();
                    check();
                }

            }


        });
    this.addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            if(gameStart==false) return;
            if(gamer.getColor()!=turn) return;

            int eventx=e.getX();
            int eventy=e.getY();
            eventx=(int)Math.round((eventx-x)/30.0);
            eventy=(int)Math.round((eventy-y)/30.0);
            if(eventx<0||eventx>=15||eventy<0||eventy>=15) return ;

            tempx=eventx;
            tempy=eventy;
            repaint();
        }
    });}
        public void paintTemp(Graphics g){
        g.setColor(Color.black);
        if(tempx<0||tempy<0) return;
        int length=8;
        g.drawLine(30*tempx+x-15,30*tempy+y-15,30*tempx+x-15+length,30*tempy+y-15);
        g.drawLine(30*tempx+x+15,30*tempy+y-15,30*tempx+x+15-length,30*tempy+y-15);
        g.drawLine(30*tempx+x-15,30*tempy+y+15,30*tempx+x-15+length,30*tempy+y+15);
        g.drawLine(30*tempx+x+15,30*tempy+y+15,30*tempx+x+15-length,30*tempy+y+15);
        g.drawLine(30*tempx+x-15,30*tempy+y-15,30*tempx+x-15,30*tempy+y-15+length);
        g.drawLine(30*tempx+x-15,30*tempy+y+15,30*tempx+x-15,30*tempy+y+15-length);
        g.drawLine(30*tempx+x+15,30*tempy+y-15,30*tempx+x+15,30*tempy+y-15+length);
        g.drawLine(30*tempx+x+15,30*tempy+y+15,30*tempx+x+15,30*tempy+y+15-length);


        }
    public void changeTurn(){
        turn=turn%2+1;
        if(turn==2){
        qipan.gameinfo.setText("it's turn to white");
        //gamer.setColor(2);
        }
        if(turn==1) {
            qipan.gameinfo.setText("it's turn to black");
            //gamer.setColor(1);
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public void sendInfo2Sever(gameData data){

    }
    public void computerNextStep(){
        int computerColor;
        int x;
        int y;
        while(true) {
            x = (int) (Math.random() * 15);
             y = (int) (Math.random() * 15);
            if (location[x][y] == 0) break;
        }
      if(gameStart==false) return;
        computerColor=gamer.getColor()%2+1;
        location[x][y]=computerColor;
        changeTurn();
        repaint();
    }
    public void firstStep(){
        location[7][7]=1;
        changeTurn();
        repaint();
    }
}
