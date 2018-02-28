package com.taohansi.util;

public class rules {
    private int [][]qizi;
    private static final int FIVE=500;
    private static final int FOURE_LIVE=90;
    private static final int FOUR_DEAD=60;
    private static final int THREE_LIVE=50;
    private static final int THREE_DEAD=30;
    private static final int TWO_LIVE=20;
    private static final int TWO_DEAD=10;


    public rules(int [][]qizi){
        this.qizi=qizi;

    }
    // 1 blackwin 2white win
    public int isOver() {
          for(int i=0;i<15;i++)
              for(int j=0;j<15;j++) {
                      if (i+4<15&&qizi[i][j] == 1&&qizi[i + 1][j] == 1&&qizi[i + 2][j] == 1&&qizi[i + 3][j] == 1&&qizi[i + 4][j] == 1)
                          return 1;
                      if (i+4<15&&qizi[i][j] == 2&&qizi[i + 1][j] == 2&&qizi[i + 2][j] == 2&&qizi[i + 3][j] == 2&&qizi[i + 4][j] == 2)
                          return 2;
                      if(j+4<15&&qizi[i][j]==1&&qizi[i][j+1]==1&&qizi[i][j+2]==1&&qizi[i][j+3]==1&&qizi[i][j+4]==1)
                          return 1;
                      if(j+4<15&&qizi[i][j]==2&&qizi[i][j+1]==2&&qizi[i][j+2]==2&&qizi[i][j+3]==2&&qizi[i][j+4]==2)
                          return 2;
                      if(i-4>=0&&j+4<15&&qizi[i][j]==1&&qizi[i-1][j+1]==1&&qizi[i-2][j+2]==1&&qizi[i-3][j+3]==1&&qizi[i-4][j+4]==1)
                          return 1;
                      if(i-4>=0&&j+4<15&&qizi[i][j]==2&&qizi[i-1][j+1]==2&&qizi[i-2][j+2]==2&&qizi[i-3][j+3]==2&&qizi[i-4][j+4]==2)
                          return 2;
                      if(i+4<15&&j+4<15&&qizi[i][j]==1&&qizi[i+1][j+1]==1&&qizi[i+2][j+2]==1&&qizi[i+3][j+3]==1&&qizi[i+4][j+4]==1)
                          return 1;
                      if(i+4<15&&j+4<15&&qizi[i][j]==2&&qizi[i+1][j+1]==2&&qizi[i+2][j+2]==2&&qizi[i+3][j+3]==2&&qizi[i+4][j+4]==2)
                          return 2;
              }
              return 0;
    }

    public int score(int x,int y,int cloor){
     return 0;

    }
}
