package com.erget.chatgpt.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @program: ams5.0
 * @description: 字典序排序工具
 * @author: pengJQ
 * @create: 2021-01-28 16:05
 **/
public class IdeaSortDictUtils {

    private IdeaSortDictUtils() {
        //构造私有
    }

    public static IdeaSortDictUtils getInstance(){
       return new IdeaSortDictUtils();
    }

    public void ideaSortToDict(List<String> list) throws Exception {
        if (list == null){
            throw new Exception("字典序排序集合对象不能为空！");
        }
        Collections.sort(list, new Spell2Comparator());

    }


    /**
     * 汉字拼音排序比较器
     */
    class SpellComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // 取得比较对象的汉字编码，并将其转换成字符串
                String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                // 运用String类的 compareTo（）方法对两对象进行比较
                return s1.compareTo(s2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    /**
     * 数字或英文字符串排序比较器
     */
    class Spell2Comparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // 取得比较对象，并将其转换成字符串
                String s1 = o1.toString();
                String s2 = o2.toString();
                // 运用String类的 compareTo（）方法对两对象进行比较
                return s1.compareTo(s2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

    }
}