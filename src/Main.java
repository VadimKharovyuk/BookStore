import Model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    public static void main(String[] args) {


        //продавцы
        employees.add(new Employee(1,"Томашевський Женя",32));
        employees.add(new Employee(2,"Демидов Олександер",35));
        employees.add(new Employee(3,"Кравчук Олександер",31));
        //покупатели
        customers.add(new Customer(1,"Губарьков Сергей",30));
        customers.add(new Customer(2,"Андрусенко Николай",39));
        customers.add(new Customer(3,"Корж Вячеслав",22));
        customers.add(new Customer(4,"Луценко Сергей",38));
        customers.add(new Customer(5,"Пидкопай Сергей",21));
        //книги
        books.add(new Book(1,"Алгебра","Дудь",350, BookGenre.ART));
        books.add(new Book(2,"От ждуна до мидла за 5 часов","Ютуб",999,BookGenre.PROGRAMMING));
        books.add(new Book(3,"Колобок","Шевченко",200, BookGenre.ART));
        books.add(new Book(4,"Не беспокойся","Карнеги",400, BookGenre.PSYCHOLOGY));
        books.add(new Book(5,"Совершенный код","Макконев Стив",150, BookGenre.PROGRAMMING));
        books.add(new Book(6,"Кобзар","Шевченко",650, BookGenre.ART));
        books.add(new Book(7,"Чистый код","Шмит",280, BookGenre.PROGRAMMING));
        //заказы
        orders.add(new Order(1,1,1,new long[]{2,5,7}));
        orders.add(new Order(3,2,2,new long[]{1,4}));
        orders.add(new Order(2,3,1,new long[]{1,2,5,47}));
        orders.add(new Order(4,1,3,new long[]{2,3,5,7}));

        String bookInfo = String.format("Общее количество проданных книг %d на сумму %f",getCountSoldBooks(),getAllPriceSoldBooks());
        System.out.println(bookInfo);








    }


    ///получить книгу по Id
    public static Book getBookById(long id){
        Book current = null;                 //пустая переменнная
        for (Book book :books){
            if (book.getId()==id){   //если у книги id совпадает с id который мы  ищем (в параметы метода)
                current= book;
                break;
            }
        }
        return current;
    }
    //количество проданных книг
    public static int getCountSoldBooks(){
        int count = 0;
        for (Order order:orders){
            count += order.getBooks().length;
        }
        return count;
    }
    //общая стоимомть одного заказа
    public static double getPriceSoldBooksInOrder(Order order){
        double price = 0;
        for (long bookId : order.getBooks()){
            Book book = getBookById(bookId);//
            if (book!= null)
                price += book.getPrice();
        }
        return price;
    }
    //общая стоимость всез заказов
    public static double getAllPriceSoldBooks(){
        double price = 0;
        for (Order order :orders){
            price += getPriceSoldBooksInOrder(order) ;
        }
        return  price;
    }
}

