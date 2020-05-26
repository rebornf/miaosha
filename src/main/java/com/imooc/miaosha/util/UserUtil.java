package com.imooc.miaosha.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.miaosha.domain.MiaoshaUser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/5/18 14:12
 * @Version V1.0
 **/
public class UserUtil {

    private static void creatUser(int count) throws Exception {
        List<MiaoshaUser> users =new ArrayList<>(count);
        //生成用户
        for (int i=0;i<count;i++){
            MiaoshaUser user = new MiaoshaUser();
            user.setId(13000000000L+i);
            user.setLoginCount(1);
            user.setNickname("user"+i);
            user.setRegisterDate(new Date());
            user.setSalt("1a2b3c");
            user.setPassword(MD5Util.inputPassToDbPass("123456",user.getSalt()));
            users.add(user);
        }
        System.out.println("create user");




        //登录，生成token
        String urlString ="http://localhost:8080/login/do_login";
        File file =new File("D:/tokens.txt");
        if (file.exists()){
            file.delete();
        }
        RandomAccessFile raf =new RandomAccessFile(file,"rw");
        file.createNewFile();
        raf.seek(0);
        for (int i=0;i<users.size();i++){
            MiaoshaUser user =users.get(i);
            URL url =new URL(urlString);
            HttpURLConnection co =(HttpURLConnection)url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFormPass("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jo = JSON.parseObject(response);
            String token = jo.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId()+","+token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }

    public static void main(String[] args) throws Exception {
        creatUser(5000);

    }
}
