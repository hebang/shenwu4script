package com.czq.shenwu.ui.view.button;

import com.czq.shenwu.model.base.NullQuery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCheckButton extends JButton  implements NullQuery {
    private boolean isCheck = false;
    private String name ;
    private  int num;
    public JCheckButton(String name,int num) {
        this.name = name;
        this.num = num;
        setText(name);
        setBackground(Color.GRAY);
        setForeground(Color.WHITE);
    }
    public void setCheckState(boolean isCheck) {
        this.isCheck = isCheck;
        if (isCheck) {
            this.setBackground(Color.BLUE);
        } else {
            this.setBackground(Color.GRAY);
        }
    }


    public boolean isCheck() {
        return isCheck;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
