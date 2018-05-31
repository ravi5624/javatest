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
@Table(name = "testing_instrument")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class TestingInstrument extends BaseAuditableModel {

    public TestingInstrument() {
    }

    public TestingInstrument(CommonForm form) {
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


}
