package com.czq.shenwu.model.bo.strategy;

import com.czq.shenwu.model.vo.CheckStateVO;

import java.awt.image.BufferedImage;

public interface IStrategy {

    /**
     * 准备
     */
    void ready(BufferedImage bi);
    void execute(BufferedImage bi);
    void end();

    /**
     * 获得指定策略的CheckStateVO状态
     * @return
     */
    CheckStateVO getStrategyCheckState();
}
