package com.imooc.miaosha.util; /**
 * Created with IntelliJ IDEA By fty on 2020/7/1
 */

import org.springframework.format.datetime.joda.DateTimeParser;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.joda.time.DateTime;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/7/1 20:35
 * @Version V1.0
 **/
public class RandomUtil {
    private static final SimpleDateFormat dateFormatOne=new SimpleDateFormat("yyyyMMddHHmmssSS");

    private static final ThreadLocalRandom random=ThreadLocalRandom.current();

    /**
     * 生成订单编号-方式一
     * @return
     */
    public static String generateOrderCode(){
        //TODO:时间戳+N为随机数流水号
        return dateFormatOne.format(DateTime.now().toDate()) + generateNumber(4);
    }

    //N为随机数流水号
    public static String generateNumber(final int num){
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=num;i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }


//    public static void main(String[] args) {
//        for (int i = 0; i < 100000; i++) {
//            System.out.println(generateOrderCode());
//        }
//    }
}
