package imadehendra;

import java.awt.*;

public class bata {

    public int bt [][];
    public int bwidth;
    public int bheight;
    public bata(int bar, int kol) {


            bt = new int[bar][kol];
            for (int i = 0; i < bt.length; i++) {
                for (int j = 0; j < bt[0].length; j++) {
                    bt[i][j] = 1;
                }
            }

            bwidth = 340 / kol;
            bheight = 15;
        }

    public void  draw(Graphics2D g){

        for(int i = 0; i < bt.length; i++){
            for(int j =0; j < bt[0].length; j++){
                if (bt [i][j] > 0){
                    g.setColor(Color.RED);
                    g.fillRect(j * bwidth + 32,i * bheight + 50, bwidth, bheight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.WHITE);
                    g.drawRect(j * bwidth + 32,i * bheight + 50, bwidth, bheight);

                }

            }
        }

    }

    public void setBtValue (int value, int bar, int kol){
        bt[bar][kol] = value;

        System.out.println(value);
    }

}
