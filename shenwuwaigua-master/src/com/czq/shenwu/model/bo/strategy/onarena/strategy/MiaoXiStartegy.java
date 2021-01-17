package com.czq.shenwu.model.bo.strategy.onarena.strategy;

import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.MouseOperation;
import com.czq.shenwu.model.bo.strategy.OnArenaStrategyImpl;

import java.awt.image.BufferedImage;

public class MiaoXiStartegy extends OnArenaStrategyImpl {
    @Override
    protected Point confirmTarget(BufferedImage bi) {
        return null;
    }

    @Override
    public void initStrategyCheckStateVO() {
        mCheckStateVO.setHandleDiolog(true);
    }

    @Override
    public void ready(BufferedImage bi) {

    }

    @Override
    public void end() {
        MouseOperation.keyAltAndQ();
        MouseOperation.keyAltAndQ();
    }
}
