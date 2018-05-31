package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.common.base.BaseAuditableModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@Entity
@Table(name = "le_testing_instrument")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class LETestingInstrument extends BaseAuditableModel {

    public LETestingInstrument() {
    }

    public LETestingInstrument(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @ManyToOne
    CommonForm form;

    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "make")
    private String make;

    @Column(name = "number")
    private String number;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "is_new")
    Boolean isNew;
}