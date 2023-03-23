package service;

import java.util.HashMap;
import java.util.List;
import dto.ReserveDTO;
import dto.RoomDTO;

public interface ReserveService {
	void register(ReserveDTO reserveDTO);
	List<ReserveDTO> getAll();
	// HashMap<String, ReserveDTO> getRoomId(String ro_id, String firstday, String lastday);
	HashMap<String, ReserveDTO> getByRoomId(HashMap<String, Object> map);
	ReserveDTO getByRoomIdToday(HashMap<String, Object> map);
	List<ReserveDTO> getByUser(HashMap<String, Object> map);
	List<RoomDTO> getRoomList();
	RoomDTO getOneRoom(String ro_id);
	void registerRoom(RoomDTO roomDTO);
	void modifyRoom(RoomDTO roomDTO);
}
