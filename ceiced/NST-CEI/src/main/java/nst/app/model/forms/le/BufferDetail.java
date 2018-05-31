package nst.app.model.forms.le;

import lombok.Data;
import lombok.ToString;
import nst.app.enums.AddressType;
import nst.app.enums.AgencyType;
import nst.app.enums.BufferType;
import nst.app.model.forms.Address;
import nst.common.base.BaseAuditableModel;
import nst.common.base.BaseModel;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@Entity
@Table(name = "buffer_detail")
@BatchSize(size = 50)
@ToString(exclude = "form", callSuper = true)
public class BufferDetail extends BaseModel {

    public BufferDetail(){}


    public BufferDetail(CommonForm form) {
        this.form = form;
    }

    @JoinColumn(name = "form_id")
    @OneToOne
    CommonForm form;

    @Column(name = "buffer_type")
    @Enumerated(value = EnumType.STRING)
    BufferType bufferType;

    @Column(name = "type")
    private String type;

    @Column(name = "other_type")
    private String  otherType;

    @Column(name = "number_of_buffer")
    private Integer numberOfBuffer;

    @Column(name = "stroke")
    private Integer stroke;

    public boolean isFor(BufferType type) {
        return getBufferType() == type;
    }
}