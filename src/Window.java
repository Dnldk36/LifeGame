import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements Runnable {

    JFrame frame;
    Box[][] boxes;
    float time = 0f;

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.WIDTH * Config.SIZE + 14, Config.HEIGHT * Config.SIZE + 37);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Life Game");
    }

    void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }
        for (int x = 0; x < Config.WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                for (int sx = -1; sx <= +1; sx++)
                    for (int sy = -1; sy <= +1; sy++)
                        if (!(sx == 0 && sy == 0))
                            boxes[x][y].cell.addNear(boxes
                                    [(x + sx + Config.WIDTH) % Config.WIDTH]
                                    [(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);
        int centerX = 0; int centerY = 0;
        centerX = Config.WIDTH/2; centerY = Config.HEIGHT/2;
        for (int x = centerX-5; x < centerX+5; x++) {
            boxes[x][centerY].cell.status = Status.LIFE;
            boxes[x][centerY].setColor();
        }
    }

    private void initTimer() {
        TimerListener tl = new TimerListener();
        Timer timer = new Timer(Config.SLEEPMS, tl);
        timer.start();
    }

    private class TimerListener implements ActionListener {
        boolean flop = false;

        int score = 0;


        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            int life = 0;
            for (int x = 0; x < Config.WIDTH; x++)
                for (int y = 0; y < Config.HEIGHT; y++) {

                    if (boxes[x][y].cell.status == Status.LIFE) life++;

                    if (flop) {
                        boxes[x][y].step1();
                    } else {
                        boxes[x][y].step2();
                    }

                }
            score+=life;
            time+=Config.SLEEPMS/1000.;
            System.out.println("Время: " + time + "c");
            System.out.println("Население: " + score);
            score=0;
            final String ANSI_CLS = "\u001b[2J";
            final String ANSI_HOME = "\u001b[H";
            System.out.print(ANSI_CLS + ANSI_HOME);
            System.out.flush();
        }
    }
}
