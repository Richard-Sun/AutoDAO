package autodem.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {


    private JFrame f;
    private JPanel p;
    private JButton openSql;
    private JButton openProject;
    private JButton start;
    public static JLabel sql;
    public static JLabel project;
    public static String sqlPath = "";
    public static String projectPath = "";

    public MyFrame() {
        f = new JFrame("AutoDEM");
        p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 15));
        f.setSize(500, 430);
        f.setLocationRelativeTo(null);
        f.add(p);
        setLabel();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setButton();
    }

    private void setLabel() {
        JLabel title = new JLabel("AutoDEM");
        JLabel title2 = new JLabel("自动生成Entity,Dao,Mapper");
        title.setFont(new Font("Microsoft YaHei", Font.PLAIN, 30));
        title2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        p.add(title);
        p.add(title2);
        title.setVisible(true);
        title2.setVisible(true);
    }

    private void setButton() {
        sql = new JLabel("SQL文件: ");
        sql.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        project = new JLabel("工程: ");
        project.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        p.add(sql);
        p.add(project);
        openSql = new JButton("浏览sql文件");
        openSql.setPreferredSize(new Dimension(150, 50));
        openSql.addActionListener(new sqlClass());
        openProject = new JButton("浏览project");
        openProject.setPreferredSize(new Dimension(150, 50));
        openProject.addActionListener(new projectClass());
        start = new JButton("开始生成");
        start.setPreferredSize(new Dimension(150, 50));
        start.addActionListener(new startClass());
        p.add(openSql);
        p.add(openProject);
        p.add(start);
    }


}
