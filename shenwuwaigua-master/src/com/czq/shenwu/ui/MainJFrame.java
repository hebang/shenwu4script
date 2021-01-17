package com.czq.shenwu.ui;

import com.czq.shenwu.controller.IController;
import com.czq.shenwu.model.base.NullQuery;
import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.view.IView;
import com.czq.shenwu.ui.view.jpanel.LeftJPanel;
import com.czq.shenwu.ui.view.jpanel.MiddleJPanel;
import com.czq.shenwu.ui.view.jpanel.RightJPanel;

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame implements NullQuery,IViewObserver, IView {

    private final LeftJPanel leftJPanel;
    private final MiddleJPanel middleJPanel;
    private final RightJPanel rightJPanel;

    IController mController;

    private MainJFrame() {
        leftJPanel = new LeftJPanel();
        middleJPanel = new MiddleJPanel();
        rightJPanel = new RightJPanel();

        //初始化JFrame参数
        this.setLayout(new GridLayout(1,3,10,13));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);   //setResizable决定了窗体是否可以放大。true则可以最大化，false则不可变

        //JFrame添加控件
        this.add(leftJPanel);
        this.add(middleJPanel);
        this.add(rightJPanel);

        leftJPanel.setCheckButtonListen(new LeftJPanel.CheckButtonListen() {
            @Override
            public void checkButtonStateChange(CheckStateVO checkStateVO) {
                if (mController != null) {
                    mController.checkButtonStateChange(checkStateVO);
                }
            }
        });
        middleJPanel.setMainButtonStateListener(new MiddleJPanel.MainButtonStateListener() {
            @Override
            public void onStart() {
                if (mController != null) {
                    mController.onBattle();
                }
            }
        });
        rightJPanel.setSelectStartegyListen(new RightJPanel.SelectStartegyListen() {
            @Override
            public void startegyChange(String startegyName) {
                if (mController != null) {
                    mController.startegyChange(startegyName);
                }
            }
        });
        rightJPanel.setSelectThemeListen(new RightJPanel.SelectThemeListen() {
            @Override
            public void themeChanage(String themeName) {
                if (mController != null) {
                    mController.themeChange(themeName);
                }
            }
        });

    }



    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onArena() {

    }

    @Override
    public void outArena() {

    }

    @Override
    public void onStop() {

    }
    @Override
    public boolean isStart() {
        return middleJPanel.isStart();
    }

    @Override
    public void setCheckButtonState(CheckStateVO checkButtonState) {
        leftJPanel.setCheckState(checkButtonState);
    }

    @Override
    public void bindConstant(IController iController) {
        mController = iController;
    }

    @Override
    public void unBindConstant() {
        if (mController != null) {
            mController.unBindView();
            mController = null;
        }
    }




    private static class Single {
        private static final MainJFrame INSTANCE = new MainJFrame();
    }
    public static MainJFrame getInstance() {
        return Single.INSTANCE;
    }
}
