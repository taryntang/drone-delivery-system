package edu.gatech.deliveryservice.service;

import edu.gatech.deliveryservice.jpa.*;
import edu.gatech.deliveryservice.repository.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class OrderSystem {

    private final Logger log = getLogger(OrderSystem.class);
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ItemLineRepository itemLineRepository;
    private final DroneRepository droneRepository;
    private final ItemRepository itemRepository;
    private final PilotRepository pilotRepository;
    private StoreSystem storeSystem;
    private String error_msg;

    @Autowired
    public OrderSystem(StoreRepository storeRepository, CustomerRepository customerRepository,
                       OrderRepository orderRepository, ItemLineRepository itemLineRepository,
                       DroneRepository droneRepository, ItemRepository itemRepository,
                       PilotRepository pilotRepository, StoreSystem storeSystem) {
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.itemLineRepository = itemLineRepository;
        this.droneRepository = droneRepository;
        this.itemRepository = itemRepository;
        this.pilotRepository = pilotRepository;
        this.storeSystem = storeSystem;
    }

    // customer
    @Async(value = "asyncAddCustomerThread")
    public void makeCustomer(Customer customer) {
        Optional<Customer> customerByCustomerID =
                customerRepository.findCustomerByCustomerID(customer.getCustomerID());

        try {Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (customerByCustomerID.isPresent()) {
            error_msg = "ERROR:customer_identifier_already_exists";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(customer.getCustomerPassword());
            customer.setCustomerPassword(encodedPassword);
            error_msg = "OK:change_completed";
            customerRepository.save(customer);
        }

        System.out.println(error_msg);

    }

    public List<Customer> displayCustomers(){
        error_msg = "OK:display_completed";
        System.out.println(error_msg);
        return customerRepository.findCustomersByCustomerIDIsNotAdmin();
    }

    // order
    @Async(value = "asyncStartOrderThread")
    public void startOrder(Order order) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(order.getStoreName());
        Optional<Order> orderByOrderIDAndStoreName =
                orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(order.getOrderID(), order.getStoreName());
        Optional<Drone> droneByDroneIDAndStoreName =
                droneRepository.findDroneByStoreNameAndDroneID(order.getStoreName(), order.getDroneID());
        Optional<Customer> customerByCustomerID = customerRepository.findCustomerByCustomerID(order.getCustomerID());

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!storeByStoreName.isPresent()){
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else if(orderByOrderIDAndStoreName.isPresent()){
            error_msg = "ERROR:order_identifier_already_exists";
        } else if (!droneByDroneIDAndStoreName.isPresent()){
            error_msg = "ERROR:drone_identifier_does_not_exist";
        } else if (!customerByCustomerID.isPresent()) {
            error_msg = "ERROR:customer_identifier_does_not_exist";
        } else if(droneByDroneIDAndStoreName.get().getRemainingDelivery() == 0) {
            error_msg = "ERROR:drone_needs_fuel";
        } else {
            orderRepository.save(order);
            Drone drone = droneRepository.findDroneByStoreNameAndDroneID(order.getStoreName(), order.getDroneID()).get();
            drone.updateNumOrders(1);
            droneRepository.save(drone);
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }



    public TreeMap<String, List<ItemLine>> displayOrders(String storeName){
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        if(!storeByStoreName.isPresent()){
            error_msg = "ERROR:store_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:store_identifier_does_not_exist");
        } else {
            TreeMap<String, List<ItemLine>> ordersWithItemLine = new TreeMap<>();
            List<Order> orderByStoreName = orderRepository.findOrdersByStoreNameOrderByOrderID(storeName);
            if (!orderByStoreName.isEmpty()){
                for(Order order: orderByStoreName.stream().toList()) {
                    List<ItemLine> itemLinePerOrder =
                            itemLineRepository.findItemLinesByStoreNameAndOrderID(storeName, order.getOrderID());
                    ordersWithItemLine.put(order.getOrderID(), itemLinePerOrder);
                }
            }
            error_msg = "OK:display_completed";
            System.out.println(error_msg);
            return ordersWithItemLine;
        }
    }

    public TreeMap<String, List<ItemLine>> displayOrdersForCustomer(String customerID){
        Optional<Customer> customerByCustomerID = customerRepository.findCustomerByCustomerID(customerID);
        if(!customerByCustomerID.isPresent()){
            error_msg = "ERROR:customer_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:customer_identifier_does_not_exist");
        } else {
            TreeMap<String, List<ItemLine>> ordersWithItemLineForCustomer = new TreeMap<>();
            List<Order> orderByCustomerID = orderRepository.findOrdersByCustomerIDOrderByOrderID(customerID);
            if (!orderByCustomerID.isEmpty()){
                for(Order order: orderByCustomerID.stream().toList()) {
                    List<ItemLine> itemLinePerOrder =
                            itemLineRepository.findItemLinesByStoreNameAndOrderID(order.getStoreName(), order.getOrderID());
                    ordersWithItemLineForCustomer.put(order.getOrderID() + "," + order.getStoreName(), itemLinePerOrder);
                }
            }
            error_msg = "OK:display_completed";
            System.out.println(error_msg);
            return ordersWithItemLineForCustomer;
        }
    }

    public TreeMap<String, Order> findOrdersForStore(String storeName){
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        if(!storeByStoreName.isPresent()){
            error_msg = "ERROR:store_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:store_identifier_does_not_exist");
        } else {
            TreeMap<String, Order> ordersMap = new TreeMap<>();
            List<Order> orderByStoreName = orderRepository.findOrdersByStoreNameOrderByOrderID(storeName);
            if (!orderByStoreName.isEmpty()){
                for(Order order: orderByStoreName.stream().toList()) {
                    ordersMap.put(order.getOrderID(), order);
                }
            }
            error_msg = "OK:display_completed";
            System.out.println(error_msg);
            return ordersMap;
        }
    }

    public TreeMap<String, Order> findOrdersForCustomer(String customerID){
        Optional<Customer> customerByCustomerID = customerRepository.findCustomerByCustomerID(customerID);
        if(!customerByCustomerID.isPresent()){
            error_msg = "ERROR:customer_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:customer_identifier_does_not_exist");
        } else {
            TreeMap<String, Order> ordersMap = new TreeMap<>();
            List<Order> orderByCustomerID = orderRepository.findOrdersByCustomerIDOrderByOrderID(customerID);
            if (!orderByCustomerID.isEmpty()){
                for(Order order: orderByCustomerID.stream().toList()) {
                    ordersMap.put(order.getOrderID() + "," + order.getStoreName(), order);
                }
            }
            error_msg = "OK:display_completed";
            System.out.println(error_msg);
            return ordersMap;
        }
    }

    @Async(value = "asyncRequestItemThread")
    public void requestItem(ItemLine itemLine) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(itemLine.getStoreName());
        Optional<Order> orderByOrderIDAndStoreName =
                orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(itemLine.getOrderID(), itemLine.getStoreName());
        Optional<Item> itemByStoreNameAndOrderID =
                itemRepository.findItemByItemNameAndAndStoreName(itemLine.getItemName(), itemLine.getStoreName());
        Optional<ItemLine> itemLineByStoreNameAndOrderIDAndItemName =
                itemLineRepository.findItemLinesByStoreNameAndOrderIDAndItemName(itemLine.getStoreName(),
                        itemLine.getOrderID(), itemLine.getItemName());

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(!storeByStoreName.isPresent()){
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else if(!orderByOrderIDAndStoreName.isPresent()) {
            error_msg = "ERROR:order_identifier_does_not_exist";
        } else if (!itemByStoreNameAndOrderID.isPresent()) {
            error_msg = "ERROR:item_identifier_does_not_exist";
        } else if (itemLineByStoreNameAndOrderIDAndItemName.isPresent()) {
            error_msg = "ERROR:item_already_ordered";
        } else {
            Order order = orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(itemLine.getOrderID(), itemLine.getStoreName()).get();
            Customer customer = customerRepository.findCustomerByCustomerID(order.getCustomerID()).get();
            if (customer.getCustomerCredit() < order.getOrderTotalPrice() +
                    itemLine.getQuantity() * itemLine.getUnitPrice()) {
                error_msg = "ERROR:customer_cant_afford_new_item";
            } else {
                Drone drone = droneRepository.findDroneByStoreNameAndDroneID(order.getStoreName(), order.getDroneID()).get();
                Item item = itemRepository.getItemByItemNameAndAndStoreName(itemLine.getItemName(), itemLine.getStoreName());
                if (drone.getRemainingCap() < item.getItemWeight() * itemLine.getQuantity()) {
                    error_msg = "ERROR:drone_cant_carry_new_item";
                } else {
                    // update itemLine
                    itemLine.setLineWeight(item.getItemWeight() * itemLine.getQuantity());
                    itemLine.updateLinePrice();
                    itemLineRepository.save(itemLine);
                    // update order
                    order.updateOrderTotalPrice(itemLine.getQuantity() * itemLine.getUnitPrice());
                    order.updateOrderTotalWeight(item.getItemWeight() * itemLine.getQuantity());
                    orderRepository.save(order);
                    // update drone
                    Integer droneRemainingCap = drone.getRemainingCap() - item.getItemWeight() * itemLine.getQuantity();
                    drone.setRemainingCap(droneRemainingCap);
                    droneRepository.save(drone);
                    error_msg = "OK:change_completed";
                }
            }
        }
        System.out.println(error_msg);
    }

    @Async(value = "asyncPurchaseOrderThread")
    public void purchaseOrder(String storeName, String orderID) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        List<ItemLine> itemLineName = itemLineRepository.findItemLinesByStoreNameAndOrderID(storeName, orderID);

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!storeByStoreName.isPresent()){
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else {
            Optional<Order> orderByOrderIDAndStoreName =
                    orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(orderID, storeName);
            if (!orderByOrderIDAndStoreName.isPresent()) {
                error_msg = "ERROR:order_identifier_does_not_exist";
            } else {
                Order order = orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(orderID, storeName).get();
                if (order.getOrderStatus().equals("Completed")) {
                    error_msg = "ERROR: order_has_been_placed";
                } else {
                    Drone drone = droneRepository.findDroneByStoreNameAndDroneID(storeName, order.getDroneID()).get();
                    if (drone.getPilotID()==null) {
                        error_msg = "ERROR:drone_needs_pilot";
                    } else if (drone.getRemainingDelivery() <= 0) {
                        error_msg = "ERROR:drone_needs_fuel";
                    } else if (itemLineName.isEmpty()) {
                        error_msg = "ERROR:no_item_in_the_order";
                    } else {
                        //1)deduct the cost of order from customer credit
                        Integer orderCost = order.getOrderTotalPrice();
                        Customer customer = customerRepository.findCustomerByCustomerID(order.getCustomerID()).get();
                        customer.updateCustomerCredit(order.getOrderTotalPrice());
                        customerRepository.save(customer);

                        //2)add the cost to store revenue
                        Store store = storeRepository.findStoreByStoreName(storeName).get();
                        store.setRevenue(store.getRevenue() + orderCost);
                        storeRepository.save(store);

                        //3)remaining delivery of the drone be reduced by 1
                        Integer deliveryRemaining = drone.getRemainingDelivery();
                        drone.setRemainingDelivery(deliveryRemaining - 1);
                        droneRepository.save(drone);

                        //4)add pilot experience by 1
                        Pilot pilot = pilotRepository.findPilotByPilotID(drone.getPilotID()).get();
                        Integer numDelivery = pilot.getNumDelivery();
                        pilot.setNumDelivery(numDelivery + 1);
                        pilotRepository.save(pilot);

                        //5)Change order status to complete
                        drone.updateNumOrders(-1);
                        Integer remainingCap = drone.getRemainingCap() + order.getOrderTotalWeight();
                        drone.setRemainingCap(remainingCap);

                        droneRepository.save(drone);
                        order.setOrderStatus("Completed");
                        orderRepository.save(order);
                        //orderRepository.delete(order);
                        error_msg = "OK:change_completed";

                    }
                }

            }
        }
        System.out.println(error_msg);
    }

    @Async(value = "asyncCancelOrderThread")
    public void cancelOrder(String storeName, String orderID){
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else {
            Optional<Order> orderByOrderIDAndStoreName =
                    orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(orderID, storeName);
            if (!orderByOrderIDAndStoreName.isPresent()) {
                error_msg = "ERROR:order_identifier_does_not_exist";
            } else {
                Order order = orderRepository.findOrderByOrderIDAndStoreNameOrderByOrderID(orderID, storeName).get();
                if (order.getOrderStatus().equals("Completed")) {
                    error_msg = "ERROR: order_has_been_placed";
                } else if (order.getOrderStatus().equals("Cancelled")) {
                    error_msg = "ERROR: order_has_already_been_cancelled";
                } else {
                    Drone drone = droneRepository.findDroneByStoreNameAndDroneID(storeName, order.getDroneID()).get();
                    drone.updateNumOrders(-1);
                    Integer remainingCap = drone.getRemainingCap() + order.getOrderTotalWeight();
                    drone.setRemainingCap(remainingCap);
                    order.setOrderStatus("Cancelled");
                    droneRepository.save(drone);
                    orderRepository.save(order);
                    error_msg = "OK:change_completed";
                }
            }
        }
        System.out.println(error_msg);
    }

    /**public List<Order> displayArchiveOrders() {
     error_msg = "OK:display_completed";
     System.out.println(error_msg);
     return orderRepository.findOrdersByDaysInSystemGreaterThan(180);
     }
     */

    public void writeArchivedOrdersToCsv(Writer writer) {
        List<Order> archivedOrders = orderRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("orderID","orderStatus","orderDate","customerID","storeName","droneID","totalPrice","daysInSystem","totalWeight");
            for (Order order : archivedOrders) {
                if (order.getDaysInSystem() > 180) {
                    csvPrinter.printRecord(order.getOrderID(), order.getOrderStatus(), order.getOrderDate(), order.getCustomerID(),
                            order.getStoreName(), order.getDroneID(), order.getOrderTotalPrice(), order.getDaysInSystem(), order.getOrderTotalWeight());
                }
            }
            error_msg = "OK:order_archived";
            System.out.println(error_msg);
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
            error_msg = "ERROR:fail_when_writing_to_csv";
            System.out.println(error_msg);
        }
        orderRepository.deleteByDaysInSystemGreaterThan(180);
    }

    public String getError_msg() {
        return error_msg;
    }

    public StoreSystem getStoreSystem() {
        return storeSystem;
    }
}