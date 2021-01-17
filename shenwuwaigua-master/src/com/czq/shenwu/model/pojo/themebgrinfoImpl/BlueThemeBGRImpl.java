package com.czq.shenwu.model.pojo.themebgrinfoImpl;

import com.czq.shenwu.model.pojo.IThemeBGRInfo;

public class BlueThemeBGRImpl implements IThemeBGRInfo {
    //Hp为0处的BGR
    private  final int HP_ZERO_POINT_BGR = -1361342;

    private  final int CAI_DAN_BGR = -16564657;

    private final int BAN_HP_BGR = -1289903;

    private final int BATTLE_POINT_BGR = -15842748;

    private final int DIALOG_CENTER = -7283000;

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
