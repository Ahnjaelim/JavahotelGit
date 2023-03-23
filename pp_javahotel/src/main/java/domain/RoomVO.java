package domain;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO {
	private int ro_id;
	private String ro_name;
	private String ro_type;
	private int ro_capa;
	private int ro_price;
	private int ro_use;
	private int ro_state;
	private Timestamp ro_datetime;
}
