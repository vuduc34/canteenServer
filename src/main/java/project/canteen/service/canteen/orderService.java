package project.canteen.service.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.canteen.common.constant;
import project.canteen.entity.auth.account;
import project.canteen.entity.canteen.*;
import project.canteen.model.auth.ResponMessage;
import project.canteen.model.canteen.addCartItemModel;
import project.canteen.model.canteen.createOrderModel;
import project.canteen.repository.auth.accountRepository;
import project.canteen.repository.canteen.cartItemRepository;
import project.canteen.repository.canteen.cartRepository;
import project.canteen.repository.canteen.orderItemRepository;
import project.canteen.repository.canteen.orderRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class orderService {
    @Autowired
    private orderRepository orderRepository;
    @Autowired
    private orderItemRepository orderItemRepository;
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private cartRepository cartRepository;
    @Autowired
    private cartItemRepository cartItemRepository;

    public ResponMessage createOrder(createOrderModel createOrderModel) {
        ResponMessage responMessage = new ResponMessage();
        try{
            account account = accountRepository.findAccountById(createOrderModel.getAccount_id());
            cart cart = cartRepository.findCartById(createOrderModel.getCart_id());
            if(account == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData("Not found account id = "+createOrderModel.getAccount_id());
            } else if(cart == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData("Not found cart id = "+createOrderModel.getCart_id());
            } else if(account.getCart().getId() != createOrderModel.getCart_id()) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData("account id = "+createOrderModel.getAccount_id()+" not mapping with cart id = "+createOrderModel.getCart_id());
            } else if(cart.getCartItems().size() == 0) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData("Cart is empty");
            } else {

                order order = new order();
                order.setTotalPrice(cart.getTotalPrice());
                order.setStatus(constant.STATUS.UN_CONFIRMED);
                order.setAccount(account);
                order.setCode(generateCode());
                orderRepository.save(order);
                order orderSaved = orderRepository.findOrderByCode(order.getCode());
                cart.getCartItems().forEach(cartItem -> {
                    orderItem orderItem = new orderItem();
                    orderItem.setOrder(orderSaved);
                    orderItem.setFoodItem(cartItem.getFoodItem());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItemRepository.save(orderItem);
                });
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(orderSaved);

                // remove cartItem
                cart.setTotalPrice(0L);
                cartRepository.save(cart);
                Iterator<cartItem> iterator = cart.getCartItems().iterator();
                while (iterator.hasNext()) {
                    cartItem cartItem = iterator.next();
                    cartItemRepository.deleteCartItemById(cartItem.getId());
                }
            }
        }catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.ERROR);
            responMessage.setData(e.getMessage());
        }
        return responMessage;
    }

    public String generateCode() {
        Random random = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            token.append(random.nextInt(10));
        }
        return token.toString();
    }
}
