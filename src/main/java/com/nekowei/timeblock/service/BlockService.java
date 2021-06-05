package com.nekowei.timeblock.service;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.repo.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Transactional
    public void save(BlockEntity s) {
        blockRepository.save(s);
    }

    @Transactional(readOnly = true)
    public List<BlockEntity> list(LocalDate date) {
        BlockEntity be = new BlockEntity();
        be.setRecordDate(date);
        return blockRepository.findAll(Example.of(be));
    }
}
