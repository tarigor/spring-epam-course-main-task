package com.epam.movieTheater.aspects;

import com.epam.movieTheater.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DiscountAspect extends CommonAspect{

    @Value("${filterOfDiscountProviderByUserId}")
    private String userId;
    Map<String, Integer> birthdayDiscountMap = new HashMap<>();
    @Autowired
    private UserService userService;

    @Pointcut("execution(* com.epam.movieTheater.discount.DiscountStrategy.checkBirthdayDiscount(..))")
    public void counterOfBirthdayDiscount() {
    }

    @AfterReturning("counterOfBirthdayDiscount()")
    public void countOfBirthdayDiscount(JoinPoint joinPoint) {
        if (userId.contentEquals("all")) {
            String discountName = "Birthday discount";
            if (!birthdayDiscountMap.containsKey(discountName)) {
                birthdayDiscountMap.put(discountName, 0);
            }
            birthdayDiscountMap.put(discountName, birthdayDiscountMap.get(discountName) + 1);
        } else {
            String discountNameForCertainUser = "Birthday discount " + userService.getById(((Integer) joinPoint.getArgs()[0])).getName();
            System.out.println("DESC -> " + discountNameForCertainUser);
            if (((Integer) joinPoint.getArgs()[0]).equals(Integer.parseInt(userId))) {
                if (!birthdayDiscountMap.containsKey(discountNameForCertainUser)) {
                    birthdayDiscountMap.put(discountNameForCertainUser, 0);
                }
                birthdayDiscountMap.put(discountNameForCertainUser, birthdayDiscountMap.get(discountNameForCertainUser) + 1);
            }
        }
    }

    public Map<String, Integer> getBirthdayDiscountMap() {
        return birthdayDiscountMap;
    }

}
