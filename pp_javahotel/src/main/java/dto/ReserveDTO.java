package dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;
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
public class ReserveDTO {
	int re_id;
	int ro_id;
	String re_name;
	String re_password;
	String re_phone;
	LocalDate re_startday;
	LocalDate re_endday;
	int re_capa;
	int re_price;
	int re_payment;
	Timestamp re_datetime;
}
