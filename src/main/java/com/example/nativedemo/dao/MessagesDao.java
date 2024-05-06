package com.example.nativedemo.dao;

import com.example.nativedemo.entity.Messages;
import com.example.nativedemo.mapper.MessagesMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MessagesDao extends MPJBaseServiceImpl<MessagesMapper, Messages> {
}
