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
@Table(name = "states")
@BatchSize(size = 50)
public class State implements Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "states_seq")
    private Long id;

    @Column(name = "state_name")
    private String name;
}
