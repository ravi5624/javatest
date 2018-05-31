package nst.service;

import nst.common.AbstractService;
import nst.kernal.ActivityLog;
import nst.kernal.ExternalCommunication;
import nst.manager.ActivityLogManager;
import nst.manager.ExternalCommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BackgroundService extends AbstractService {

  @Autowired
  ExternalCommunicationManager communicationManager;

  @Autowired
  ActivityLogManager activityLogManager;

  public void add(ExternalCommunication model) {
    new Thread() {
      public void run() {
        communicationManager.save(model);
      }
    }.start();
  }

  public void log(ActivityLog model) {
    activityLogManager.save(model);
  }
}
