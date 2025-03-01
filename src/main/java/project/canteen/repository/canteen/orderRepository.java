package project.canteen.repository.canteen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import project.canteen.entity.canteen.foodItem;
import project.canteen.entity.canteen.order;

import java.util.List;

@Component
public interface orderRepository extends JpaRepository<order, Long> {
    @Query(value = "SELECT * from `order` where id = :id", nativeQuery = true)
    order findOrderById(@Param("id") Long id);
    @Query(value = "SELECT * from `order` where code = :code", nativeQuery = true)
    order findOrderByCode(@Param("code") String code);

    @Query(value = "SELECT * from `order` where account_id = :account_id", nativeQuery = true)
    List<order> findOrderByAccountId(@Param("account_id") Long account_id);

    @Query(value = "SELECT * from `order` where status = 'unconfirmed' or status = 'preparing' ORDER BY `order_time` ASC", nativeQuery = true)
    List<order> findOrderPreparingOrUnconfirmed();
}
