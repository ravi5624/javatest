package nst.util;

import java.util.concurrent.ThreadLocalRandom;
import nst.common.EnumType;

public class RandomUtil {

  private static final char[] DATA = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
      't', 'u', 'v', 'w', 'x', 'y', 'z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

  public static String getRandom(String... values) {
    return values[getNumber(values.length)];
  }

  public static <T> T getRandom(T... values) {
    return values[getNumber(values.length)];
  }

  public static EnumType getRandomFromEnum(EnumType... values) {
    return values[getNumber(values.length)];
  }

  public static EnumType getRandom(EnumType[] values) {
    return values[getNumber(values.length)];
  }

  public static String getWord(int length) {
    if (length < 0) {
      return "";
    }

    if (length == 1) {
      return new StringBuilder(DATA[getNumber(DATA.length)]).toString();
    }

    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(DATA[getNumber(DATA.length)]);
    }
    return builder.toString();
  }

  public static String getRandomLengthWord(int maxLength) {
    return getWord(1) + getWord(getNumber(maxLength - 2));
  }

  public static String getNumericWord(int length) {
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(getNumber(8) + 1);
    }
    return builder.toString();
  }

  public static int getNumber(int max) {
    return ThreadLocalRandom.current().nextInt(max);
  }

  public static int getNumber() {
    return ThreadLocalRandom.current().nextInt();
  }

  public static void sleep(int inMS) {
    try {
      Thread.sleep(inMS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}