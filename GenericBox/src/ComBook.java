public class ComBook extends Book{
    private  String title;
    private  int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ComBook(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
