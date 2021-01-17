package com.czq.shenwu.ui.view.jpanel;

import com.czq.shenwu.ui.view.button.MainButton;
import com.czq.shenwu.ui.view.jpanel.base.BasePanel;
import com.czq.shenwu.utils.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MiddleJPanel extends BasePanel  {
    private static final String TAG = "MiddleJPanel";
    private MainButton mainButton;
    private boolean isStart = false;

    private MainButtonStateListener mMainButtonStateListener;

    public MiddleJPanel() {
        this.mainButton = new MainButton();
        setLayout(new CardLayout());
        mainButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStart = !isStart;
                if (isStart) {
                    if (mMainButtonStateListener != null) {
                        LogUtils.d(TAG,"开启外挂!");
                        mMainButtonStateListener.onStart();
                    }
                    mainButton.setText(MainButton.STOP);
                } else {
                    LogUtils.d(TAG,"关闭外挂!");
                    mainButton.setText(MainButton.START);
                }
            }
        });
        add(mainButton);
    }

    public boolean isStart() {
        return isStart;
    }

    public void setMainButtonStateListener(MainButtonStateListener mainButtonStateListener) {
        mMainButtonStateListener = mainButtonStateListener;
    }

    public interface MainButtonStateListener {
        void onStart();
    }

}
