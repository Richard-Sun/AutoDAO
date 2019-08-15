package autodem.other;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class projectClass implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        MyFrame.project.setText("工程: " + file.getAbsolutePath());
        MyFrame.projectPath = file.getAbsolutePath();
    }
}
