package com.imooc.miaosha.domain; /**
 * Created with IntelliJ IDEA By fty on 2020/7/2
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/7/2 21:25
 * @Version V1.0
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaMessageInfo implements Serializable {
    private Integer id ;

    private Long messageId ;

    private Long userId ;

    private String content ;

    private Date createTime;

    private Integer status ;

    private Date overTime ;

    private Integer messageType ;

    private Integer sendType ;

    private String goodName ;

    private BigDecimal price ;

    private String messageHead ;

}
