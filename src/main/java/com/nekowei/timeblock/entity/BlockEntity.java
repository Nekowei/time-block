package com.nekowei.timeblock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "t_block_entity")
public class BlockEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate recordDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer typeId;
    private Integer detailId;

}
