package edu.gatech.deliveryservice.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OrderSystemViewController {

//    customer related
//    @GetMapping("/make_customer")
//    public String makeCustomer() {
//        return "make_customer";
//    }

    @GetMapping("/display_customers")
    public String displayCustomer() {
        return "display_customers";
    }

//    order related
    @GetMapping("/order")
    public String orderMainMenu() {
        return "order";
    }

    @GetMapping("/order/display_orders")
    public String displayOrder() {
        return "display_orders";
    }

    @GetMapping("/order/display_orders_customer")
    public String displayOrderForCustomer() {
        return "display_orders_customer";
    }

    @GetMapping("/order/start_order")
    public String startOrder() {
        return "start_order";
    }

    @GetMapping("/order/request_item")
    public String requestItem() {
        return "request_item";
    }

    @GetMapping("/order/purchase_order")
    public String purchaseOrder() {
        return "purchase_order";
    }

    @GetMapping("/order/cancel_order")
    public String cancelOrder() {
        return "cancel_order";
    }

    //@GetMapping("/order/downloadArchivedOrders")
    //public String downloadArchivedOrders() {
        //return "download_archive_order";
    //}
}
