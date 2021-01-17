package com.czq.shenwu.model.pojo;

public interface IThemeBGRInfo {

    String[] THEME_TYPES = {"蓝色古韵","绿色金典"};

    int NPC_BGR = -6797312;
    //旧的NPC BGR-989008;

    int DIE_NPC_BGR = -1842205;

    int SAN_JIAO_XING = -3810079;

    int getHpZeroBGR();

    int getCaiDanBGR();

    int getBanHpBGR();

    int getBattlePointBGR();

    int getDialogCenter();
}
