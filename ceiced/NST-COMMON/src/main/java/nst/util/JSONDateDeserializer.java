package nst.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Date;
import nst.common.error.AppException;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;

public class JSONDateDeserializer extends JsonDeserializer<Date> {

  @Override
  public Date deserialize(JsonParser jsonparser, DeserializationContext context) {
    String dateAsString;
    try {
      dateAsString = jsonparser.getText();
    } catch (IOException e) {
      MyLogger.logError(e);
      throw AppException
          .createWithMessageCode(SystemConstants.BAD_REQUEST, SystemConstants.INVALID_DATE);
    }
    return AllUtil.toDate(dateAsString);
  }
}
