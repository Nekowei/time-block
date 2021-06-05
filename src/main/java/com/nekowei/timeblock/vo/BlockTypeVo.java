package com.nekowei.timeblock.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BlockTypeVo {
    private Integer id;
    private Integer parentId;
    private String name;
    private String color;
    private List<BlockTypeVo> detail;
}
