package com.nekowei.timeblock.web;

import com.nekowei.timeblock.entity.BlockTypeEntity;
import com.nekowei.timeblock.service.BlockTypeService;
import com.nekowei.timeblock.util.UserUtil;
import com.nekowei.timeblock.vo.BlockTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
    public ResponseEntity<String> add(@ModelAttribute BlockTypeEntity e) {
        if (!StringUtils.hasText(e.getName())) {
            return new ResponseEntity<>("name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        e.setUsername(UserUtil.getUsername());
        blockTypeService.add(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id) {
        blockTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
