package com.example.freshlin.xl.frame.utils;

import android.util.Xml;

import com.example.freshlin.xl.example.bean.Act;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 想写成泛型
 * Created by freshlin on 2016/8/18.
 */
public class ParseUtils{

    public static List<Act> parse(InputStream is) throws Exception {
        List<Act> mList = null;
        Act act = null;

        // 由android.util.Xml创建一个XmlPullParser实例
        XmlPullParser xpp = Xml.newPullParser();
        // 设置输入流 并指明编码方式
        xpp.setInput(is,"UTF-8");
        // 产生第一个事件
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType) {
                // 判断当前事件是否为文档开始事件
                case XmlPullParser.START_DOCUMENT:
                    mList = new ArrayList<Act>(); // 初始化books集合
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("act")) { // 判断开始标签元素是否是book
                        act = new Act();
                    } else if (xpp.getName().equals("name")) {
                        eventType = xpp.next();//让解析器指向name属性的值
                        // 得到name标签的属性值，并设置beauty的name
                        act.setName(xpp.getText());
                    } else if (xpp.getName().equals("info")) { // 判断开始标签元素是否是book
                        eventType = xpp.next();//让解析器指向age属性的值
                        // 得到age标签的属性值，并设置beauty的age
                        act.setInfo(xpp.getText());
                    }
                    break;
                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("act")) { // 判断结束标签元素是否是book
                        mList.add(act); // 将book添加到books集合
                        act = null;
                    }
                    break;
            }
            // 进入下一个元素并触发相应事件
            eventType = xpp.next();
        }
        return mList;

    }


    public String serialize(List<Act> act) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
