package edu.gatech.deliveryservice.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StoreSystemViewController {

    @GetMapping("/admin_platform")
    public String admin() {
        return "admin_platform";
    }

    @GetMapping("/make_store")
    public String makeStore() {
        return "make_store";
    }

    @GetMapping("/display_stores")
    public String displayStore() {
        return "display_stores";
    }

    @GetMapping("/make_drone")
    public String makeDrone() {
        return "make_drone";
    }

    @GetMapping("/display_drones")
    public String displayDrone() {
        return "display_drones";
    }

    @GetMapping("/make_pilot")
    public String makePilot() {
        return "make_pilot";
    }

    @GetMapping("/display_pilots")
    public String displayPilot() {
        return "display_pilots";
    }

    @GetMapping("/sell_item")
    public String sellItem() {
        return "sell_item";
    }

    @GetMapping("/display_items")
    public String displayItems() {
        return "display_items";
    }

    @GetMapping("/fly_drone")
    public String flyDrone() {
        return "fly_drone";
    }
}
