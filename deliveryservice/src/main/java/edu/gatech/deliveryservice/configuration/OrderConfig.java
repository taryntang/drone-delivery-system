package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.jpa.Item;
import edu.gatech.deliveryservice.jpa.ItemLine;
import edu.gatech.deliveryservice.jpa.Order;
import edu.gatech.deliveryservice.repository.ItemLineRepository;
import edu.gatech.deliveryservice.repository.ItemRepository;
import edu.gatech.deliveryservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner commandLineRunner2(OrderRepository orderRepository,
                                         ItemLineRepository itemLineRepository,
                                         ItemRepository itemRepository) {
        return args -> {
            Item i1 = new Item("Wholefoods","apple",1);
            Item i2 = new Item("Wholefoods","pear",1);
            Item i3 = new Item("Publix","milk",1);
            Item i4 = new Item("Publix","bread",1);
            Item i5 = new Item("Target","fries",1);
            Item i6 = new Item("Target","pear",1);
            Item i7 = new Item("Hmart","apple",1);
            Item i8 = new Item("Hmart","bread",1);

            ItemLine l1 = new ItemLine("Wholefoods","order1","apple",4,3);
            ItemLine l2 = new ItemLine("Wholefoods","order2","pear",3,2);
            ItemLine l3 = new ItemLine("Publix","order2","bread",1,4);
            ItemLine l4 = new ItemLine("Publix","order1","milk",1,5);
            ItemLine l5 = new ItemLine("Target","order1","pear",2,1);
            ItemLine l6 = new ItemLine("Target","order2","fries",2,3);
            ItemLine l7 = new ItemLine("Hmart","order4","apple",5,3);
            ItemLine l8 = new ItemLine("Hmart","order1","bread",2,6);

            l1.setLineCost(l1.getUnitPrice()*l1.getQuantity());
            l2.setLineCost(l2.getUnitPrice()*l2.getQuantity());
            l3.setLineCost(l3.getUnitPrice()*l3.getQuantity());
            l4.setLineCost(l4.getUnitPrice()*l4.getQuantity());
            l5.setLineCost(l5.getUnitPrice()*l5.getQuantity());
            l6.setLineCost(l6.getUnitPrice()*l6.getQuantity());
            l7.setLineCost(l7.getUnitPrice()*l7.getQuantity());
            l8.setLineCost(l8.getUnitPrice()*l8.getQuantity());

            l1.setLineWeight(i1.getItemWeight()* l1.getQuantity());
            l2.setLineWeight(i2.getItemWeight()* l2.getQuantity());
            l3.setLineWeight(i3.getItemWeight()* l3.getQuantity());
            l4.setLineWeight(i4.getItemWeight()* l4.getQuantity());
            l5.setLineWeight(i5.getItemWeight()* l5.getQuantity());
            l6.setLineWeight(i6.getItemWeight()* l6.getQuantity());
            l7.setLineWeight(i7.getItemWeight()* l7.getQuantity());
            l8.setLineWeight(i8.getItemWeight()* l8.getQuantity());


            Order o1 = new Order("Wholefoods", "order1", "1", "cust1","2020-12-21","Completed");
            Order o2 = new Order("Wholefoods", "order2", "2", "cust2","2022-03-08", "Completed");
            Order o3 = new Order("Publix", "order1", "1", "cust4","2021-01-09", "Completed");
            Order o4 = new Order("Publix", "order2", "5", "cust2","2022-03-21", "Completed");
            Order o5 = new Order("Target", "order1", "7", "cust1","2020-12-01", "Completed");
            Order o6 = new Order("Target", "order2", "8", "cust2","2021-06-06", "Completed");
            Order o7 = new Order("Hmart", "order4", "8", "cust4","2021-10-08", "Completed");
            Order o8 = new Order("Hmart", "order1", "4", "cust3","2021-11-01", "Cancelled");

            o1.updateOrderTotalPrice(l1.getLineCost());
            o2.updateOrderTotalPrice(l2.getLineCost());
            o3.updateOrderTotalPrice(l3.getLineCost());
            o4.updateOrderTotalPrice(l4.getLineCost());
            o5.updateOrderTotalPrice(l5.getLineCost());
            o6.updateOrderTotalPrice(l6.getLineCost());
            o7.updateOrderTotalPrice(l7.getLineCost());
            o8.updateOrderTotalPrice(l8.getLineCost());

            o1.updateOrderTotalWeight(l1.getLineWeight());
            o2.updateOrderTotalWeight(l2.getLineWeight());
            o3.updateOrderTotalWeight(l3.getLineWeight());
            o4.updateOrderTotalWeight(l4.getLineWeight());
            o5.updateOrderTotalWeight(l5.getLineWeight());
            o6.updateOrderTotalWeight(l6.getLineWeight());
            o7.updateOrderTotalWeight(l7.getLineWeight());
            o8.updateOrderTotalWeight(l8.getLineWeight());

            itemRepository.saveAll(List.of(i1,i2,i3,i4,i5,i6,i7,i8));
            itemLineRepository.saveAll(List.of(l1,l2,l3,l4,l5,l6,l7,l8));
            orderRepository.saveAll(List.of(o1,o2,o3,o4,o5,o6,o7,o8));

        };
    }
}
