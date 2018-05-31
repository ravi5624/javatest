package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@Entity
@Table(name = "le_safety_gadget")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class LESafetyGadget extends BaseAuditableModel {

    public LESafetyGadget() {
    }

    public LESafetyGadget(CommonForm form) {
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

    @Column(name = "is_new")
    Boolean isNew;


}
