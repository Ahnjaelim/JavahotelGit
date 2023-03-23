package mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import domain.ReserveVO;
import domain.RoomVO;

public interface ReserveMapper {
	String getTime();
	void insert(ReserveVO reserveVO);
	List<ReserveVO> selectAll();
	List<ReserveVO> selectByRoomId(HashMap<String, Object> map);
	ReserveVO selectByRoomIdToday(HashMap<String, Object> map);
	List<ReserveVO> selectByUser(HashMap<String, Object> map);
	List<RoomVO> selectRoomList();
	RoomVO selectOneRoom(String ro_id);
	void insertRoom(RoomVO roomVO);
	void updateRooom(RoomVO roomVO);
}
