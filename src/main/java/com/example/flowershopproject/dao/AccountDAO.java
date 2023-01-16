package com.example.flowershopproject.dao;

import com.example.flowershopproject.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Account findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, userName);
    }
    public void updateAccount(String userName, String email, String phoneNumber, String addres, String name, String lastName, String city) {
        Session session = this.sessionFactory.getCurrentSession();
        Account account = findAccount(userName);
        if(!email.isEmpty()){
            account.setEmail(email);
        }
        if(!phoneNumber.isEmpty()){
            account.setPhone(phoneNumber);
        }
        if(!addres.isEmpty()){
            account.setUserAddress(addres);
        }
        if(!name.isEmpty()){
            account.setFirstName(name);
        }
        if(!lastName.isEmpty()){
            account.setLastName(lastName);
        }
        if(!city.isEmpty()){
            account.setUserCity(city);
        }
        session.update(account);
    }

}
