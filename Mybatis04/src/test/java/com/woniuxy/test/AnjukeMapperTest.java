package com.woniuxy.test;

import com.woniuxy.entity.Anjuke;
import com.woniuxy.mapper.AnjukeMapper;
import com.woniuxy.utils.DBUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: rua
 * @Date: 2021/7/27 19:17
 * @Description:
 */
public class AnjukeMapperTest {

	@Test
	public void findLimit() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		List<Anjuke> list = mapper.findLimit("北", 3, 3);
		for (Anjuke anjuke : list) {
			System.out.println(anjuke);
		}


	}

	@Test
	public void findByCondition() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		Anjuke anjuke = new Anjuke();
//		anjuke.setId(31);
		anjuke.setTrainLine("2号线");
		List<Anjuke> list = mapper.findByCondition(anjuke);
		for (Anjuke a : list) {
			System.out.println(a);
		}

	}


	@Test
	public void findLikeName() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		List<Anjuke> list = mapper.findLikeName("浦东");
		for (Anjuke anjuke : list) {
			System.out.println(anjuke);
		}

	}


	@Test
	public void findAllAnjuke() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		Map<Integer, Anjuke> map = mapper.findAllAnjuke();
		for (Map.Entry<Integer, Anjuke> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}
	}

	@Test
	public void findAllMap() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		List<Map<String, Object>> list = mapper.findAllMap();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	@Test
	public void findAll() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		List<Anjuke> list = mapper.findAll();
		for (Anjuke anjuke : list) {
			System.out.println(anjuke);
		}
	}

	@Test
	public void findById() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		Anjuke anjuke = mapper.findById(20);
		System.out.println(anjuke);
	}

	@Test
	public void update() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		Anjuke anjuke = new Anjuke();
		anjuke.setId(31);
		anjuke.setTrainLine("2号线");
		int row = mapper.update(anjuke);
		System.out.println(row);


	}

	@Test
	public void deleteByIds() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		int i = mapper.deleteByIds(30, 32);
		System.out.println(i);

	}

	@Test
	public void addList() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		ArrayList<Anjuke> list = new ArrayList<>();
		list.add(new Anjuke("浦东新区", new BigDecimal("5000")));
		list.add(new Anjuke("徐汇区", new BigDecimal("6000")));
		list.add(new Anjuke("静安区", new BigDecimal("4000")));

		int row = mapper.addList(list);
		System.out.println(row);


	}

	@Test
	public void add() {
		AnjukeMapper mapper = DBUtil.getMapper(AnjukeMapper.class);
		Anjuke anjuke = new Anjuke();
		anjuke.setDirection("朝南");
		int row = mapper.add(anjuke);
		System.out.println(row > 0 ? "成功" : "失败");
	}
}
