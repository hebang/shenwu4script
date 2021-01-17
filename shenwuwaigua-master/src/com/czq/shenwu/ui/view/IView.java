package com.czq.shenwu.ui.view;

import com.czq.shenwu.controller.IController;
import com.czq.shenwu.model.vo.CheckStateVO;


public interface IView {

    boolean isStart();

    void setCheckButtonState(CheckStateVO checkButtonState);

    void bindConstant(IController iController);

    void unBindConstant();


}
