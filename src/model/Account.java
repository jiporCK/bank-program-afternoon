package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    private Integer id;
    private String ownerName;
    private Double balance;

    public Account(String ownerName, Double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

}
