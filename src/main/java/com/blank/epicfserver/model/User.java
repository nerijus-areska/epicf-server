package com.blank.epicfserver.model;

import com.blank.epicfserver.payload.UserPayload;
import com.blank.epicfserver.service.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

//@Entity
//@Table(	name = "users",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "email")
//        })
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidEmail
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private Integer hp = 200;
    private Integer coins = 0;
    private Integer energy = 100;

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
    private List<Item> items = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "user",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL
//    )
//    @MapKeyEnumerated(EnumType.STRING)
//    @MapKeyColumn(name="slot")
    private Map<ItemSlot, Item> equippedItems = new HashMap<>();

    public Map<ItemSlot, Item> getEquippedItems() {
        return equippedItems;
    }

    public UserPayload genPayload() {
        return new UserPayload(hp, energy);
    }

    public void addItem(Item item) {
        items.add(item);
        item.setUser(this);
    }

    public void equipUserItem(Item item) {
        if(item.getEquipped() == false) {
            Item currentItem = equippedItems.get(item.getTemplate().getItemSlot());
            if (currentItem != null) {
                currentItem.setEquipped(false);
            }
            item.setEquipped(true);
            equippedItems.put(item.getTemplate().getItemSlot(), item);
        } else {
            item.setEquipped(false);
            equippedItems.remove(item.getTemplate().getItemSlot());
        }
    }

    public Optional<Item> findItemById(String userItemId) {
        return items.stream()
                .filter(item -> userItemId.equals(item.getId()))
                .findAny();
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
