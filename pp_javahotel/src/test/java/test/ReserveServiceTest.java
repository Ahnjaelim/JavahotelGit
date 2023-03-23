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
import dto.ReserveDTO;
import dto.RoomDTO;
import service.ReserveService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReserveServiceTest {

	@Autowired
	private ReserveService reserveService;
	
	// @Test
	public void testRegister() {
		ReserveDTO reserveDTO = ReserveDTO.builder()
				.ro_id(2)
				.re_name("이보람")
				.re_password("1111")
				.re_phone("01022223333")
				.re_startday(LocalDate.of(2023, 01, 04))
				.re_endday(LocalDate.of(2023, 01, 05))
				.re_capa(2)
				.re_price(200000)
				.re_payment(0)
				.re_datetime(new Timestamp(System.currentTimeMillis()))
				.build();
		reserveService.register(reserveDTO);
	}
	
	// @Test
	public void getByRoomIdTest() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ro_id", "1");
		map.put("firstday", "2023-01-01");
		map.put("lastday", "2023-01-30");
		HashMap<String, ReserveDTO> maplist = reserveService.getByRoomId(map);
		for(String i : maplist.keySet()){ //저장된 key값 확인
		    System.out.println("[Key]:" + i + " [Value]:" + maplist.get(i));
		}
	}
	@Test
	public void getByRoomIdTodayTest() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("ro_id", 1);
		map.put("today", "2023-01-10");
		ReserveDTO reserveDTO = reserveService.getByRoomIdToday(map);
		System.out.println(reserveDTO);
	}
	
	
	// @Test
	public void getOneRoomTest() {
		RoomDTO roomDTO = reserveService.getOneRoom("1");
		System.out.println(roomDTO);
	}
	
	// @Test
	public void getByUserTest() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("re_name", "안재림");
		map.put("re_phone", "01012345678");
		map.put("re_password", "0000");
		List<ReserveDTO> dtoList = reserveService.getByUser(map);
		dtoList.forEach(dto -> System.out.println(dto));			
	}
	
	// @Test
	public void registerRoomTest() {
		RoomDTO roomDTO = RoomDTO.builder()
				.ro_id(7)
				.ro_name("107호")
				.ro_type("일반")
				.ro_capa(2)
				.ro_price(150000)
				.ro_state(0)
				.ro_datetime(new Timestamp(System.currentTimeMillis()))
				.build();
		reserveService.registerRoom(roomDTO);			
	}

	
}
