package com.example.freshlin.frame.frame;

/**
 * Created by freshlin on 2016/8/4.
 */
public abstract class BasePresenter <T>{
    public T view;

    public void attach(T view){
        this.view = view;
    }

    public void dettach(){
        this.view = null;
    }
}
