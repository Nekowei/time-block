package com.nekowei.timeblock.repo;

import com.nekowei.timeblock.entity.BlockTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockTypeRepository extends JpaRepository<BlockTypeEntity, Integer> {
}
