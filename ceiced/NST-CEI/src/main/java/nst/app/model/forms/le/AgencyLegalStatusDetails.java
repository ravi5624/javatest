package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

/* it is depedent class of EAndMAgencyLicense, IAndTAgencyLicense, OandMAgencyLicense*/
@Data
@Entity
@Table(name = "agency_legal_status_details")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class AgencyLegalStatusDetails extends BaseModel {

    public AgencyLegalStatusDetails() {

    }

    public AgencyLegalStatusDetails(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @ManyToOne
    CommonForm form;

    @Column(name = "partner_name")
    private String partnerName;

}