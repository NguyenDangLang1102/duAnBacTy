package com.example.demo_get.service.implement;


import com.example.demo_get.model.in.BuffBotPrice;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.model.respond.CommandRespond;
import com.example.demo_get.repostory.AccountRepository;
import com.example.demo_get.service.AccountBotService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.Locale;

@Component
@Transactional

public class AccountBotServiceImpl implements AccountBotService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CommandRespond createBot(CreatBotIn creatBotIn) {
        for (int i = 0; i < creatBotIn.getAmountBot() ; i++) {
            Faker faker = new Faker(new Locale("vi"));
            String name = faker.name().fullName();
            accountRepository.insertAccount(name, "0", "bot");
        }
        return new  CommandRespond("thanh cong");
    }

    @Override
    public CommandRespond updateUser(Integer Id, BuffBotPrice buffBotPrice) {
        accountRepository.updateUser(Id,buffBotPrice.getStockPrice());
        return new CommandRespond("update thanh cong");
    }
}
