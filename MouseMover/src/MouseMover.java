import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MouseMover {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int MAX_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();


    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        Random random = new Random();

        Thread mouseMoverThreat = new Thread() {
            public void run() {
                while (true) {
                    robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
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

        frame.setSize(new Dimension(250,150));
        frame.setVisible(true);


//        while (true) {
//            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
//            Thread.sleep(FIVE_SECONDS);
//        }
    }
}