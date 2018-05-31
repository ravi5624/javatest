package nst.kernal;

import nst.config.MyLogger;
import nst.dto.APIRequestDTO;
import nst.dto.APIResponseDTO;
import nst.util.ExternalServiceUtil;
import org.springframework.stereotype.Component;


@Component
public class SMSSender {

  public void send(APIRequestDTO apiRequestDTO) {
    APIResponseDTO result = ExternalServiceUtil.getResult(apiRequestDTO);
    MyLogger.logService("SMSSender", result.getResponse());
  }
}
