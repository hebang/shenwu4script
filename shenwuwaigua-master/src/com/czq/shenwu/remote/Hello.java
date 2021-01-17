package com.czq.shenwu.remote;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements IHello{
    protected Hello() throws RemoteException {
    }



    @Override
    public int helloWorld() throws RemoteException {
        return 0;
    }
}
