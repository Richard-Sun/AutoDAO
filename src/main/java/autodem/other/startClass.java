package autodem.other;

import autodem.core.DaoGenerator;
import autodem.core.EntityGenerator;
import autodem.core.MapperGenerator;
import autodem.core.SqlReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startClass implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        JFrame j = new JFrame();
        j.setSize(150, 150);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        JLabel label = new JLabel();
        label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 40));
        panel.add(label);
        j.add(panel);

        try {
            if (!("".equals(MyFrame.sqlPath)) && !("".equals(MyFrame.projectPath))) {
                SqlReader sr = new SqlReader(MyFrame.sqlPath, MyFrame.projectPath);
                EntityGenerator g = new EntityGenerator(sr);
                Boolean gg = g.generate();
                MapperGenerator m = new MapperGenerator(sr);
                Boolean mm = m.generate();
                DaoGenerator d = new DaoGenerator(sr);
                Boolean dd = d.generate();
                if (gg && mm && dd){
                    label.setText("Succeed!");
                    return;
                }
            }
        } catch (Throwable throwable) {
        }
        label.setText("Failed!");
        return;
    }
}
