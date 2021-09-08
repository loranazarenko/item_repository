package com.example.itemRep.service;

import com.example.itemRep.dao.ItemDao;
import com.example.itemRep.dto.ItemDto;
import com.example.itemRep.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    ItemMapper itemMapper;

    @Transactional
    public List<Long> save (List<ItemDto> itemDtos){
       return itemDao.save(this.itemMapper.map(itemDtos));
    }


    //@Transactional(isolation = Isolation.SERIALIZABLE)
    @Transactional
    public List<Long> saveMerge(List<ItemDto> itemDtos){
        return itemDao.saveMerge(this.itemMapper.map(itemDtos));
    }


}
