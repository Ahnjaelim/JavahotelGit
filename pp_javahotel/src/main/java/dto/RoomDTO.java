package dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	private int ro_id;
	private String ro_name;
	private String ro_type;
	private int ro_capa;
	private int ro_price;
	private int ro_use;
	private int ro_state;
	private Timestamp ro_datetime;
}
