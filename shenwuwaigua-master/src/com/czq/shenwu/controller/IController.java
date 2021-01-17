package com.czq.shenwu.controller;

import com.czq.shenwu.model.vo.CheckStateVO;
import com.czq.shenwu.ui.view.IView;

public interface IController {

    void bindView(IView view);

    void ready();
    void onBattle();

    void unBindView();

    void startegyChange(String startegyName);

    void themeChange(String themeName);

    void checkButtonStateChange(CheckStateVO checkStateVO);
}
