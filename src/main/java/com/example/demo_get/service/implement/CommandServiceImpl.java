package com.example.demo_get.service.implement;

import com.example.demo_get.mapper.CommandMapper;
import com.example.demo_get.model.dto.CommandDto;
import com.example.demo_get.model.entity.CommandEntity;
import com.example.demo_get.model.in.CommandIn;
import com.example.demo_get.model.respond.CommandRespond;
import com.example.demo_get.repostory.CommandRepository;
import com.example.demo_get.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
@Component
@Transactional

public class CommandServiceImpl implements CommandService {



    @Autowired
    private CommandRepository commandRepository;

    @Override
    public CommandRespond getAll() {
        List<CommandEntity>list = commandRepository.getCommand();
        List<CommandDto>listDto=list.stream().map(CommandMapper::mapEntity).collect(Collectors.toList());
        return new CommandRespond(listDto, "Success");
    }


    @Override
    public CommandRespond create(CommandIn CommandIn) {
        System.out.println("vao Command"+ CommandIn);

//        userRepository.insertCommand(UserIn.getNameUser(),UserIn.getNameStock(),UserIn.getIsSale(),UserIn.getStockPrice(), UserIn.getStockNumber());
        List<CommandEntity>list = commandRepository.getCommand();
        List<CommandDto>listDto=list.stream().map(CommandMapper::mapEntity).collect(Collectors.toList());

        if (CommandIn.getIsSale() == true){        //Bán
            List<CommandDto> matchingObjects = listDto.stream()
                    .filter(p-> p.getNameStock().startsWith(CommandIn.getNameStock())
                    && p.getIsSale() != CommandIn.getIsSale()
                    && p.getStockPrice() >= CommandIn.getStockPrice())
                    .collect(Collectors.toList());

            if (matchingObjects.size() == 0){
                commandRepository.insertCommand(CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(), CommandIn.getStockNumber());
                return new CommandRespond( "Đặt lệnh bán thành công");
            }
//            Comparator comparator = Comparator.comparing(UserDto::getStockPrice );
//            UserDto maxObject1  =  matchingObjects.stream()
//                    .max(Comparator.comparing(comparator).get();

            CommandDto maxObject1  = matchingObjects.stream().max(Comparator.comparing(CommandDto::getStockPrice )).get();
            System.out.println(maxObject1);
            
            List<CommandDto> maxPriceList = new ArrayList<>();
            for (CommandDto user: matchingObjects
                 ) {
                if(user.getStockPrice()==maxObject1.getStockPrice()){
                    maxPriceList.add(user);
                }
            }
            CommandDto soonTime  = maxPriceList.stream().min(Comparator.comparing(CommandDto::getTimeCreate )).get();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Integer realStockNumberSale;
            if(CommandIn.getStockNumber() > soonTime.getStockNumber()){
                realStockNumberSale = CommandIn.getStockNumber() - soonTime.getStockNumber();
                commandRepository.deleteTable(soonTime.getId());
                commandRepository.insertCommand(CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(), realStockNumberSale);
                commandRepository.insertCommandComplete( soonTime.getNameUser(),soonTime.getNameStock(),soonTime.getIsSale(), soonTime.getStockPrice(), CommandIn.getStockPrice() ,  soonTime.getStockNumber(),   soonTime.getStockNumber(), soonTime.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),soonTime.getStockPrice() ,  CommandIn.getStockNumber() , realStockNumberSale , timestamp);
            }
            else if(CommandIn.getStockNumber() < soonTime.getStockNumber()) {  ///sai th nay
                realStockNumberSale = soonTime.getStockNumber() - CommandIn.getStockNumber();
//              UserEntity userEntity = userRepository.getById(soonTime.getId());
//              userEntity.setStockNumber(realStockNumberSale);nameUser = "lang10"
//              userRepository.save(userEntity);
               commandRepository.updateTable(soonTime.getId(), realStockNumberSale);
                commandRepository.insertCommandComplete( soonTime.getNameUser(),soonTime.getNameStock(),soonTime.getIsSale(), soonTime.getStockPrice(), CommandIn.getStockPrice() ,  soonTime.getStockNumber(),   CommandIn.getStockNumber(), soonTime.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),soonTime.getStockPrice() ,  CommandIn.getStockNumber() ,  CommandIn.getStockNumber() , timestamp);
            }else {
                commandRepository.deleteById(soonTime.getId());
                commandRepository.insertCommandComplete( soonTime.getNameUser(),soonTime.getNameStock(),soonTime.getIsSale(), soonTime.getStockPrice(), CommandIn.getStockPrice() ,  soonTime.getStockNumber(),   soonTime.getStockNumber(), soonTime.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),soonTime.getStockPrice() ,  CommandIn.getStockNumber() ,  CommandIn.getStockNumber() , timestamp);
            }
            return new CommandRespond(soonTime, "Khớp lệnh bán");
        }else {                                 //Mua
            List<CommandDto> matchingObjects = listDto.stream()
                    .filter(p-> p.getNameStock().startsWith(CommandIn.getNameStock())
                    && p.getIsSale() != CommandIn.getIsSale()
                    && p.getStockPrice() <= CommandIn.getStockPrice())
                    .collect(Collectors.toList());

            if (matchingObjects.size() == 0){
                commandRepository.insertCommand(CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(), CommandIn.getStockNumber());
                return new CommandRespond( "Đặt lệnh mua thành công");
            }

            CommandDto minObject1  =  matchingObjects.stream()
                    .min(Comparator.comparing(CommandDto::getStockPrice )
                            .thenComparing(CommandDto::getTimeCreate))
                    .get();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Integer realStockNumber;
            if(CommandIn.getStockNumber() > minObject1.getStockNumber()){
                realStockNumber = CommandIn.getStockNumber() - minObject1.getStockNumber();
                commandRepository.deleteTable(minObject1.getId());
                commandRepository.insertCommand(CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(), realStockNumber);
                commandRepository.insertCommandComplete( minObject1.getNameUser(),minObject1.getNameStock(),minObject1.getIsSale(), minObject1.getStockPrice(), CommandIn.getStockPrice() ,  minObject1.getStockNumber(),   minObject1.getStockNumber(), minObject1.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),minObject1.getStockPrice() ,  CommandIn.getStockNumber() , realStockNumber , timestamp);
            }
            else if(CommandIn.getStockNumber() < minObject1.getStockNumber()) {
                realStockNumber = minObject1.getStockNumber() - CommandIn.getStockNumber();
                CommandEntity commandEntity = commandRepository.getById(minObject1.getId());
                commandEntity.setStockNumber(realStockNumber);
                commandRepository.save(commandEntity);
//                userRepository.updateById(maxObject1.getId(), realStockNumber);
                commandRepository.insertCommandComplete( minObject1.getNameUser(),minObject1.getNameStock(),minObject1.getIsSale(), minObject1.getStockPrice(), CommandIn.getStockPrice() ,  minObject1.getStockNumber(),   CommandIn.getStockNumber(), minObject1.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),minObject1.getStockPrice() ,  CommandIn.getStockNumber() ,  CommandIn.getStockNumber() , timestamp);
            }else {
//                userRepository.deleteById(minObject1.getId());
                commandRepository.insertCommandComplete( minObject1.getNameUser(),minObject1.getNameStock(),minObject1.getIsSale(), minObject1.getStockPrice(), CommandIn.getStockPrice() ,  minObject1.getStockNumber(),   CommandIn.getStockNumber(), minObject1.getTimeCreate());
                commandRepository.insertCommandComplete( CommandIn.getNameUser(), CommandIn.getNameStock(), CommandIn.getIsSale(), CommandIn.getStockPrice(),minObject1.getStockPrice() ,  CommandIn.getStockNumber() ,  CommandIn.getStockNumber() , timestamp);
            }
            return new CommandRespond(minObject1, "Khớp lệnh mua");
        }
    }
    @Override
    public CommandRespond delete(Integer Id) {
        commandRepository.deleteTable(Id);
        return new CommandRespond(0, "delete lệnh mua");
    }
    @Override
    public CommandRespond update(Integer Id, CommandIn commandIn) {
        commandRepository.updateTable(Id, commandIn.getStockNumber());
        return new CommandRespond(0, "update lệnh mua");
    }

    @Override
    public CommandRespond getById(Integer p_ID) {
       CommandEntity list = commandRepository.getbyid(p_ID);
       return new CommandRespond(list, "Success");
    }


}



