package nst.app.model.forms;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "organization_details")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class OrganizationDetails extends BaseAuditableModel{
    public OrganizationDetails() {
    }

    public OrganizationDetails(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @ManyToOne
    CommonForm form;

    @Column(name = "name_of_partner")
    private String nameOfPartner;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private Date dateOfBirth;


    @Column(name = "home_address")
    private String homeAddress;


}




