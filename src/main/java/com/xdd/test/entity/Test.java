package com.xdd.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("id_user")
    private String idUser;
    @TableField("name_user")
    private String nameUser;
    @TableField("gender_user")
    private Integer genderUser;


}
