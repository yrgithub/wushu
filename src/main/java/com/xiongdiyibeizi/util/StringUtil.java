package com.xiongdiyibeizi.util;

import com.xiongdiyibeizi.common.Contains;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

public class StringUtil {

    public final static StringUtil INST = new StringUtil();

    private StringUtil()
    {

    }

    //生成32位随机数
    public String getGeneralKey()
    {
        String key = UUID.randomUUID().toString();
        return key;
    }


    //生成16位唯一性的会员号
    public String getUUID(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }

    //密码加密
    public String getPassword(int encryptionType,String password,String username)
    {
        SimpleHash simpleHash;
        switch (encryptionType){
                case Contains.MD5:
                    simpleHash = new SimpleHash("md5",password, ByteSource.Util.bytes(username),1024);
                    return simpleHash.toString();
                case Contains.SHA1:
                    simpleHash = new SimpleHash("sha1",password, ByteSource.Util.bytes(username),1024);
                    return simpleHash.toString();
                default:
                    simpleHash = new SimpleHash("md5",password, ByteSource.Util.bytes(username),1024);
                    return simpleHash.toString();

        }
    }

    public String decodeStrUrl(String value)
    {
        String result = null;
        if (value == null || value.length()<=0){
            return "";
        }
        try {
            result = URLDecoder.decode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
