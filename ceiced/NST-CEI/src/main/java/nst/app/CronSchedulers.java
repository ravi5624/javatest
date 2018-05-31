package nst.app;

import java.util.Date;
import nst.config.MyLogger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CronSchedulers {

  @Scheduled(cron = "0 0 0/1 * * ?")
  public void addToLog() {
    String data = String.format("Added to Log @ %s", new Date());
    MyLogger.log("CronSchedulers", data);
    MyLogger.logService("CronSchedulers", data);
    MyLogger.logError("CronSchedulers", data);
  }

  //@Scheduled(cron = "0 0/1 * * * ?")
  public void testSchedular() {
    String data = String.format("Added to Log @ %s", new Date());
    MyLogger.log("CronSchedulers", data);
    MyLogger.logService("CronSchedulers", data);
    MyLogger.logError("CronSchedulers", data);
  }
}
