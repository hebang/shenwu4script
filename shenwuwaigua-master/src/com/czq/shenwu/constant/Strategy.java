package com.czq.shenwu.constant;

public enum Strategy {
    FENYAO("大唐封妖捉鬼"),
    FUBEN("大唐副本"),
    MIAOXIFENYAO("秒系封妖"),
    MIAOXIHUODONG("秒系活动");
    public final String name;
    Strategy(String name) {
        this.name = name;
    }


    public static final Strategy getStrategy(String strategyName) {
        Strategy[] strategies = Strategy.values();
        for (int i = 0; i < strategies.length; i++) {
            if (strategies[i].name.equals(strategyName))
                return strategies[i];
        }
        return null;
    }
}
