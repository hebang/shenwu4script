package com.czq.shenwu.ui.view.jpanel.base;

import com.czq.shenwu.constant.SizeConstant;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {
    public BasePanel() {
        this.setPreferredSize(new Dimension(SizeConstant.JPANEL_WIDTH,SizeConstant.JPANEL_HEIGHT));
    }
}
