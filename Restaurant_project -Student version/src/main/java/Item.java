import static java.util.Objects.isNull;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() throws itemNotFoundException {
        return name;
    }
    public int getPrice() throws itemNotFoundException {
        if(isNull(price)) {
             throw new itemNotFoundException("price doesn't exist");}
        else {
            return price;
        }
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
}
