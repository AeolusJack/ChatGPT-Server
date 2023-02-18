package com.erget.chatgpt.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 集合工具类
 **/
public class CollectionUtils {
    /**
     * 判断List是否含有null,同时删除null
     *
     * @param list
     * @return
     */
    public static List removeNull(List<Object> list){
        if(list.size()>0){
            List<Object> newList = new ArrayList<Object>();
            Iterator<Object> iterator = list.iterator();
            while(iterator.hasNext()){
                Object obj = iterator.next();
                if(obj != null){
                    newList.add(obj);
                }
            }
            return newList;
        }else{
            return list;
        }
    }
}
