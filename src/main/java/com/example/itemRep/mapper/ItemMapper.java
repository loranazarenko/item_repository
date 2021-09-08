package com.example.itemRep.mapper;

import com.example.itemRep.dto.ItemDto;
import com.example.itemRep.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

 //@Mapping(target = "id", expression = "java(itemDto.getId().equals(null) ? null : itemDto.getId())")
 ItemEntity itemDtotoItemEntity(ItemDto itemDto);
 List<ItemEntity> map (List<ItemDto> itemDtos);

}
