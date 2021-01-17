package com.czq.shenwu.model.vo;

public class CheckStateVO {
    private boolean  renewHp = false;
    private boolean renewMp = false;
    private boolean renewBbHp = false;
    private boolean renewBbMp = false;
    private boolean handleDiolog = false;

    public boolean isRenewHp() {
        return renewHp;
    }

    public void setRenewHp(boolean renewHp) {
        this.renewHp = renewHp;
    }

    public boolean isRenewMp() {
        return renewMp;
    }

    public void setRenewMp(boolean renewMp) {
        this.renewMp = renewMp;
    }

    public boolean isRenewBbHp() {
        return renewBbHp;
    }

    public void setRenewBbHp(boolean renewBbHp) {
        this.renewBbHp = renewBbHp;
    }

    public boolean isRenewBbMp() {
        return renewBbMp;
    }

    public void setRenewBbMp(boolean renewBbMp) {
        this.renewBbMp = renewBbMp;
    }

    public boolean isHandleDiolog() {
        return handleDiolog;
    }

    public void setHandleDiolog(boolean handleDiolog) {
        this.handleDiolog = handleDiolog;
    }

    @Override
    public String toString() {
        return "CheckStateVO{" +
                "renewHp=" + renewHp +
                ", renewMp=" + renewMp +
                ", renewBbHp=" + renewBbHp +
                ", renewBbMp=" + renewBbMp +
                ", handleDiolog=" + handleDiolog +
                '}';
    }
}
