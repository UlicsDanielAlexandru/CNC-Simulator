package controller;

import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View view;

    public Controller(View view)
    {
        this.view = view;
        view.addFileOpenButtonListener(new FileOpenButtonListener());
    }

    public class FileOpenButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = view.getFilePath();
            if(filePath != null)
                view.setFilePath(filePath);
        }
    }
}
