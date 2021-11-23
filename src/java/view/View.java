package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JButton simulateButton = new JButton("Simulate");
    private JTextField filePathTextField = new JTextField();
    private JButton fileOpenButton = new JButton("Open");
    private JLabel layoutDimensionLabel = new JLabel("Select layout dimension:");
    private String[] layoutDimensions = {"100x100", "200x200"};
    private JComboBox<String> layoutDimensionComboBox = new JComboBox<>(layoutDimensions);
    private JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT,40,10));
    private FileDialog fileChooser = new FileDialog(this,"Choose file", FileDialog.LOAD);

    public View()
    {
        filePathTextField.setPreferredSize(new Dimension(700,23));
        content.add(filePathTextField);
        content.add(fileOpenButton);
        content.add(layoutDimensionLabel);
        content.add(layoutDimensionComboBox);
        content.add(simulateButton);
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1022,800);
        this.setVisible(true);
    }

    public String getFilePath()
    {
        fileChooser.setVisible(true);
        return fileChooser.getDirectory() + fileChooser.getFile();
    }

    public void setFilePath(String path)
    {
        filePathTextField.setText(path);
    }

    public void addFileOpenButtonListener(ActionListener fileOpenButtonListener)
    {
        fileOpenButton.addActionListener(fileOpenButtonListener);
    }
}
