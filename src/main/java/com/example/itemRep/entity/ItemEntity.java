package com.example.itemRep.entity;

public class ItemEntity {
    Long id;
   String code;
   String description;
   int isDeleted ;

    public ItemEntity(Long id, String code, String description, int isDeleted) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
