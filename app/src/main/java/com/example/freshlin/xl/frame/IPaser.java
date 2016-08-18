package com.example.freshlin.xl.frame;

import java.io.InputStream;
import java.util.List;

/**
 * Created by freshlin on 2016/8/18.
 */
public interface IPaser<T> {

    /**
     * 解析
     * @param is
     * @return
     * @throws Exception
     */
    public List<T> parse(InputStream is) throws Exception;


    /**
     *
     * 序列化Beauty对象集合，得到XML形式的字符串
     * @param beauties
     * @return
     * @throws Exception
     */
    public String serialize(List<T> beauties) throws Exception;
}
