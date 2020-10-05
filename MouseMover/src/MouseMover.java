import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MouseMover {
    public static final int FIVE_SECONDS = 8000;
    public static final int MAX_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int MAX_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static boolean instantMovement = false;


    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();

        Thread mouseMoverThreat = new Thread() {
            public void run() {

                while (true) {

                    if(instantMovement) {
                        //instant
                        robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
                    } else {
                        //smooth
                        int startX = MouseInfo.getPointerInfo().getLocation().x;
                        int startY = MouseInfo.getPointerInfo().getLocation().y;
                        int endX = random.nextInt(MAX_X);
                        int endY = random.nextInt(MAX_Y);
                        mouseGlide(startX, startY, endX, endY,400,1000);

                    }
                    try {
                        Thread.sleep(FIVE_SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        mouseMoverThreat.start();
        mouseMoverThreat.suspend();

        System.out.println(MAX_X + ":" + MAX_Y);


        JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("AIBMM - Automatic Inactivity Blocking Mouse Mover");


        JLabel mouseMoverStateLabel = new JLabel("Mouse Mover: Offline");
        mouseMoverStateLabel.setForeground(Color.RED);
        frame.add(mouseMoverStateLabel);
        mouseMoverStateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton stopMouseMoverButton = new JButton("Stop Mouse Mover");
        frame.add(stopMouseMoverButton);
        stopMouseMoverButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        stopMouseMoverButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                 mouseMoverThreat.suspend();
                 mouseMoverStateLabel.setText("Mouse Mover: Offline");
                 mouseMoverStateLabel.setForeground(Color.RED);
                }
            }
        );


        JButton startMouseMoverButton = new JButton("Start Mouse Mover");
        frame.add(startMouseMoverButton);
        startMouseMoverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startMouseMoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseMoverThreat.resume();
                mouseMoverStateLabel.setText("Mouse Mover: Online");
                mouseMoverStateLabel.setForeground(new Color(0,170,0));

            }
        });


        JCheckBox instantMovementCheckBox = new JCheckBox("Instant Movement:", false);
        instantMovementCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instantMovement = instantMovementCheckBox.isSelected();

            }
        });
        frame.add(instantMovementCheckBox);
        instantMovementCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);


        frame.setSize(new Dimension(250,150));
        frame.setVisible(true);


//        while (true) {
//            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
//            Thread.sleep(FIVE_SECONDS);
//        }
    }

    public static void mouseGlide(int x1, int y1, int x2, int y2, int t, int n) {
            try {
                Robot r = new Robot();
                double dx = (x2 - x1) / ((double) n);
                double dy = (y2 - y1) / ((double) n);
                double dt = t / ((double) n);
                for (int step = 1; step <= n; step++) {
                    Thread.sleep((int) dt);
                    r.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
                }
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}