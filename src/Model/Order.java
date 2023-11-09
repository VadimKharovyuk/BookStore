package Model;

public class Order {
    private long id;
    private long customerId; //кому продали
    private long employeeID; //кто продал
    private long[] books; //список  книг которые  продали

    public Order(long id, long customerId, long employeeID, long[] books) {
        this.id = id;
        this.customerId = customerId;
        this.employeeID = employeeID;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public long[] getBooks() {
        return books;
    }

    public void setBooks(long[] books) {
        this.books = books;
    }
}
