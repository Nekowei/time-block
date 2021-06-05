package com.nekowei.timeblock.web;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @GetMapping("list")
    public ResponseEntity<List<BlockEntity>> list(@RequestParam("date") LocalDate date) {
        List<BlockEntity> list = blockService.list(date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Void> save(@RequestBody BlockEntity e) {
        blockService.save(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
