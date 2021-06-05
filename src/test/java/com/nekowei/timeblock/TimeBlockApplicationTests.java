package com.nekowei.timeblock;

import com.nekowei.timeblock.entity.BlockEntity;
import com.nekowei.timeblock.entity.BlockTypeEntity;
import com.nekowei.timeblock.service.BlockService;
import com.nekowei.timeblock.service.BlockTypeService;
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

	@Test
	void saveType() {
		BlockTypeEntity e = new BlockTypeEntity();
		e.setName("rua");
		e.setColor("#ffeedd");
		blockTypeService.save(e);
	}

	@Test
	void saveTypeDetail() {
		BlockTypeEntity e = new BlockTypeEntity();
		e.setName("mua");
		e.setColor("#ffeedd");
		e.setParentId(1);
		blockTypeService.save(e);
	}

	@Test
	void saveBlock() {
		BlockEntity e = new BlockEntity();
		e.setRecordDate(LocalDate.now());
		e.setStartTime(LocalTime.of(21, 0));
		e.setEndTime(LocalTime.of(21, 15));
		e.setTypeId(1);
		blockService.save(e);
	}

	@Test
	void saveBlockWithDetail() {
		BlockEntity e = new BlockEntity();
		e.setRecordDate(LocalDate.now());
		e.setStartTime(LocalTime.of(21, 15));
		e.setEndTime(LocalTime.of(21, 30));
		e.setTypeId(1);
		e.setDetailId(2);
		blockService.save(e);
	}

}
