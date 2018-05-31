package nst.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nst.common.base.BaseDTO;
import nst.config.MyLogger;
import nst.kernal.SystemConstants;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AllUtil {

  private static final int OTP_CODE_LENGTH = 5;
  private static final int EMAIL_CODE_LENGTH = 5;

  private AllUtil() {
  }

  public static void print(String name) {
    System.out.println("Your Name is : " + name);
  }

  public static int getCUP() {
    return Runtime.getRuntime().availableProcessors() * 100;
  }

  public static String getEmailCode() {
    return getUUID();
  }

  public static String getOtpCode() {
    return RandomUtil.getNumericWord(OTP_CODE_LENGTH);
  }

  public static String toJSON(BaseDTO params) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(params);
    } catch (JsonProcessingException e) {
    }
    return null;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static String getUUID(String data) {
    return UUID.nameUUIDFromBytes(data.getBytes()).toString().replace("-", "");
  }

  public static boolean isDataMatching(String regex, String data) {
    Pattern pattern = Pattern.compile(regex);
    final Matcher matcher = pattern.matcher(data);
    return matcher.matches();
  }

  public static String extractData(String src, String regex, int index) {
    if (StringUtils.isEmpty(src) || StringUtils.isEmpty(regex) || index < 0) {
      return null;
    }

    try {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(src);
      if (matcher.find()) {
        return matcher.group(index);
      }
    } catch (Exception e) {
    }
    return null;
  }

  public static String formatDate(String date, String pattern) {
    try {
      Date date1 = new SimpleDateFormat(SystemConstants.FORMAT_DATE_COMMA).parse(date);
      return new SimpleDateFormat(pattern).format(date1);
    } catch (Exception e) {
//      MyLogger.log("AllUtil", "formatDate : %s with format : %s ==> %s", date, pattern, e.getMessage());
    }
    return null;
  }

  public static String formatDate(Date date) {
    try {
      return new SimpleDateFormat(SystemConstants.FORMAT_DATE_BACKSLASH).format(date);
    } catch (Exception e) {
      //MyLogger.log("AllUtil", "formatDate : %s ==> %s", date, e.getMessage());
    }
    return null;
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    try {
      Instant now = date.toInstant();
      ZoneId currentZone = ZoneId.systemDefault();
      return LocalDateTime.ofInstant(now, currentZone);
    } catch (Exception e) {
      //MyLogger.log("AllUtil", "formatDate : %s ==> %s", date, e.getMessage());
    }
    return null;
  }

  public static String formatFlatDate(Date date) {
    try {
      return new SimpleDateFormat(SystemConstants.FORMAT_DATE_FLAT).format(date);
    } catch (Exception e) {
      //MyLogger.log("AllUtil", "formatDate : %s ==> %s", date, e.getMessage());
    }
    return null;
  }

  public static String formatUIDate(Date date) {
    try {
      return new SimpleDateFormat(SystemConstants.FORMAT_DATE_COMMA).format(date);
    } catch (Exception e) {
      //MyLogger.log("AllUtil", "formatDate : %s ==> %s", date, e.getMessage());
    }
    return null;
  }

  public static String formatNGDate(Date date) {
    try {
      return new SimpleDateFormat(SystemConstants.NEWGEN_DATE_FORMAT).format(date);
    } catch (Exception e) {
      //MyLogger.log("AllUtil", "formatDate : %s ==> %s", date, e.getMessage());
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println("args = " + isDataMatching("(.*)Vishal(.*)", "Vishal Patel"));
  }

  public static Date toDate(String date) {
    if (StringUtils.isEmpty(date)) {
      return null;
    }
    return toDate(date, SystemConstants.FORMAT_DATE_BACKSLASH);
  }

  public static Integer getYear(String date, String format) {
    if (StringUtils.isEmpty(date))
      return null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    LocalDate localDate = LocalDate.parse(date, formatter);
    return localDate.getYear();
  }

  public static Date toUIDate(String date) {
    if (StringUtils.isEmpty(date)) {
      return null;
    }
    return toDate(date, SystemConstants.FORMAT_DATE_COMMA);
  }

  public static Date manageUIDate(String date) {
    if (StringUtils.isEmpty(date)) {
      return null;
    }
    return toDate(date, SystemConstants.FORMAT_DATE_COMMA);
  }

  public static String manageUIDate(Date date) {
    try {
      return new SimpleDateFormat(SystemConstants.FORMAT_DATE_COMMA).format(date);
    } catch (Exception e) {
    }
    return null;
  }

  public static Date toNGDate(String date) {
    if (StringUtils.isEmpty(date)) {
      return null;
    }
    return toDate(date, SystemConstants.NEWGEN_DATE_FORMAT);
  }

  public static Date toDate(String date, String pattern) {
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      simpleDateFormat.setLenient(false);
      return simpleDateFormat.parse(date);
    } catch (Exception e) {
      MyLogger.log("CommonUtil : getDate : <%s> with format : %s ==> %s", date, pattern,
          e.getMessage());
    }
    return null;
  }

  public static String getAppNo() {
    return AllUtil.formatFlatDate(new Date()) + RandomUtil.getWord(5).toUpperCase();
  }

  public static String getFileName() {
    return RandomUtil.getWord(5).toUpperCase();
  }

  public static String getAppNo(String code) {
    return AllUtil.formatFlatDate(new Date()) + "-" + code + "-" + RandomUtil.getWord(5)
        .toUpperCase();
  }

  public static Date getDayStart() {
    return getDayStart(new Date());
  }

  public static Date getDayStart(Date day) {
    if (day == null) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(day);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  private static Date parse(String date, String pattern) {
    try {
      if (StringUtils.isEmpty(date)) {
        return null;
      }
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      simpleDateFormat.setLenient(false);
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
    }
    return null;
  }

  public static String getDocType(String docName) {
    return extractData(docName, SystemConstants.REGEX_FILE_EXT, 2);
  }

  public static String intToString(Integer intValue) {
    if (intValue == null) {
      return "";
    }
    return String.valueOf(intValue);
  }

  public static String getFieldValue(Object value, String fieldName) {
    try {
      return ReflectionUtils.findMethod(value.getClass(), "get" + fieldName).invoke(value)
          .toString();
    } catch (Exception e) {
      try {
        return ReflectionUtils.findMethod(value.getClass(), "is" + fieldName).invoke(value)
            .toString();
      } catch (Exception e1) {
        return null;
      }
    }
  }

  public static <T> T getValue(Object value, String fieldName) {
    try {
      return (T) ReflectionUtils.findMethod(value.getClass(), "get" + fieldName).invoke(value);
    } catch (Exception e) {
    }
    return null;
  }

  public static void print(Object... values) {
    System.out.println(Arrays.stream(values).map(Object::toString).collect(Collectors.joining(" => ")));
  }
}