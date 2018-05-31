package nst.manager;

import nst.common.Manager;
import nst.kernal.ActivityLog;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogManager implements Manager {

    public ActivityLog save(ActivityLog activityLog){
        System.out.println("activityLog: " + activityLog);
        return null;
    }
}
