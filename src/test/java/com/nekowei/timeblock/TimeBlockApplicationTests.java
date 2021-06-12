package com.nekowei.timeblock;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.entity.BlockTypeEntity;
import com.nekowei.timeblock.service.BlockService;
import com.nekowei.timeblock.service.BlockTypeService;
import com.nekowei.timeblock.vo.BlockVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
class TimeBlockApplicationTests {

	@Autowired
	private BlockService blockService;
	@Autowired
	private BlockTypeService blockTypeService;

	private final String username = "nekowei";

	@Test
	void saveType() {
		BlockTypeEntity e = new BlockTypeEntity();
		e.setName("rua");
		e.setColor("#ffeedd");
		e.setUsername(username);
		e = blockTypeService.save(e);
		blockTypeService.delete(e.getId());
	}

	@Test
	void saveTypeDetail() {
		BlockTypeEntity e = new BlockTypeEntity();
		e.setName("mua");
		e.setColor("#ffeedd");
		e.setParentId(1);
		e.setUsername(username);
		e = blockTypeService.save(e);
		blockTypeService.delete(e.getId());
	}

	@Test
	void saveBlock() {
		BlockEntity e = new BlockEntity();
		e.setRecordDate(LocalDate.now());
		e.setStartTime(LocalTime.of(21, 0));
		e.setEndTime(LocalTime.of(21, 15));
		e.setTypeId(1);
		e.setUsername(username);
		e = blockService.save(e);
		blockService.delete(e);
	}

	@Test
	void saveAllBlock() {
		BlockVo e = new BlockVo();
		e.setRecordDate(LocalDate.now());
		e.setHour(23);
		e.setTypeId(1);
		blockService.saveAll(e, username)
				.forEach(be -> blockService.delete(be));
	}

}
