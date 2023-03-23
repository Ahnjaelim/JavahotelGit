package domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;
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
public class ReserveVO {
	private int re_id;
	private int ro_id;
	private String re_name;
	private String re_password;
	private String re_phone;
	private LocalDate re_startday;
	private LocalDate re_endday;
	private int re_capa;
	private int re_price;
	private int re_payment;
	private Timestamp re_datetime;
}
