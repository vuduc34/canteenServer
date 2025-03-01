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
    @PutMapping(value = "/order/cancel")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponMessage cancelOrder(@RequestParam Long orderId) {
        return orderService.cancelOrder(orderId);
    }
    @PutMapping(value = "/order/preparing")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponMessage preparingOrder(@RequestParam Long orderId) {
        return orderService.preparingOrder(orderId);
    }

    @PutMapping(value = "/order/done")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponMessage doneOrder(@RequestParam Long orderId) {
        return orderService.doneOrder(orderId);
    }

    @GetMapping(value = "/order/findOrderByAccountId")
    @ResponseBody
    public ResponMessage findOrderByAccountId(@RequestParam Long accountId) {
        return orderService.getOrderByAccountId(accountId);
    }

    @GetMapping(value = "/order/getAllOrder")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponMessage getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping(value = "/order/getOrderPreparingOrUnconfirmed")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponMessage getOrderPreparingOrUnconfirmed() {
        return orderService.getOrderPreparingOrUnconfirmed();
    }
}
