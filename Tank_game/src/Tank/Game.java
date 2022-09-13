package Tank;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JFrame {
    static Scanner s = new Scanner(System.in);

    MyPanel mp = null;
    public static void main(String[] args) {
        Game game = new Game();


    }

    public Game()  {
        System.out.println("0: start a new game"+" "+"1:start an old game");
        int key = s.nextInt();
        mp = new MyPanel(key);
        this.add(mp);
        this.setVisible(true);
        Thread thread2 = new Thread(mp);
        //thread2.isDaemon();
        thread2.start();
        this.addKeyListener(mp);//Jframe listen
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)  {
                Recorder.save();
                System.exit(0);
            }
        });

    }
}
