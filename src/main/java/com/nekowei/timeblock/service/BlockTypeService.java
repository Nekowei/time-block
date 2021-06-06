package com.nekowei.timeblock.service;

import com.nekowei.timeblock.entity.BlockTypeEntity;
import com.nekowei.timeblock.repo.BlockTypeRepository;
import com.nekowei.timeblock.vo.BlockTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockTypeService {

    @Autowired
    private BlockTypeRepository blockTypeRepository;

    public void save(BlockTypeEntity e) {
        blockTypeRepository.save(e);
    }

    public List<BlockTypeVo> tree() {
        List<BlockTypeEntity> list = blockTypeRepository.findAll();
        return list.stream()
                .map(this::copy)
                .filter(e -> e.getParentId() == null)
                .peek(e -> e.setDetail(list.stream()
                        .filter(d -> d.getParentId() != null && d.getParentId().equals(e.getId()))
                        .map(this::copy)
                        .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    private BlockTypeVo copy(BlockTypeEntity e) {
        BlockTypeVo vo = new BlockTypeVo();
        BeanUtils.copyProperties(e, vo);
        return vo;
    }

    @Transactional
    public void add(BlockTypeEntity e) {
        if (e.getId() != null) {
            BlockTypeEntity old = blockTypeRepository.getById(e.getId());
            if (StringUtils.hasText(e.getColor())) {
                old.setColor(e.getColor());
            }
            if (StringUtils.hasText(e.getName())) {
                old.setName(e.getName());
            }
            if (e.getParentId() != null) {
                old.setParentId(e.getParentId());
            }
            blockTypeRepository.save(old);
        } else {
            blockTypeRepository.save(e);
        }
    }

    @Transactional
    public void delete(Integer id) {
        blockTypeRepository.findById(id).ifPresent(e -> {
            if (e.getParentId() == null) {
                blockTypeRepository.findAll(Example.of(BlockTypeEntity.builder()
                            .parentId(id).build()))
                        .forEach(d -> blockTypeRepository.deleteById(d.getId()));
            }
            blockTypeRepository.deleteById(id);
        });
    }

}
