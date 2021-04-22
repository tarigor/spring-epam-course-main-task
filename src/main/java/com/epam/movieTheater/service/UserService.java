package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.TicketCsv;
import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.service.impl.IUserService;
import com.epam.movieTheater.utility.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

@Component
public class UserService implements IUserService {

    private final String SQL_QUERY_INSERT_TO_USER_TABLE = "INSERT INTO t_users (id, userName, userEmail, userBirthday, userOrderHistory) VALUES (?,?,?,?,?)";
    private final String FILE_PATH = "src/main/resources/users/users.csv";
    private final String USERS_TABLE_SQL_QUERY = "t_users (" + "id INT NOT NULL PRIMARY KEY," +
            "userName VARCHAR(255)," +
            "userEmail VARCHAR(255)," +
            "userBirthday VARCHAR(255)," +
            "userOrderHistory VARCHAR(255)" + ")";
    @Autowired
    private Scanner scanner;
    private final Integer userId;
    @Autowired
    private DatabaseController databaseController;

    public UserService() {
        userId = 0;
    }

    @PostConstruct
    private void checkIfUsersTableExists() {
        databaseController.checkIfTableExists(USERS_TABLE_SQL_QUERY, "USERS");
    }

    @Override
    public User getUserByEmail(String emailName) {
        List<User> list = getAll();
        for (User user : list) {
            if (user.getEmail().contains(emailName)) {
                return user;
            }
        }
        System.out.println("there is no such user whit email contains:" + emailName);
        return null;
    }

    @Override
    public void save() {

        System.out.println("Input a name of user:");
        String inputName = scanner.nextLine();
        System.out.println("Input an email of user:");
        String inputEmail = scanner.nextLine();
        System.out.println("Input a birthday date (format DD.MM.YYYY):");
        String inputBirthday = scanner.nextLine();

        databaseController.updateTable(SQL_QUERY_INSERT_TO_USER_TABLE, String.valueOf(toIncrementUserId()), inputName, inputEmail, inputBirthday, "");

        List<User> list = databaseController.getUsersTable();
        System.out.println("Saved to DB user with id " + list.get(list.size() - 1).getId());
    }

    private Integer toIncrementUserId() {
        List<User> list = getAll();
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1).getId() + 1;
    }

    @Override
    public void remove() {

    }

    @Override
    public User getById(Integer id) {
        List<User> list = getAll();
        if (list.iterator().next().getId().equals(id))
            list.stream().filter(u -> u.getId().equals(id)).findAny();
        for (User user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        System.out.println("there is no such user with id:" + id);
        return null;
    }

    @Override
    public List<User> getAll() {
        return databaseController.getUsersTable();
    }

    public void updateUserHistory(TicketCsv ticketCsv) {
        String updatedOrderHistory;
        if (getById(ticketCsv.getUserId()).getOrderHistory().contentEquals("")) {
            updatedOrderHistory = ticketCsv.toString();
        } else {
            updatedOrderHistory = getById(ticketCsv.getUserId()).getOrderHistory() + ":" + ticketCsv.toString();
        }
        databaseController.updateTable("UPDATE T_USERS SET USERORDERHISTORY='" + updatedOrderHistory + "' WHERE ID=" + ticketCsv.getUserId());
    }

    public Object connect(String userEmail, String password){
        for (User userFromList:getAll()) {
            if(userEmail.contentEquals(userFromList.getEmail())){
                userFromList.setConnected(true);
                return userFromList;
            }
        }
        System.out.println("There is no such user with typed email");
        return false;
    }
}
