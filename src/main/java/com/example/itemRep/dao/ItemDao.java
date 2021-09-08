package com.example.itemRep.dao;

import com.example.itemRep.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao {
      public List<Long> save (List<ItemEntity> items);
      public List<Long> saveMerge(List<ItemEntity> items);
}
