package project.canteen.repository.canteen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.canteen.entity.canteen.category;
import project.canteen.entity.canteen.foodItem;

import java.util.List;

@Repository
public interface foodRepository extends JpaRepository<foodItem, Long> {
    @Query( value = "SELECT  * FROM food_item where category_id = :category_id  and status =:status ", nativeQuery = true)
    List<foodItem> findByCategory(@Param("category_id") Long category_id,@Param("status") String status);

    @Query(value = "SELECT * from food_item where id = :id", nativeQuery = true)
    foodItem findFoodById(@Param("id") Long id);
}
