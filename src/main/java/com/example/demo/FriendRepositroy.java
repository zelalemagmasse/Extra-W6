package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface FriendRepositroy extends CrudRepository<Friend,Long>{
Iterable<Friend>findAllByMyFriend(User aFriend) ;
Iterable<Friend> findAllByMyFriendOrderByMyRank(User aFriend);
}
