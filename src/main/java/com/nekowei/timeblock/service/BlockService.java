package com.nekowei.timeblock.service;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.repo.BlockRepository;
import com.nekowei.timeblock.repo.BlockTypeRepository;
import com.nekowei.timeblock.vo.BlockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private BlockTypeRepository blockTypeRepository;

    @Transactional
    public void save(BlockEntity s) {
        blockRepository.save(s);
    }

    @Transactional(readOnly = true)
    public Map<Integer, List<BlockVo>> list(LocalDate date) {
        List<BlockEntity> list = blockRepository.findAll(Example.of(BlockEntity.builder()
                .recordDate(date).build()));
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                BlockEntity e = new BlockEntity();
                e.setRecordDate(date);
                e.setStartTime(LocalTime.of(i, j * 15));
                e.setEndTime(LocalTime.of(i, j * 15).plusMinutes(15));
                if (!list.stream()
                    .filter(be -> be.getStartTime().equals(e.getStartTime()))
                    .findAny().isPresent()) {
                    list.add(e);
                }
            }
        }
        return list.stream()
                .map(this::copy)
                .peek(vo -> {
                    if (vo.getTypeId() != null) {
                        blockTypeRepository.findById(vo.getTypeId()).ifPresent(t -> {
                            vo.setName(t.getName());
                            vo.setColor(t.getColor());
                        });
                    }
                })
                .sorted(Comparator.comparing(BlockVo::getStartTime))
                .collect(Collectors.groupingBy(e -> e.getStartTime().getHour()));
    }

    private BlockVo copy(BlockEntity e) {
        BlockVo vo = new BlockVo();
        BeanUtils.copyProperties(e, vo);
        return vo;
    }

    @Transactional
    public void saveAll(BlockVo e) {
        List<BlockEntity> list = IntStream.range(0, 4).mapToObj(i -> {
            BlockEntity be = new BlockEntity();
            be.setRecordDate(e.getRecordDate());
            be.setTypeId(e.getTypeId());
            be.setStartTime(LocalTime.of(e.getHour(), i * 15));
            be.setEndTime(LocalTime.of(e.getHour(), i * 15).plusMinutes(15));
            return be;
        }).collect(Collectors.toList());
        blockRepository.saveAll(list);
    }

}
