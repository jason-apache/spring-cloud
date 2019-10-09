package com.jason.utils;



import com.jason.statusEnum.StatusEnum;

import java.util.HashMap;
import java.util.Map;

public class ResultMapUtil {

    public static boolean isMatching(Map<String ,Object> map, StatusEnum statusEnum){
        if(null == map || map.size()<=0){
            return false;
        }
        if(null != map.get(statusEnum.getCodeName()) && statusEnum.getCode() == map.get(statusEnum.getCodeName())){
            return true;
        }
        return false;
    }

    public static Map<String ,Object> putStatus(Map<String ,Object> map,StatusEnum statusEnum){
        if(null == map || null == statusEnum){
            return putStatus(new HashMap<String, Object>(),StatusEnum.ERROR);
        }
        if(null != statusEnum.getCodeName() && null != statusEnum.getCode()){
            map.put(statusEnum.getCodeName(),statusEnum.getCode());
        }else{
            return putStatus(new HashMap<String, Object>(),StatusEnum.ERROR);
        }
        return map;
    }

    public static Map<String ,Object> putResult(Map<String ,Object> map,Object... objs){
        if(null == map || null == objs || objs.length<=0){
            return putStatus(map,StatusEnum.ERROR);
        }
        map.put(StatusEnum.RESULT.getCodeName(),objs);
        return map;
    }
}
