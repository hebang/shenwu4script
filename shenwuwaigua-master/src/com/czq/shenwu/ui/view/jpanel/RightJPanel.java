package com.czq.shenwu.ui.view.jpanel;

import com.czq.shenwu.constant.Strategy;
import com.czq.shenwu.model.pojo.IThemeBGRInfo;
import com.czq.shenwu.ui.view.jpanel.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightJPanel extends BasePanel  {

    private JComboBox<String> selectStrategy;
    private String selectItem;  //当前ui选择的策略类型

    private JComboBox<String> selectTheme;
    private SelectStartegyListen mSelectStartegyListen;
    private SelectThemeListen mSelectThemeListen;

    public RightJPanel() {
        selectStrategy = new JComboBox<>();
        selectStrategy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    selectItem = (String) e.getItem();
                    if (mSelectStartegyListen != null)
                        mSelectStartegyListen.startegyChange(selectItem);
                }
            }
        });

        selectTheme = new JComboBox<>();
        selectTheme.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (mSelectThemeListen != null)
                        mSelectThemeListen.themeChanage((String) e.getItem());
                }
            }
        });
        this.setLayout(new GridLayout(2,1,3,3));
        this.add(selectStrategy);
        this.add(selectTheme);
        initSelectType();
    }

    /**
     * 初始化策略和主题
     */
    public void initSelectType() {
        Strategy[] strategies = Strategy.values();
        for (int i = 0; i < strategies.length; i++) {
            selectStrategy.addItem(strategies[i].name);
        }
        String[] themes = IThemeBGRInfo.THEME_TYPES;
        for (int i = 0; i < themes.length; i++) {
            selectTheme.addItem(themes[i]);
        }
    }

    public String getSelectItem() {
        return selectItem;
    }

    public void setSelectStartegyListen(SelectStartegyListen mSelectStartegyListen) {
        this.mSelectStartegyListen = mSelectStartegyListen;
    }

    public void setSelectThemeListen(SelectThemeListen selectThemeListen) {
        mSelectThemeListen = selectThemeListen;
    }


    public interface SelectStartegyListen {
        void startegyChange(String startegyName);
    }

    public interface SelectThemeListen {
        void themeChanage(String themeName);
    }
}
