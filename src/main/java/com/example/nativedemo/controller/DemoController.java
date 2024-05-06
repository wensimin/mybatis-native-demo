package com.example.nativedemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nativedemo.dao.MessagesDao;
import com.example.nativedemo.dao.SysUserDao;
import com.example.nativedemo.enmus.MessageType;
import com.example.nativedemo.entity.MessageDto;
import com.example.nativedemo.entity.Messages;
import com.example.nativedemo.entity.SysUser;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DemoController {
    private final MessagesDao messagesDao;
    private final SysUserDao sysUserDao;

    public DemoController(MessagesDao messagesDao, SysUserDao sysUserDao) {
        this.messagesDao = messagesDao;
        this.sysUserDao = sysUserDao;
    }

    @Transactional
    @PostMapping
    public Messages add() {
        var user = new SysUser(null, "newUser");
        sysUserDao.save(user);
        var message = new Messages(null, user.getId(), "new message", MessageType.TEXT, null, null);
        messagesDao.save(message);
        return message;
    }

    @DeleteMapping
    public boolean delete(Long id) {
        return this.messagesDao.removeById(id);
    }

    @GetMapping
    public Page<MessageDto> listAll() {
        MPJLambdaWrapper<Messages> query = new MPJLambdaWrapper<Messages>().leftJoin(SysUser.class, SysUser::getId, Messages::getUserId)
                .selectAll(Messages.class)
                .selectAs(SysUser::getUsername, MessageDto::getUserName);
        return this.messagesDao.selectJoinListPage(new Page<>(), MessageDto.class, query);
    }

}
