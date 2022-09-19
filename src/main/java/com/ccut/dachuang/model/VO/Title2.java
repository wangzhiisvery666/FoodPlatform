package com.ccut.dachuang.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("二级标题")
public class Title2 {
    //子标题
    @ApiModelProperty("二级标题的子标题")
    List<Title3>   subheading;
    //Title2 name
    @ApiModelProperty("二级标题名")
    String  name;
}
