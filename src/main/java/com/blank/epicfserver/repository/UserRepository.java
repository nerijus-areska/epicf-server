package com.blank.epicfserver.repository;

import com.blank.epicfserver.model.Item;
import com.blank.epicfserver.model.ItemTemplate;
import com.blank.epicfserver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Boolean existsByEmail(String email);
//
//    Optional<User> findByEmail(String email);
//}

@Repository
public class UserRepository {

    private static Map<Long, User> userMap = new HashMap<>();
    static {
        User user = new User();
        user.setId(1L);

        Item item = ItemTemplate.itemMap.get(ItemTemplate.ITEM_MILITARY_HELMET).createItem();
        user.addItem(item);

        userMap.put(1L, user);
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    public User save(User user) {
        return user;
    }

    public boolean existsByEmail(String email) {
        return false;
    }

    public Optional<User> findByEmail(String username) {
        return Optional.empty();
    }


//    Boolean existsByEmail(String email);
//
//    Optional<User> findByEmail(String email);
}