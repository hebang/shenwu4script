package com.czq.shenwu.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
    public int helloWorld()throws RemoteException;
}
