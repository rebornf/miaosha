package com.imooc.miaosha.domain; /**
 * Created with IntelliJ IDEA By fty on 2020/7/2
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/7/2 21:32
 * @Version V1.0
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class MiaoshaMessageUser implements Serializable {
    private Long id ;

    private Long userId ;

    private Long messageId ;

    private String goodId ;

    private Date orderId;
}
