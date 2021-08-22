package com.blank.epicfserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Item {
    private ItemTemplate template;
    private User user;
    private Boolean equipped = false;

    public Item() {

    }

    public Item(ItemTemplate template) {
        this.template = template;
    }

    public ItemTemplate getTemplate() {
        return template;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
    }

    public String getId() {
        return hashCode() + "";
    }
}
