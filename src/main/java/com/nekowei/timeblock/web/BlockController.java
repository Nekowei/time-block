package com.nekowei.timeblock.web;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.service.BlockService;
import com.nekowei.timeblock.vo.BlockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @GetMapping("date")
    public ResponseEntity<String> date(@RequestParam(value = "day", required = false)Integer day) {
        return new ResponseEntity<>(getDate(day).toString(), HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Map<Integer, List<BlockVo>>> list(@RequestParam(value = "day", required = false)Integer day) {
        return new ResponseEntity<>(blockService.list(getDate(day)), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<Void> save(@ModelAttribute BlockEntity e) {
        blockService.save(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("save/all")
    public ResponseEntity<Void> saveAll(@ModelAttribute BlockVo e) {
        blockService.saveAll(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private LocalDate getDate(Integer day) {
        if (day == null) {
            day = 0;
        }
        return LocalDate.now().plusDays(day);
    }

}
