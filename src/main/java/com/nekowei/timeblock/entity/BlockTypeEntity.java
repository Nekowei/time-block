package com.nekowei.timeblock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "t_block_type")
public class BlockTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer parentId;

    @Column(nullable = false)
    private String name;

    @Column
    private String color;

    @Column(nullable = false)
    private String username;

}
