package com.nekowei.timeblock.repo;

import com.nekowei.timeblock.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<BlockEntity, Integer> {
}
