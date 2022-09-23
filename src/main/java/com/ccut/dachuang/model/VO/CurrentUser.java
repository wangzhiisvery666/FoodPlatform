package com.ccut.dachuang.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class CurrentUser {

    @ApiModelProperty("当前用户username")
    private String username;
    @ApiModelProperty("当前用户avatar")

    private String avatar;
    @ApiModelProperty("当前用户sex")

    private String sex;
    @ApiModelProperty("当前用户phoneNumber")

    private String phoneNumber;
    @ApiModelProperty("当前用户defaultAddress")

    private String defaultAddress;
}
