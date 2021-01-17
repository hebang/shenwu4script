package com.czq.shenwu.model.base;

public class NullObject implements NullQuery{
    @Override
    public boolean isNull() {
        return true;
    }
}
