package com.nekowei.timeblock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class BlockTypeEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer parentId;
    private String name;
    private String color;

}
