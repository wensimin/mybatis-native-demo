package com.example.nativedemo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nativedemo.dao.MessagesDao;
import com.example.nativedemo.enmus.MessageType;
import com.example.nativedemo.entity.Messages;
import com.example.nativedemo.mapper.MessagesMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication(proxyBeanMethods = false)
public class NativeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NativeDemoApplication.class, args);
    }

    @Bean
    @Order(3)
    ApplicationRunner runWithMybatisPlus(MessagesMapper messagesMapper, MessagesDao messagesDao) {
        return args -> {
            System.out.println("------------演示insert-----------------");
            Messages messages = new Messages(null, null, "Hello MybatisPlus", MessageType.VOICE, null, null);
            messagesDao.save(messages);
            System.out.println("------------演示select-----------------");
            System.out.println("simple query:" + messagesMapper.selectById(messages.getId()));
            System.out.println("query wrapper:" + messagesMapper.selectOne(Wrappers.<Messages>query().eq("id", 1)));
            System.out.println("------------演示update-----------------");
            messages.setMessageType(MessageType.TEXT);
            messagesMapper.updateById(messages);
            messagesMapper.update(messages, Wrappers.<Messages>update().eq("id", 1L));
            System.out.println("------------演示delete-----------------");
            messagesMapper.delete(Wrappers.<Messages>update().eq("id", 0L));
//            messagesMapper.deleteById(messages);
            System.out.println("------------演示page-----------------");
            Page<Messages> page = messagesMapper.selectPage(new Page<>(1, 2), Wrappers.emptyWrapper());
            System.out.println("total:" + page.getTotal() + ",records" + page.getRecords());
            System.out.println("------------演示lambda-----------------");
            System.out.println("lambda query:" + messagesMapper.selectOne(Wrappers.<Messages>lambdaQuery().select(Messages::getMessage).eq(Messages::getId, messages.getId())));
            System.out.println("lambda update:" + messagesMapper.update(messages, Wrappers.<Messages>lambdaUpdate().eq(Messages::getId, 1L)));
            System.out.println("lambda delete:" + messagesMapper.delete(Wrappers.<Messages>lambdaUpdate().eq(Messages::getId, 1L)));
        };
    }

}
