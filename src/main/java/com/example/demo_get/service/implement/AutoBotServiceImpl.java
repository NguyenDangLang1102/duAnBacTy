package com.example.demo_get.service.implement;

import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.respond.UserRespond;
import com.example.demo_get.repostory.UserRepository;
import com.example.demo_get.service.AutobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.Random;

@Component
@Transactional

@Configuration
@EnableScheduling

public class AutoBotServiceImpl implements AutobotService {
    private static int allBotPrice = 0;

   @Autowired
    private UserRepository userRepository;


    @Override
    public UserRespond insert(AutoBotIn autoBotIn) {
        allBotPrice= autoBotIn.getAllBotPrice();
        return null;
    }

    @Override
    @Scheduled(cron = "0 0/15 9-13/45 * * *")
    //tao ra cac command chay deu 15/lan tu 9h-18h
    public UserRespond commandAutoBot() {// start va stop thu cong
        if (allBotPrice == 0){
            return null;
        }else {
            Random random = new Random();
            String[] userName = {"Trung", "Lang", "Minh", "Huy"};
            Boolean isSale = random.nextBoolean();
            int stockPrice = random.nextInt(11) + 20;
            int stockNumber = random.nextInt(51);
            int realBotPrice = allBotPrice - stockNumber * stockPrice;//so tien con lai
            allBotPrice = realBotPrice;
            System.out.println("gggggg   " + allBotPrice);
            if (allBotPrice > (stockNumber * stockPrice)) {
                userRepository.insertCommand(userName[random.nextInt(4)], "cow", isSale, stockPrice, stockNumber);
                return null;
            } else {

                return null;
            }
        }
    }




}
