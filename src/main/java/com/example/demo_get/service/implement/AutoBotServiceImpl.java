package com.example.demo_get.service.implement;

import com.example.demo_get.mapper.AccountMapper;
import com.example.demo_get.model.dto.AccountDto;
import com.example.demo_get.model.entity.AccountEntity;
import com.example.demo_get.model.in.AutoBotIn;
import com.example.demo_get.model.respond.CommandRespond;
import com.example.demo_get.repostory.AccountRepository;
import com.example.demo_get.repostory.CommandRepository;
import com.example.demo_get.service.AutobotService;
import com.example.demo_get.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Transactional

@Configuration
@EnableScheduling

public class AutoBotServiceImpl implements AutobotService {

    private static int timeSleep = 0;
    private static int maxStockVolume = 0;
    private static int minStockVolume = 0;
    private static int fromIndex = 0;
    private static int toIndex = 0;

    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommandService commandService;

    @Override
    public CommandRespond insert(AutoBotIn autoBotIn) {
        if (autoBotIn.getMinStockVolume() <= autoBotIn.getMaxStockVolume()){
            timeSleep = autoBotIn.getTimeSleep();
            fromIndex = autoBotIn.getFromIndex();
            toIndex = autoBotIn.getToIndex();
            maxStockVolume = autoBotIn.getMaxStockVolume();
            minStockVolume = autoBotIn.getMinStockVolume();
            return null;
        }else {
            return new CommandRespond("Min ?");
        }
    }

    @Override
    @Scheduled(cron = "* * 9-11 * * 1-5")
    @Scheduled(cron = "* 0-30 11 * * 1-5")
    @Scheduled(cron = "* * 11-15 * * 1-5")
    @Scheduled(fixedDelayString = "PT1s")
    //tao ra cac command chay deu 15/lan tu 9h-15h
    public CommandRespond commandAutoBot() {// start va stop thu cong
        if ( toIndex  == 0){
            return new CommandRespond("if");
        }else {
            List<AccountEntity> list = accountRepository.getAccount();
            List<AccountDto> listDto = list.stream().map(AccountMapper::mapEntity).collect(Collectors.toList());
            List<AccountDto> listAccount = listDto.subList(fromIndex, toIndex);//0-5
            System.out.println("vao day1");
                for (int i = 0; i < listAccount.size() ; i++) {
                    Random random = new Random();
                    String[] codeStock = {"cow", "pig", "horse", "rooster", "hen", "dog", "cat", "donkey"};
                    Boolean isSale = random.nextBoolean();
                    int stockPrice = random.nextInt(11) + 20;// random.nextInt(max + 1 - min) + min
                    int stockNumber = random.nextInt(maxStockVolume + 1 - minStockVolume) + minStockVolume;

                    AccountDto account = listAccount.get(i);
                    if (account.getBotPrice() > (stockNumber * stockPrice) ) {
                        commandRepository.insertCommand(account.getNameUser(), codeStock[random.nextInt(8)], isSale, stockPrice, stockNumber);
                        //  CommandIn commandIn = new CommandIn(name, codeStock[random.nextInt(8)], stockPrice, isSale, stockNumber);// ham khop lenh
                        //    commandService.create(commandIn);
                        int realBotPrice = account.getBotPrice() - stockNumber * stockPrice;//so tien con lai
                        String realBotPriceString =String.valueOf(realBotPrice);// ep kieu theo DB
                        accountRepository.updateUser(account.getIdBot(),realBotPriceString);//update gia tien
                        try {
                            Thread.sleep(timeSleep);//tg nghi moi lenh
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                    return new CommandRespond("else");
        }
    }
}
