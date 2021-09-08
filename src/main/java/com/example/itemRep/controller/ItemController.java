package com.example.itemRep.controller;

import com.example.itemRep.dto.ItemDto;
import com.example.itemRep.dto.ResponseDto;
import com.example.itemRep.service.ItemService;
import com.example.itemRep.wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("/item")
@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> saveItem(@RequestBody List<ItemDto> itemDtos) {
        List<Long> ids =itemService.save(itemDtos);
        ResponseWrapper<Long> wrapper = new ResponseWrapper<>();
        return  wrapper.wrapToResponse(() -> wrapper.wrap(ids, null), null);


    }

    @PostMapping("/saveMerge")
    public ResponseEntity<ResponseDto> saveMerge(@RequestBody List<ItemDto> itemDtos){
        ResponseWrapper <Long> wrapper =new ResponseWrapper<>();
        List<Long> ids = itemService.saveMerge(itemDtos);
        return wrapper.wrapToResponse(()->wrapper.wrap(ids,null),null);


    }



}
