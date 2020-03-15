package com.mybaitisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author psy
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TestPlus对象", description="")
public class TestPlus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测试表的主键ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户的姓名")
    private String username;

    @ApiModelProperty(value = "用户的密码")
    private String password;

    @ApiModelProperty(value = "用户的邮箱账号")
    private String email;

    @ApiModelProperty(value = "邮箱的密码")
    private String emailPassword;

    @ApiModelProperty(value = "创建的时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户的年龄")
    private Integer age;


}
