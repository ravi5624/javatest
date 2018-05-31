package nst.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import nst.common.Model;
import org.hibernate.annotations.BatchSize;

@Data
@Entity
@Table(name = "districts")
@BatchSize(size = 50)
public class District implements Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "districts_seq")
    private Long id;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "district_code")
    private String code;

    @Column(name = "district_name")
    private String name;

    @Column(name = "state_id")
    private Long stateId;
}
