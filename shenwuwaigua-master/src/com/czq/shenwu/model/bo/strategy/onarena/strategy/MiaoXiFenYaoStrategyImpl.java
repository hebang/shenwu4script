package com.czq.shenwu.model.bo.strategy.onarena.strategy;

import com.czq.shenwu.constant.SizeConstant;
import com.czq.shenwu.model.Point;
import com.czq.shenwu.model.bo.BGRCollection;
import com.czq.shenwu.model.bo.MouseOperation;
import com.czq.shenwu.model.bo.PointCollection;
import com.czq.shenwu.model.bo.strategy.OnArenaStrategyImpl;
import com.czq.shenwu.utils.LogUtils;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MiaoXiFenYaoStrategyImpl extends OnArenaStrategyImpl {
    private  static final String TAG = "MiaoXiFenYaoStrategyImpl";
    @Override
    protected Point confirmTarget(BufferedImage bi) {
        Point target = null;
        LogUtils.d(TAG,"npcBGR:" + BGRCollection.getInstance().getPointBGR(bi, PointCollection.NpcName.UNDER_FOURTH.getPoint()));
        if (npcIsAlive(bi,PointCollection.NpcName.UNDER_FIFTH)) {
            target = PointCollection.NpcName.UNDER_FIFTH.getPoint();
            mouseSelect = KeyEvent.BUTTON1_MASK;
        }
        return target;
    }

    @Override
    public void initStrategyCheckStateVO() {

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
