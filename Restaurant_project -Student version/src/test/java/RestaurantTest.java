import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void initialize_restaurant_var_fortesting(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //Test cases for showOrder Value method in Restaurant
    @Test
    public void showordervalue_should_return_correct_order_value() throws itemNotFoundException {
        assertEquals(388,restaurant.showOrderValue(restaurant.getMenu()));
    }
    @Test
    public void showordervalue_should_retrun_incorrect_order_value_when_price_isworng () throws itemNotFoundException {
        assertFalse(300,restaurant.showOrderValue(restaurant.getMenu()));
    }
    @Test
    public void showordervalue_should_throw_exception_if_item_not_found () throws itemNotFoundException {
        assertThrow(itemNotFoundException,restaurant.showOrderValue(restaurant.getMenu()));
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        LocalTime mockedTime = LocalTime.parse("15:31:00");
        Restaurant mockedRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(mockedTime);
        System.out.println(mockedRestaurant.isRestaurantOpen());
        assertTrue(mockedRestaurant.isRestaurantOpen());
    }
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        LocalTime mockedTime = LocalTime.parse("23:30:00");
        Restaurant mockedRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(mockedTime);
        System.out.println(mockedRestaurant.isRestaurantOpen());
        assertFalse(mockedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
