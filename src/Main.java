import Model.*;

import java.util.ArrayList;
import java.util.HashMap;

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
        books.add(new Book(2,"От ждуна до мидла за 5 часов","Ютуб",500,BookGenre.PROGRAMMING));
        books.add(new Book(3,"Колобок","Шевченко",200, BookGenre.ART));
        books.add(new Book(4,"Не беспокойся","Карнеги",400, BookGenre.PSYCHOLOGY));
        books.add(new Book(5,"Совершенный код","Макконев Стив",150, BookGenre.PROGRAMMING));
        books.add(new Book(6,"Кобзар","Шевченко",650, BookGenre.ART));
        books.add(new Book(7,"Чистый код","Шмит",300, BookGenre.PROGRAMMING));
        //заказы
        orders.add(new Order(1,1,1,new long[]{2,5,7,4}));
        orders.add(new Order(3,2,2,new long[]{1,4}));
        orders.add(new Order(2,3,1,new long[]{1,2,5,47}));
        orders.add(new Order(4,1,3,new long[]{2,3,5,7}));


        //количество проданых книг + сумма проданных книг
        String bookInfo = String.format("Общее количество проданных книг %d на сумму %f",getCountSoldBooks(),getAllPriceSoldBooks());
        System.out.println(bookInfo);
        System.out.println("_____________________");

        // на какую суму продал каждый продавец
        for (Employee employee:employees){
            System.out.println(employee.getName()+" продал "+getProfitByEmploee(employee.getId()));
        }
        //кол проданых книг по жанру + стоимость
        System.out.println("_____________________");
        ArrayList<BookAdditional> soldBooksCount =getCountOfSoldBooksByGenre();
        HashMap<BookGenre,Double> soldBookPrice = getPriceOfSoldBooksByGenre();
        String soldBoolStr= "По жанру %s продано %d книг общей стоимостью %f";
        for (BookAdditional additional: soldBooksCount){
            double price = soldBookPrice.get(additional.getGenre());
            System.out.println(
                    String.format(
                            soldBoolStr,
                            additional.getGenre().name(),additional.getCount(),price
                    )
            );
        }




    }
    //колчество проданых книг по жанрам
    public static ArrayList<BookAdditional> getCountOfSoldBooksByGenre(){
        ArrayList<BookAdditional> result = new ArrayList<>();
        int countArt = 0, countPr =0,countPs = 0;
        for (Order order: orders){
            countArt +=getCountOfSoldBooksByGenre(order,BookGenre.ART);
            countPs += getCountOfSoldBooksByGenre(order,BookGenre.PSYCHOLOGY);
            countPr += getCountOfSoldBooksByGenre(order,BookGenre.PROGRAMMING);
        }
        result.add(new BookAdditional(BookGenre.ART,countArt));
        result.add(new BookAdditional(BookGenre.PROGRAMMING,countPr));
        result.add(new BookAdditional(BookGenre.PSYCHOLOGY,countPs));

        return result;
    }
    public static double getPriceOfSoldBooksByGende(Order order,BookGenre genre){
        double price = 0;

        for (long bookId : order.getBooks()){
            Book book =getBookById(bookId);
            if (book != null && book.getGenre() == genre)
            price += book.getPrice();
        }
        return  price;
    }
    public static HashMap<BookGenre,Double> getPriceOfSoldBooksByGenre(){
        HashMap<BookGenre, Double> result = new HashMap<>();
        double priceArt = 0, pricePr= 0, pricePs = 0;
        for (Order order: orders){
            priceArt+= getPriceOfSoldBooksByGende(order,BookGenre.ART);
            pricePr += getPriceOfSoldBooksByGende(order,BookGenre.PROGRAMMING);
            pricePs += getPriceOfSoldBooksByGende(order,BookGenre.PSYCHOLOGY);
        }
        result.put(BookGenre.ART,priceArt);
        result.put(BookGenre.PSYCHOLOGY,pricePs);
        result.put(BookGenre.PROGRAMMING,pricePr);
        return result;
    }

    public static int getCountOfSoldBooksByGenre(Order order ,BookGenre genre){
        int count = 0;
        for (long bookId :order.getBooks()){
            Book book = getBookById(bookId);
            if (book != null && book.getGenre()==genre)
                count++;
        }return  count;
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
    //общее стоимость проданого товара для продавца
    public static Profit getProfitByEmploee(long emploeeId){
        int count = 0; double price = 0;
        for (Order order: orders){
            if (order.getEmployeeID()==emploeeId){
                price +=getPriceSoldBooksInOrder(order);
                count += order.getBooks().length;
            }
        }return  new Profit(count,price);
    }



}

