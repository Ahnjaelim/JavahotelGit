package controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.ReserveDTO;
import dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import service.ReserveService;
import utils.DateProcess;

@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

	private final ReserveService reserveService;
	private final DateProcess dateProcess; 
	
	@GetMapping("/list")
	public void roomGET(Model model) {
		model.addAttribute("roomlist", reserveService.getRoomList());
		HashMap<Integer, Object> todaylist = new HashMap<>(); 
		HashMap<String, Object> varmap = new HashMap<>();
		List<RoomDTO> roomlist = reserveService.getRoomList();
		for(int i=0; i<roomlist.size(); i++) {
			varmap.clear();
			varmap.put("ro_id", roomlist.get(i).getRo_id());
			varmap.put("today", dateProcess.today());
			// 아하 값이 없을 때 나는 에러가 마이바티스가 아니라 모델 매퍼에서 나는 오류였음
			todaylist.put(roomlist.get(i).getRo_id(), reserveService.getByRoomIdToday(varmap));
		}
		model.addAttribute("todaylist", todaylist);
		
	}
	@GetMapping("/register")
	public void registerGET(String ro_id, Model model) {
		System.out.println("<Controller> register GET......");
		if(ro_id!=null) {
			model.addAttribute("room", reserveService.getOneRoom(ro_id));
		}
	}
	@PostMapping("/register")
	public String registerPOST(RoomDTO roomDTO) {
		System.out.println("<Controller> register POST......");
		System.out.println(roomDTO);
		reserveService.registerRoom(roomDTO);
		return "redirect:/room/list";	
	}
	@PostMapping("/modify")
	public String modifyPOST(RoomDTO roomDTO) {
		System.out.println("<Controller> modify POST......");
		reserveService.modifyRoom(roomDTO);
		return "redirect:/room/list";	
	}
	
	@GetMapping("/read")
	public void readGET(String ro_id, Model model) {
		System.out.println("<Controller> read GET......");
		HashMap<String, Object> varmap = new HashMap<>();
		varmap.put("ro_id", ro_id);
		varmap.put("today", dateProcess.today());
		model.addAttribute("dto", reserveService.getByRoomIdToday(varmap));
		
	}
	
}
