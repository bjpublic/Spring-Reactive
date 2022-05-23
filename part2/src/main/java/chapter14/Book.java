package chapter14;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private String bookName;
    private String authName;
    private String penName;
    private int price;
}
