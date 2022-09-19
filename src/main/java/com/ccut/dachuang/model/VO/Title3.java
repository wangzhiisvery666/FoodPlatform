package com.ccut.dachuang.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("三级标题")
public class Title3 implements Serializable {
    @ApiModelProperty("三级标题名")
    String name;
}
