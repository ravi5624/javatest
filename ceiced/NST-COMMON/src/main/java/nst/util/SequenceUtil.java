package nst.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import nst.config.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SequenceUtil {

  DataSource dataSource;

  public Long getNextSequence(String seqName) {
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      connection = dataSource.getConnection();
      stmt = connection.createStatement();
      String query = "SELECT nextval('" + seqName + "')";
      rs = stmt.executeQuery(query);
      if (rs.next()) {
        return rs.getLong(1);
      }
    } catch (Exception e) {
      MyLogger.logError(e);
      return null;
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (Exception e) {
        MyLogger.logError(e);
      }
    }
    return null;
  }

  public void resetSequence(String seqName, Long index) {
    Connection connection = null;
    Statement stmt = null;
    try {
      connection = dataSource.getConnection();
      stmt = connection.createStatement();
      String query = "SELECT setval('" + seqName + "', " + index + ")";
      stmt.executeQuery(query);
    } catch (Exception e) {
      MyLogger.logError(e);

    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        MyLogger.logError(e);
      }
    }
  }
}
