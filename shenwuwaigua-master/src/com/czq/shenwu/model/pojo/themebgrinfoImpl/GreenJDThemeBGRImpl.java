package com.czq.shenwu.model.pojo.themebgrinfoImpl;

import com.czq.shenwu.model.pojo.IThemeBGRInfo;

public class GreenJDThemeBGRImpl implements IThemeBGRInfo {
    //绿色经典Hp为0处的BGR
    private final int HP_ZERO_POINT_BGR = -1813446;

    private final int CAI_DAN_BGR = -4480188;

    private final int BAN_HP_BGR = -1748166;

    //不稳定,也有可能等于-7879051
    private final int BATTLE_POINT_BGR = -13601697;

    private final int DIALOG_CENTER = -12661860;

    @Override
    public int getHpZeroBGR() {
        return HP_ZERO_POINT_BGR;
    }

    @Override
    public int getCaiDanBGR() {
        return CAI_DAN_BGR;
    }

    @Override
    public int getBanHpBGR() {
        return BAN_HP_BGR;
    }

    @Override
    public int getBattlePointBGR() {
        return BATTLE_POINT_BGR;
    }

    @Override
    public int getDialogCenter() {
        return DIALOG_CENTER;
    }
}
