package com.example.nativedemo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nativedemo.enmus.MessageType;
import com.example.nativedemo.entity.Messages;
import com.example.nativedemo.mapper.MessagesMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.io.Serializable;

@SpringBootApplication(proxyBeanMethods = false)
public class NativeDemoApplication implements Serializable {

  public static void main(String[] args) {
    SpringApplication.run(NativeDemoApplication.class, args);
  }

  @Bean
  @Order(1)
  ApplicationRunner run(MyMapper mapper) {
    return args -> {
      mapper.insert(new Message(null, "Hello World on run!"));
      Message message = mapper.select(1);
      System.out.println(message);
    };
  }

  @Bean
  @Order(2)
  ApplicationRunner runWithXmlMapper(MyXmlMapper mapper) {
    return args -> {
      mapper.insert(new Message(null, "Hello World! on runWithXmlMapper"));
      Message message = mapper.select(2);
      System.out.println(message);
    };
  }

  @Bean
  @Order(3)
  ApplicationRunner runWithMybatisPlus(MessagesMapper messagesMapper) {
    return args -> {
      System.out.println("------------演示insert-----------------");
      Messages messages = new Messages(null, "Hello MybatisPlus", MessageType.VOICE);
      messagesMapper.insert(messages);
      System.out.println("------------演示select-----------------");
      System.out.println("simple query:" + messagesMapper.selectById(messages.getId()));
      System.out.println("------------演示update-----------------");
      messages.setMessageType(MessageType.TEXT);
      messagesMapper.updateById(messages);
      System.out.println("------------演示page-----------------");
      Page<Messages> page = messagesMapper.selectPage(new Page<>(1, 2), Wrappers.emptyWrapper());
      System.out.println("total:" + page.getTotal() + ",records" + page.getRecords());
      System.out.println("------------演示lambda-----------------");
      System.out.println("lambda query:" + messagesMapper.selectOne(Wrappers.<Messages>lambdaQuery().select(Messages::getMessage).eq(Messages::getId, messages.getId())));
    };
  }

  @Mapper
  interface MyMapper {

    @Insert("""
          INSERT INTO messages (message)
            VALUES (#{message})
        """)
    void insert(Message message);

    @Select("""
          SELECT
            id
            ,message
          FROM
            messages
          WHERE
            id = #{id}
        """)
    Message select(Integer id);

  }

  @Mapper
  interface MyXmlMapper {
    void insert(Message message);

    Message select(Integer id);
  }

  record Message(Integer id, String message) {
  }

}
