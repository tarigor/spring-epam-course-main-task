package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.TicketCsv;
import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.service.impl.IUserService;
import com.epam.movieTheater.utility.BeanToCsvBuilderUtility;
import com.epam.movieTheater.utility.CsvToBeanBuilderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UserService implements IUserService {

    private final String FILE_PATH = "src/main/resources/users/users.csv";

    @Autowired
    private Scanner scanner;
    @Autowired
    private User user;
    @Resource
    private CsvToBeanBuilderUtility csvToBeanBuilderUtility;
    @Resource
    private BeanToCsvBuilderUtility beanToCsvBuilderUtility;
    private final Integer userId;
    private List<User> usersList;

    public UserService() {
        userId = 0;
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
//        id,name,email,birthday
        System.out.println("Input a name of user:");
        String inputName = scanner.nextLine();
        System.out.println("Input an email of user:");
        String inputEmail = scanner.nextLine();
        System.out.println("Input a birthday date (format DD.MM.YYYY):");
        String inputBirthday = scanner.nextLine();
        user = user.createUser(toIncrementUserId(), inputName, inputEmail, inputBirthday, "");
        System.out.println(user.toString());

        beanToCsvBuilderUtility.writeListToCsv(FILE_PATH, user, User.class, true);
    }

    private Integer toIncrementUserId() {
        List<User> list = getAll();
        if (list.size() == 1) {
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
//        return getAll().stream().filter(u -> u.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public List<User> getAll() {
        try {
            usersList = csvToBeanBuilderUtility.getListOfBeansFromCsv(FILE_PATH, User.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        usersList.forEach(System.out::println);
        return usersList;
    }

    public void updateUserHistory(TicketCsv ticketCsv) {
        List<User> userList = getAll();
        for (User user : userList) {
            if (user.getId().equals(ticketCsv.getUserId())) {
                user.setOrderHistory(user.getOrderHistory() + ":" + ticketCsv.toString());
            }
        }
        beanToCsvBuilderUtility.writeListToCsv(FILE_PATH, null, User.class, false);
        for (User user : userList) {
            beanToCsvBuilderUtility.writeListToCsv(FILE_PATH, user, User.class, true);
        }
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
