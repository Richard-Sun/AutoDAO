package autodem.other;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private JFrame f;
    private JPanel p;


    public MyFrame() {
        f = new JFrame("AutoDEM");
        p = new JPanel();
        p.setLayout(new GridLayout(10,1));
        f.setSize(500, 400);
        f.setLocation(450, 180);
        f.add(p);
        setLabel();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setLabel() {
        JLabel title = new JLabel("AutoDEM");
        JLabel title2 = new JLabel("自动生成Entity,Dao,Mapper");
        p.add(title);
        p.add(title2);
        title.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        title2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
        title.setVisible(true);
        title2.setVisible(true);

    }
}
