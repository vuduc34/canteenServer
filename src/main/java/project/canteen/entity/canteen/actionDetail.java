package project.canteen.entity.canteen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class actionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long account_id;
    @Column
    private Long order_id;
    @ManyToOne
    @JoinColumn(name = "action_id")
    @JsonIgnore
    private action action;
}
