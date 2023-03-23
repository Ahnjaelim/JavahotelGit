package controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.ReserveDTO;
import dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import service.ReserveService;
import utils.DateProcess;

@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
	
	private final ReserveService reserveService;
	private final DateProcess dateProcess;
	
	@GetMapping("/test")
	public void testGET() {
		System.out.println("<Controller> test......");
	}
	@GetMapping("/list")
	public void listGET(String curYear, String curMon, Model model) {
		System.out.println("<Controller> list Get......");
		HashMap<String, Object> datemap = dateProcess.dateCalculate(curYear, curMon, 0); // ���� ��, �� ���� ��¥ ���	
		HashMap<Integer, Object> maplist = new HashMap<>(); // ���� ����Ʈ ��
		HashMap<String, Object> varmap = new HashMap<>(); // getByRoomId �Ű����� ��
		List<RoomDTO> roomlist = reserveService.getRoomList(); // ���� ����Ʈ �ҷ�����
		for(int i=0; i<roomlist.size(); i++) {
			varmap.clear();
			varmap.put("ro_id", roomlist.get(i).getRo_id());
			varmap.put("firstday", datemap.get("firstday"));
			varmap.put("lastday", datemap.get("lastday"));			
			maplist.put(roomlist.get(i).getRo_id(), reserveService.getByRoomId(varmap));
		}
		model.addAttribute("maplist", maplist);
		
		// �� ��� ������
		model.addAttribute("roomlist", reserveService.getRoomList());
	}
	@GetMapping("/register")
	public void registerGET(String ro_id, Model model) {
		System.out.println("<Controller> register GET......");
		// �� ��� ���
		model.addAttribute("roomlist", reserveService.getRoomList());
		// �ش� �� ���� ��� �ҷ����� 
		HashMap<String, Object> datemap = dateProcess.dateCalculate(null, null, 1);
		HashMap<String, Object> varmap = new HashMap<>();
		varmap.put("ro_id", ro_id);
		varmap.put("firstday", datemap.get("firstday"));
		varmap.put("lastday", datemap.get("lastday"));
		HashMap<String, ReserveDTO> map = reserveService.getByRoomId(varmap);
		model.addAttribute("map", map);
		// �ش� �� ����
		model.addAttribute("room", reserveService.getOneRoom(ro_id));
		
	}
	@PostMapping("/register")
	public String registerPOST(ReserveDTO reserveDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println("<Controller> register POST......");
		System.out.println(reserveDTO);
		reserveService.register(reserveDTO);
		return "redirect:/reserve/list";
		
	}
	@GetMapping("/admin")
	public void adminGET(Model model) {
		model.addAttribute("dtolist", reserveService.getAll());
	}	
	@GetMapping("/read")
	public void readGET(Model model) {
		
	}
	@PostMapping("/read")
	public void readPOST(ReserveDTO reserveDTO, Model model) {
		System.out.println("<Controller> read POST......");
		System.out.println(reserveDTO);
		HashMap<String, Object> map = new HashMap<>();
		map.put("re_name", reserveDTO.getRe_name());
		map.put("re_phone", reserveDTO.getRe_phone());
		map.put("re_password", reserveDTO.getRe_password());
		model.addAttribute("dtolist", reserveService.getByUser(map));
	}
}
