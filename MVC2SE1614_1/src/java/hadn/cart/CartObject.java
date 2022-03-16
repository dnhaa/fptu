/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    public void addItemToCart(String id) {
        //1. check available items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
         
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
            
        }
        this.items.put(id, quantity);
    }
//    public void addItemToCart1(String id) {
//        if (this.items == null) {
//            this.items = new HashMap<>();
//        }
//        int quantity = 1;
//        if (this.items.containsKey(id)) {
//            quantity = this.items.get(id) + 1;
//        }
//        this.items.put(id, quantity);
//    }
    public void removeItemFromCart (String id) {
        //1. check available
        if (this.items == null) {
            return;
        }
        //2. check available item
        if (this.items.containsKey(id)){
            this.items.remove(id);
            if (this.items.isEmpty()) { //giúp xử lí code gọn hơn cho phần sau, bằng 0 thành bằng null
                this.items = null;
            }
        }
    }
//    public void removeFromCart(String id) {
//        if (this.items == null) {
//            return;
//        } if (this.items.containsKey(id)){
//            this.items.remove(id);
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }
//        }
//    }
}
