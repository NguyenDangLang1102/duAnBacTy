package com.example.demo_get.service.implement;

import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.in.CreatBotIn;
import com.example.demo_get.model.respond.UserRespond;
import com.example.demo_get.repostory.UserRepository;
import com.example.demo_get.service.AutobotService;
import com.github.javafaker.Faker;
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
    private static int timeSleep = 0;
    private static int maxStockVolume = 0;
    private static int minStockVolume = 0;
    private static int amountBot = 1;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRespond insert(AutoBotIn autoBotIn) {
        allBotPrice= autoBotIn.getAllBotPrice();
        timeSleep = autoBotIn.getTimeSleep();
        if (autoBotIn.getMinStockVolume() <= autoBotIn.getMaxStockVolume()){
            maxStockVolume = autoBotIn.getMaxStockVolume();
            minStockVolume = autoBotIn.getMinStockVolume();
            return null;
        }else {
            return new UserRespond("Min ?");
        }
    }
    @Override
    public UserRespond creatBot(CreatBotIn creatBotIn) {
        amountBot = creatBotIn.getAmountBot();
        return null;
    }

    @Override
    @Scheduled(cron = "0 0/15 9-13/45 * * *")
    @Scheduled(fixedDelayString = "PT2s")
    //tao ra cac command chay deu 15/lan tu 9h-18h
    public UserRespond commandAutoBot() {// start va stop thu cong
        if (allBotPrice == 0){
            return null;
        }else {
            for (int i = 1; i <= amountBot; i++) {
            Random random = new Random();
            String[] codeStock = {"cow", "pig", "horse", "rooster", "hen", "dog", "cat", "donkey"};
            Boolean isSale = random.nextBoolean();
            int stockPrice = random.nextInt(11) + 20;// random.nextInt(max + 1 - min) + min
            int stockNumber = random.nextInt(maxStockVolume + 1 - minStockVolume) + minStockVolume;
            int realBotPrice = allBotPrice - stockNumber * stockPrice;//so tien con lai
            Faker faker = new Faker();
            String name = faker.name().fullName();
            if (allBotPrice > (stockNumber * stockPrice)) {
                userRepository.insertCommand(name, codeStock[random.nextInt(8)], isSale, stockPrice, stockNumber);
                allBotPrice = realBotPrice;
                try {
                    Thread.sleep(timeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                allBotPrice = 0;
            }
        }
            return null;
        }
    }




}
