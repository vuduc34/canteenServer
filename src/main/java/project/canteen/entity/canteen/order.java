package project.canteen.entity.canteen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.canteen.entity.auth.account;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long totalPrice;
    @Column
    private String status;
    @Column
    private String code;
    @Column( name = "orderTime")
    private LocalDateTime orderTime;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<orderItem> orderItems;


    @PrePersist
    protected void onCreate() {
        orderTime = LocalDateTime.now();
    }
}
