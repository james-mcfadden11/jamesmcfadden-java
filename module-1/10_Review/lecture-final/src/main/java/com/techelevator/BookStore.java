package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private List<Book> inventory;
    private List<Person> staff;
    private List<Person> customers;

    public BookStore() {
        inventory = new ArrayList<>();
        staff = new ArrayList<>();
        customers = new ArrayList<>();

        fillInventory();
    }

    private void fillInventory() {

        Person andreS = new Person("Andrzej", "Sapkowski");
        Book theLastWish = new Book("The Last Wish", andreS);
        Book blood = new Book("Blood of Elves", andreS);

        inventory.add(theLastWish);
        inventory.add(blood);

//        blood.leaveReview("It's great!");
    }


}



/*


  Sections for books
  Book
   - Title
   - Genre
   - Author
   - Price
   - NewOrUsed
   - Signed
   - LocationOnShelf

   leaveAReview()

  Person
   - First Name
   - Last Name
   - Occupation
   - RewardsCard?

  BookStore
   - Inventory
   - CoffeeShop
   - Cash
   - Staff
   - Customers

   leaveAReview()
   checkout()
   arrangeBooks()

  CoffeeShop
   - Coffee



 */