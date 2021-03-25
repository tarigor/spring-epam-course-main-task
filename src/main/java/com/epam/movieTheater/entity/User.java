package com.epam.movieTheater.entity;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Qualifier("user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String email;
    @CsvBindByPosition(position = 3)
    private String birthday;
    @CsvBindByPosition(position = 4)
    private String orderHistory;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private UserOrderHistory userOrderHistory;

    public User(Integer id, String name, String email, String birthday, String orderHistory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.orderHistory = orderHistory;
    }

    @Resource
    public void setUserOrderHistory(UserOrderHistory userOrderHistory) {
        this.userOrderHistory = userOrderHistory;
    }

    public User createUser(Integer id, String name, String email, String birthday, String orderHistory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.orderHistory = orderHistory;
        return new User(id, name, email, birthday, orderHistory);
    }

    public List<UserOrderHistory> getUserOrderHistory(User user) {
        List<UserOrderHistory> userOrderHistoryList = new ArrayList<>();
        for (String orderHistory : user.getOrderHistory().split(":")) {
            userOrderHistoryList.add(new UserOrderHistory(
                    Integer.parseInt(orderHistory.split(",")[0]),
                    Integer.parseInt(orderHistory.split(",")[1]),
                    LocalDate.parse(orderHistory.split(",")[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            ));
            System.out.println("u: " + userOrderHistoryList.toString());
        }
        return userOrderHistoryList;
    }

}