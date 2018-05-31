package nst.app.model.forms;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.DesignationType;
import nst.app.model.forms.le.CommonForm;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "form_i_employer")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class FormIEmployer extends BaseAuditableModel{
    public FormIEmployer() {
    }

    public FormIEmployer(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @ManyToOne
    CommonForm form;

    @Column(name = "name")
    private String name;


    @Column(name = "permit_no")
    String permitNo;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    DesignationType designation;


    @Column(name = "joining_date", columnDefinition = "DATE")
    Date joiningDate;

    @Column(name = "leaving_date", columnDefinition = "DATE")
    Date leavingDate;

    @Column(name = "is_inactive")
    Boolean isInactive;

    @Column(name = "is_new")
    Boolean isNewEmployer = Boolean.TRUE;

    @Column(name = "other_attachment_name")
    String otherAttachmentName;

}




