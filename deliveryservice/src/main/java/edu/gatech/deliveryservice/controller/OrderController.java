package edu.gatech.deliveryservice.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.source.tree.Tree;
import edu.gatech.deliveryservice.jpa.*;
import edu.gatech.deliveryservice.service.OrderSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

@Slf4j
@RestController
//@RequestMapping(path = "order")
public class OrderController {
    private final OrderSystem orderSystem;

    @Autowired
    public OrderController(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    // order
//    @GetMapping(path = "/order/list_orders")
//    public ResponseEntity<Object> displayOrder(@RequestParam("storeName") String storeName) {
//        TreeMap<String, List<ItemLine>> ordersWithItemLine = orderSystem.displayOrders(storeName);
//        String result = "";
//        for (String orderID: ordersWithItemLine.keySet()){
//            List<ItemLine> itemLinesPerOrder = ordersWithItemLine.get(orderID);
//            if (itemLinesPerOrder.isEmpty()){
//                result += "<tr>";
//                result += "<td>" + orderID + "</td>";
//                result += "<td>" + " " + "</td>";
//                result += "<td>" + " " + "</td>";
//                result += "<td>" + " " + "</td>";
//                result += "<td>" + " " + "</td>";
//                result += "</tr>";
//            } else {
//                for (ItemLine itemLine: itemLinesPerOrder) {
//                    result += "<tr>";
//                    result += "<td>" + orderID + "</td>";
//                    result += "<td>" + itemLine.getItemName() + "</td>";
//                    result += "<td>" + itemLine.getQuantity() + "</td>";
//                    result += "<td>" + itemLine.getLineCost() + "</td>";
//                    result += "<td>" + itemLine.getLineWeight() + "</td>";
//                    result += "</tr>";
//                }
//            }
//        }
//        ServiceResponse<String> response = new ServiceResponse<>("success", result);
//        return new ResponseEntity<Object>(response, HttpStatus.OK);
//    }
    @GetMapping(path = "/order/list_orders")
    public ResponseEntity<Object> displayOrder(@RequestParam("storeName") String storeName) {
        TreeMap<String, List<ItemLine>> ordersWithItemLine = orderSystem.displayOrders(storeName);
        TreeMap<String, Order> orders = orderSystem.findOrdersForStore(storeName);
        String result = "";
        for (String orderID: ordersWithItemLine.keySet()){
            List<ItemLine> itemLinesPerOrder = ordersWithItemLine.get(orderID);
            Order order = orders.get(orderID);
            if (itemLinesPerOrder.isEmpty()){
                result += "<tr>";
                result += "<td>" + orderID + "</td>";
                result += "<td>" + order.getOrderStatus() + "</td>";
                result += "<td>" + order.getOrderDate() + "</td>";
                result += "<td>" + order.getCustomerID() + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "</tr>";
            } else {
                for (ItemLine itemLine: itemLinesPerOrder) {
                    result += "<tr>";
                    result += "<td>" + orderID + "</td>";
                    result += "<td>" + order.getOrderStatus() + "</td>";
                    result += "<td>" + order.getOrderDate() + "</td>";
                    result += "<td>" + order.getCustomerID() + "</td>";
                    result += "<td>" + itemLine.getItemName() + "</td>";
                    result += "<td>" + itemLine.getQuantity() + "</td>";
                    result += "<td>" + itemLine.getLineCost() + "</td>";
                    result += "<td>" + itemLine.getLineWeight() + "</td>";
                    result += "</tr>";
                }
            }
        }
        ServiceResponse<String> response = new ServiceResponse<>("success", result);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/order/list_orders_customer")
    public ResponseEntity<Object> displayOrderForCustomer(@RequestParam("customerID") String customerID) {
        TreeMap<String, List<ItemLine>> ordersWithItemLine = orderSystem.displayOrdersForCustomer(customerID);
        TreeMap<String, Order> orders = orderSystem.findOrdersForCustomer(customerID);
        String result = "";
        for (String orderIDAndStoreID: ordersWithItemLine.keySet()){
            String orderID = orderIDAndStoreID.split(",")[0];
            String storeName = orderIDAndStoreID.split(",")[1];
            List<ItemLine> itemLinesPerOrder = ordersWithItemLine.get(orderIDAndStoreID);
            Order order = orders.get(orderIDAndStoreID);
            if (itemLinesPerOrder.isEmpty()){
                result += "<tr>";
                result += "<td>" + orderID + "</td>";
                result += "<td>" + order.getOrderStatus() + "</td>";
                result += "<td>" + order.getOrderDate() + "</td>";
                result += "<td>" + order.getStoreName() + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "<td>" + " " + "</td>";
                result += "</tr>";
            } else {
                for (ItemLine itemLine: itemLinesPerOrder) {
                    result += "<tr>";
                    result += "<td>" + orderID + "</td>";
                    result += "<td>" + order.getOrderStatus() + "</td>";
                    result += "<td>" + order.getOrderDate() + "</td>";
                    result += "<td>" + order.getStoreName() + "</td>";
                    result += "<td>" + itemLine.getItemName() + "</td>";
                    result += "<td>" + itemLine.getQuantity() + "</td>";
                    result += "<td>" + itemLine.getLineCost() + "</td>";
                    result += "<td>" + itemLine.getLineWeight() + "</td>";
                    result += "</tr>";
                }
            }
        }
        ServiceResponse<String> response = new ServiceResponse<>("success", result);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/order/start_order")
    public ResponseEntity<Object> startOrder(@RequestBody Order order) {
        log.info("Start thread executor for starting order");
        orderSystem.startOrder(order);
        log.info("End thread executor for starting order");
        ServiceResponse<Order> response = new ServiceResponse<>("success", order);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


    @PostMapping(path = "/order/request_item")
    public ResponseEntity<Object> requestItem(@RequestBody ItemLine itemLine) {
        log.info("Start thread executor for requesting item");
        orderSystem.requestItem(itemLine);
        log.info("End thread executor for requesting item");
        ServiceResponse<ItemLine> response = new ServiceResponse<>("success", itemLine);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/order/purchase_order")
    public ResponseEntity<Object> purchaseOrder(@RequestBody ObjectNode objectNode) {
        log.info("Start thread executor for purchasing order");
        orderSystem.purchaseOrder(objectNode.get("storeName").asText(), objectNode.get("orderID").asText());
        log.info("End thread executor for purchasing order");
        ServiceResponse<Object> response = new ServiceResponse<>("success", "");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/order/cancel_order")
    public ResponseEntity<Object> cancelOrder(@RequestBody ObjectNode objectNode) {
        log.info("Start thread executor for canceling order");
        orderSystem.cancelOrder(objectNode.get("storeName").asText(), objectNode.get("orderID").asText());
        log.info("End thread executor for canceling order");
        ServiceResponse<Object> response = new ServiceResponse<>("success", "");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/order/downloadArchivedOrders")
    public void getAllArchivedOrdersInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        LocalDateTime today = LocalDateTime.now();
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"archived_orders_"+today.toString()+".csv\"");
        orderSystem.writeArchivedOrdersToCsv(servletResponse.getWriter());
    }
}
