package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import domain.ReserveVO;
import domain.RoomVO;
import dto.ReserveDTO;
import dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import mapper.ReserveMapper;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

	private final ReserveMapper reserveMapper;
	private final ModelMapper modelMapper;
	
	@Override
	public List<ReserveDTO> getAll() {
		List<ReserveDTO> dtoList = reserveMapper.selectAll().stream()
				.map(vo -> modelMapper.map(vo, ReserveDTO.class))
				.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void register(ReserveDTO reserveDTO) {
		System.out.println(modelMapper);
		ReserveVO reserveVO = modelMapper.map(reserveDTO, ReserveVO.class);
		System.out.println(reserveVO);
		reserveMapper.insert(reserveVO);
	}

	@Override
	public HashMap<String, ReserveDTO> getByRoomId (HashMap<String, Object> map){
		// 매퍼에서 VO로 받은 자료 => DTO로 변환
		List<ReserveDTO> dtoList = reserveMapper.selectByRoomId(map).stream()
				.map(vo -> modelMapper.map(vo, ReserveDTO.class))
				.collect(Collectors.toList());		
		
		// DTO를 키를 날짜(re_startday)로 잡고 해시맵에 넣기
		HashMap<String, ReserveDTO> resultmap = new HashMap<>();
		for(int i=0; i<dtoList.size(); i++) {
			resultmap.put(String.valueOf(dtoList.get(i).getRe_startday()), dtoList.get(i));
			
			// 숙박일 계산
			Date startday = java.sql.Date.valueOf(dtoList.get(i).getRe_startday());
			Date endday = java.sql.Date.valueOf(dtoList.get(i).getRe_endday());
			long Sec = (endday.getTime() - startday.getTime()) / 1000; // 초
			long Min = (endday.getTime() - startday.getTime()) / 60000; // 분
			long Hour = (endday.getTime() - startday.getTime()) / 3600000; // 시
			long Days = Sec / (24*60*60); // 일자수
			System.out.println(Days + "일 차이");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if(Days>1) {
				LocalDate startdayLocal = dtoList.get(i).getRe_startday();
				LocalDate enddayLocal = dtoList.get(i).getRe_endday();
				for(int j=startdayLocal.getDayOfMonth()+1; j<enddayLocal.getDayOfMonth(); j++) {
					String keydate = String.valueOf(startdayLocal.getYear()+"-"+String.format("%02d",startdayLocal.getMonthValue())+"-"+String.format("%02d", j));
					System.out.println(keydate);
					resultmap.put(keydate, dtoList.get(i));					
				}
				System.out.println("더미데이터");
			}
			System.out.println("==============================");
		}
		return resultmap;
	}

	@Override
	public List<RoomDTO> getRoomList() {
		List<RoomDTO> roomDTO = reserveMapper.selectRoomList().stream()
				.map(vo -> modelMapper.map(vo, RoomDTO.class))
				.collect(Collectors.toList());
		return roomDTO;
	}
	
	@Override
	public RoomDTO getOneRoom(String ro_id) {
		RoomVO roomVO = reserveMapper.selectOneRoom(ro_id);
		RoomDTO roomDTO = modelMapper.map(roomVO, RoomDTO.class);
		return roomDTO;
	}

	@Override
	public List<ReserveDTO> getByUser(HashMap<String, Object> map) {
		List<ReserveDTO> dtoList = reserveMapper.selectByUser(map).stream()
				.map(vo -> modelMapper.map(vo, ReserveDTO.class))
				.collect(Collectors.toList());
		return dtoList;		
	}

	@Override
	public void registerRoom(RoomDTO roomDTO) {
		RoomVO roomVO = modelMapper.map(roomDTO, RoomVO.class);
		reserveMapper.insertRoom(roomVO);
		
	}

	@Override
	public void modifyRoom(RoomDTO roomDTO) {
		RoomVO roomVO = modelMapper.map(roomDTO, RoomVO.class);
		reserveMapper.updateRooom(roomVO);
		
	}

	@Override
	public ReserveDTO getByRoomIdToday(HashMap<String, Object> map) {
		ReserveVO reserveVO = reserveMapper.selectByRoomIdToday(map);
		ReserveDTO reserveDTO = null;
		if(reserveVO != null) {
			reserveDTO = modelMapper.map(reserveVO, ReserveDTO.class);
		}
		return reserveDTO;
	}

}
