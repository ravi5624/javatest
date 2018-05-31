package nst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import nst.common.base.BaseDTO;

@Data
@JsonInclude(Include.ALWAYS)
public class TransactionDTO extends BaseDTO {

  String transactionId;
  String status;
  double amount;
  String paidOn;
  String url;
}
