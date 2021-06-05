package com.nekowei.timeblock.web;

import com.nekowei.timeblock.entity.BlockTypeEntity;
import com.nekowei.timeblock.service.BlockTypeService;
import com.nekowei.timeblock.vo.BlockTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
public class BlockTypeController {

    @Autowired
    private BlockTypeService blockTypeService;

    @GetMapping("tree")
    public ResponseEntity<List<BlockTypeVo>> tree() {
        List<BlockTypeVo> list = blockTypeService.tree();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Void> add(@ModelAttribute BlockTypeEntity e) {
        blockTypeService.add(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
