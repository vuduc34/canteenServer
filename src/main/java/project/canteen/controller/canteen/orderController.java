package project.canteen.controller.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.canteen.common.constant;
import project.canteen.entity.canteen.foodItem;
import project.canteen.model.auth.ResponMessage;
import project.canteen.model.canteen.createOrderModel;
import project.canteen.service.canteen.orderService;

@RestController
@RequestMapping(constant.API.PREFIX)
public class orderController {
    @Autowired
    private orderService orderService;

    @PostMapping(value = "/order/create")
    @ResponseBody
    public ResponMessage createOrder(@RequestBody createOrderModel createOrderModel) {
        return orderService.createOrder(createOrderModel);
    }
}
