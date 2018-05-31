package nst.app.model.forms;

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
@Table(name = "safety_gadget")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class SafetyGadget extends BaseAuditableModel {

    public SafetyGadget() {
    }

    public SafetyGadget(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @ManyToOne
    CommonForm form;

    @Column(name = "name")
    String name;

    @Column(name = "make")
    String make;

    @Column(name = "number")
    String number;

    @Column(name = "remarks")
    String remarks;


}
