package com.example.itemRep.dto;

public class ItemDto {
    private Long id;
    private String code;
    private String description;
   private int isDeleted ;

    public ItemDto() {
    }

    public ItemDto(Long id, String code, String description, int isDeleted) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
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
}
