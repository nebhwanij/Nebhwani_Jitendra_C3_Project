import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime;
        currentTime = getCurrentTime();
        if(currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)){ return true;}
        else {return false;}

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException {
        boolean itemfound = false;
        for (Item item : menu) {
            if (item.getName().equals(itemName)) {
                itemfound = true;
                return item;
            }
        }
          if(itemfound == false){throw new itemNotFoundException(itemName);
        }
        return null;
    }
    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    // Method to calculate order value for items list argument from the Restaurant.
    public int showOrderValue(List<Item> listofItems) throws itemValueIsNegativeException,itemNotFoundException {
        int orderValue = 0;
        int i = 0;
        while (i < listofItems.size()) {
            orderValue = orderValue + listofItems.get(i).getPrice();

            if (listofItems.get(i).getPrice() < 0) {
                throw new itemValueIsNegativeException(listofItems.get(i).getName());
            }
            i++;
        }
        return orderValue;
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
