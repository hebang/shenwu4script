package com.czq.shenwu.ui.view.jpanel;

import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.view.button.JCheckButton;
import com.czq.shenwu.ui.view.jpanel.base.BasePanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LeftJPanel extends BasePanel {
    public final JCheckButton masterHp;
    public final JCheckButton masterMp;
    public final JCheckButton petHp;
    public final JCheckButton petMp;
    public final JCheckButton handleDiolog;
    private CheckButtonListen mCheckButtonListen;


    public LeftJPanel() {
        masterHp = new JCheckButton("人物HP",1);

        masterMp = new JCheckButton("人物MP111",2);

        petHp = new JCheckButton("宠物HP",3);

        petMp = new JCheckButton("宠物MP",4);

        handleDiolog = new JCheckButton("处理弹窗",5);

        this.setLayout(new GridLayout(5,5,3,3));
        this.add(masterHp);
        this.add(masterMp);
        this.add(petHp);
        this.add(petMp);
        this.add(handleDiolog);

        //设置点击监听
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckButton jCheckButton = (JCheckButton) e.getSource();
                boolean isCheck = jCheckButton.isCheck();
                jCheckButton.setCheckState(!isCheck);
                if (mCheckButtonListen != null)
                    mCheckButtonListen.checkButtonStateChange(getCheckStateVO());
            }
        };
        masterHp.addActionListener(actionListener);
        masterMp.addActionListener(actionListener);
        petHp.addActionListener(actionListener);
        petMp.addActionListener(actionListener);
        handleDiolog.addActionListener(actionListener);
    }


    public void setCheckState(CheckStateVO checkState) {
        masterHp.setCheckState(checkState.isRenewHp());
        masterMp.setCheckState(checkState.isRenewMp());
        petHp.setCheckState(checkState.isRenewBbHp());
        petMp.setCheckState(checkState.isRenewBbMp());
        handleDiolog.setCheckState(checkState.isHandleDiolog());
    }


    public CheckStateVO getCheckStateVO() {
        CheckStateVO checkStateVO = new CheckStateVO();
        checkStateVO.setRenewHp(masterHp.isCheck());
        checkStateVO.setRenewMp(masterMp.isCheck());
        checkStateVO.setRenewBbHp(petHp.isCheck());
        checkStateVO.setRenewBbMp(petMp.isCheck());
        checkStateVO.setHandleDiolog(handleDiolog.isCheck());
        return checkStateVO;
    }


    public void setCheckButtonListen(CheckButtonListen checkButtonListen) {
        mCheckButtonListen = checkButtonListen;
    }

    public interface CheckButtonListen {
        void checkButtonStateChange(CheckStateVO checkStateVO);
    }
}
