package com.pgk.delivery.Order.Controller;


import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Order.Pojo.Order;
import com.pgk.delivery.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderService service;

    @RequestMapping("/addOrder.do")
    public Result<?> addOrder(@RequestBody Order order){
        Result<?> msg = service.addOrder(order);
        return msg;
    }

    /**
     * 查看订单详情
     * @param order//订单id
     * @return
     */
    @RequestMapping("/queryOrder.do")
    public Result<?> queryOrder(Order order){
        Result<?> msg =service.queryOrder(order);
        return msg;
    }
    /**
     *
     * @param orderBuyerAccount
     * @return
     */
    @RequestMapping("/selectOrder.do")
    public Result<?> selectOrder(String orderBuyerAccount){
        if( null == orderBuyerAccount){
        return null;
        }else {
            Result<?> msg = service.selectOrder(orderBuyerAccount);
            return msg;
        }

    }

    @RequestMapping("/sellerSelectOrder.do")
    public Result<?> sellerSelectOrder(int accountUserId){
        Result<?> msg = service.sellerSelectOrder(accountUserId);
        return msg;
    }

    @RequestMapping("/sellerSelectOrderById.do")
    public Result<?> sellerSelectOrderById(int accountUserId){
        Result<?> orderMap = service.sellerSelectOrderById(accountUserId);
        return orderMap;
    }


    @RequestMapping("/updateState.do")
    public Result<?> updateState(@RequestBody Order order){
        Result<?> msg = service.updateState(order);
        return msg;
    }
    @RequestMapping("/deleteOrder.do")
    public Result<?> deleteOrder(int orderId){
        Result<?> msg = service.deleteOrder(orderId);
        return msg;
    }
}
