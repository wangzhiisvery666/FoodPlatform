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
@ApiModel("一级标题")
public class Title1 {
     @ApiModelProperty("一级标题的子标题")
     List<Title2> subheading;

     @ApiModelProperty("二级标题名")
     String  name;
}
