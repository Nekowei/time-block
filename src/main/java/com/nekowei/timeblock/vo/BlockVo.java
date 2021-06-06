package com.nekowei.timeblock.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Data
public class BlockVo {
    private Integer id;
    private LocalDate recordDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer typeId;
    private String name;
    private String color;
    private Integer hour;
}
