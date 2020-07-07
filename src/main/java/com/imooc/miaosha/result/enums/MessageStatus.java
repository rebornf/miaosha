package com.imooc.miaosha.result.enums; /**
 * Created with IntelliJ IDEA By fty on 2020/7/2
 */

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/7/2 22:00
 * @Version V1.0
 **/
public class MessageStatus {

    public static  final Integer ZORE = 0;
    /**
     * 消息类型
     */
    public  enum  messageType {
        maiosha_message("秒杀消息"),
        buy_message("购买消息"),
        system_message("系统消息");
        private String message;

        private messageType(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 消息内容
     */
    public  enum  ContentEnum {
        system_message_register(7000,"尊敬的用户你好，你已经成功注册！"),

        system_message_register_head(7001,"注册成功");

        private int code;
        private String message;

        private ContentEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
    /**
     * 消息类型
     */
    public  enum  sendType {
//        maiosha_message("秒杀消息"),
//        buy_message("购买消息"),
//        system_message("系统消息");
//        private String message;
//
//        private messageType(String message){
//            this.message = message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
    }
}
