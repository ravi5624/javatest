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
@Table(name = "talukas")
@BatchSize(size = 50)
public class Taluka implements Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "talukas_seq")
    private Long id;

    @Column(name = "taluka_code")
    private String code;

    @Column(name = "taluka_name")
    private String name;

    @Column(name = "district_id")
    private Long districtId;
}