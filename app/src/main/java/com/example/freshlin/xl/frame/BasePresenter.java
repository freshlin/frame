package com.example.freshlin.xl.frame;

/**
 * Created by xl on 2016/8/4.
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
