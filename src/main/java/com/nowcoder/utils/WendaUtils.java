package com.nowcoder.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WendaUtils {
    private static final Logger logger=LoggerFactory.getLogger(WendaUtils.class);

    public static String md5(String key){
        char[] hexDigits={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput=key.getBytes();
        try {
            MessageDigest mdInst=MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md=mdInst.digest();
            int j=md.length;
            char[] str=new char[2*j];
            int k=0;
            for(int i=0;i<j;i++){
                  byte byte0=md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            logger.error("生成md5失败",e);
            return null;
        }

    }
    public static String getJSONString(int code){
          JSONObject jsonObject=new JSONObject();
          jsonObject.put("code",code);
          return jsonObject.toJSONString();
    }
    public static String getJSONString(int code,String msg){
          JSONObject jsonObject=new JSONObject();
          jsonObject.put("code",code);
          jsonObject.put("msg",msg);
          return jsonObject.toJSONString();
    }
}
