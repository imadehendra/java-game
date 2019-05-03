package imadehendra;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame scr = new JFrame();

        play gameplay = new play();

        scr.setBounds(10, 10, 400, 600);
        scr.setTitle("Bola Bola Manja");
        scr.setResizable(false);
        scr.setVisible(true);
        scr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scr.add(gameplay);

    }
}
