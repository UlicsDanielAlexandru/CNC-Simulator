package controller;

import model.FileInterpreter;
import model.InitialCommandException;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View view;

    public Controller(View view)
    {
        this.view = view;
        view.addFileOpenButtonListener(new FileOpenButtonListener());
        view.addLayoutDimensionComboBoxListener(new LayoutDimensionComboBoxListener());
        view.addSimulateButtonActionListener(new SimulateButtonListener());
    }

    public class FileOpenButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = view.getFilePath();
            if(!filePath.contains("null"))
                view.setFilePath(filePath);
        }
    }

    public class LayoutDimensionComboBoxListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (view.getSelectedLayoutIndex())
            {
                case 0 -> view.replaceGrid(650,650);
                case 1 -> view.replaceGrid(450,450);
                case 2 -> view.replaceGrid(300,300);
                default -> throw new IllegalStateException();
            }
        }
    }

    public class SimulateButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(view.getTextPathTextField().equals(""))
                view.displayError("No file selected!");
            else
            {
                try {
                    view.replaceGrid();
                    FileInterpreter.interpretFile(view.getTextPathTextField());
                    view.drawCommands();
                } catch (InitialCommandException initialCommandException) {
                    view.displayError(initialCommandException.getMessage());
                }
            }
        }
    }
}
