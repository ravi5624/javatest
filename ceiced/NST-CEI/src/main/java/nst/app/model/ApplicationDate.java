package nst.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import nst.app.enums.ApplicationType;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "application_date")
@BatchSize(size = 50)
public class ApplicationDate extends BaseAuditableModel {

    @Column(name = "application_type")
    @Enumerated(value = EnumType.STRING)
    ApplicationType applicationType;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    public ApplicationDate() {
    }

    public ApplicationDate(ApplicationType applicationType, Date startDate, Date endDate) {
        this.applicationType = applicationType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
