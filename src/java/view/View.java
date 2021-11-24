package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private JButton simulateButton = new JButton("Simulate");
    private JTextField filePathTextField = new JTextField();
    private JButton fileOpenButton = new JButton("Open");
    private JLabel layoutDimensionLabel = new JLabel("Select layout dimension:");
    private String[] layoutDimensions = {"120x120", "80x80", "50x50"};
    private JComboBox<String> layoutDimensionComboBox = new JComboBox<>(layoutDimensions);
    private JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT,40,10));
    private FileDialog fileChooser = new FileDialog(this,"Choose file", FileDialog.LOAD);
    private Grid grid = new Grid(650,650);

    public View()
    {
        filePathTextField.setPreferredSize(new Dimension(700,23));
        content.add(filePathTextField);
        content.add(fileOpenButton);
        content.add(layoutDimensionLabel);
        content.add(layoutDimensionComboBox);
        content.add(simulateButton);
        content.add(grid);
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

    public void replaceGrid(int width, int height)
    {
        content.remove(grid);
        grid = new Grid(width, height);
        content.add(grid);
        content.revalidate();
        content.repaint();
    }

    public int getSelectedLayoutIndex()
    {
        return layoutDimensionComboBox.getSelectedIndex();
    }

    public void addFileOpenButtonListener(ActionListener fileOpenButtonListener)
    {
        fileOpenButton.addActionListener(fileOpenButtonListener);
    }

    public void addLayoutDimensionComboBoxListener(ActionListener layoutDimensionComboBoxListener)
    {
        layoutDimensionComboBox.addActionListener(layoutDimensionComboBoxListener);
    }
}
