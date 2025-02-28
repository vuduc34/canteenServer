package project.canteen.repository.canteen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import project.canteen.entity.canteen.foodItem;
import project.canteen.entity.canteen.order;

@Component
public interface orderRepository extends JpaRepository<order, Long> {
    @Query(value = "SELECT * from `order` where id = :id", nativeQuery = true)
    order findOrderById(@Param("id") Long id);
    @Query(value = "SELECT * from `order` where code = :code", nativeQuery = true)
    order findOrderByCode(@Param("code") String code);
}
