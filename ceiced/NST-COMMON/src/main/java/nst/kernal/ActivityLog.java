package nst.kernal;

import lombok.Data;
import lombok.NoArgsConstructor;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "log_activity")
@BatchSize(size = 50)
public class ActivityLog extends BaseModel  {

    private static final long serialVersionUID = 1L;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Column(name = "action")
    private String action;

    @CreatedDate
    @Column(name = "current_date")
    private Date currentDate;

    public ActivityLog(ActivityType activityType, Long objectId, String userName, String... action) {
        this.objectId = objectId;
        this.userName = userName;
        this.activityType = activityType;
        this.action = Arrays.toString(action);
    }
}
