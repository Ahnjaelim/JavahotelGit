package test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import domain.ReserveVO;
import domain.RoomVO;
import mapper.ReserveMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReserveMapperTest {

	@Autowired
	private ReserveMapper reserveMapper;
	
	@Test
	public void test1() {
		System.out.println(reserveMapper.getTime());
	}
	
	//@Test
	public void test2() {
		List<ReserveVO> voList = reserveMapper.selectAll();
		voList.forEach(vo -> System.out.println(vo));
	}
	
	//@Test
	public void insertTest() {
		ReserveVO reserveVO = ReserveVO.builder()
				.ro_id(1)
				.re_name("김민지")
				.re_password("1111")
				.re_phone("01011112222")
				.re_startday(LocalDate.of(2023, 01, 04))
				.re_endday(LocalDate.of(2023, 01, 05))
				.re_capa(2)
				.re_price(200000)
				.re_payment(0)
				.re_datetime(new Timestamp(System.currentTimeMillis()))
				.build();
		reserveMapper.insert(reserveVO);

	}

	// @Test
	public void selectByRoomId() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ro_id", "1");
		map.put("firstday", "2023-01-01");
		map.put("lastday", "2023-01-30");
		List<ReserveVO> voList = reserveMapper.selectByRoomId(map);
		voList.forEach(vo -> System.out.println(vo));	
	}
	
	@Test
	public void selectByRoomIdToday() {
		HashMap<String, Object> infomap = new HashMap<>();
		infomap.put("ro_id", 1);
		infomap.put("today", "2023-01-10");
		ReserveVO voList = reserveMapper.selectByRoomIdToday(infomap);
		System.out.println(voList);
	}
	
	// @Test
	public void selectRoolListTest() {
		List<RoomVO> roomList = reserveMapper.selectRoomList();
		roomList.forEach(room -> System.out.println(room));
	}
	
	// @Test
	public void selectOneRoomTest() {
		RoomVO roomVO = reserveMapper.selectOneRoom("1");
		System.out.println(roomVO);
	}
	
	// @Test
	public void selectOneTest() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("re_name", "안재림");
		map.put("re_phone", "01012345678");
		map.put("re_password", "0000");
		List<ReserveVO> voList = reserveMapper.selectByUser(map);
		voList.forEach(vo -> System.out.println(vo));		
	}
	
	// @Test
	public void insertRoomTest() {
		RoomVO roomVO = RoomVO.builder()
				.ro_id(6)
				.ro_name("106호")
				.ro_type("일반")
				.ro_capa(2)
				.ro_price(150000)
				.ro_state(0)
				.ro_datetime(new Timestamp(System.currentTimeMillis()))
				.build();
		reserveMapper.insertRoom(roomVO);
	}
	// @Test
	public void updateRoomTest() {
		RoomVO roomVO = RoomVO.builder()
				.ro_id(1)
				.ro_name("101호 수정")
				.ro_type("일반")
				.ro_capa(2)
				.ro_price(150000)
				.ro_state(0)
				.ro_datetime(new Timestamp(System.currentTimeMillis()))
				.build();
		reserveMapper.updateRooom(roomVO);
	}	
	
}
