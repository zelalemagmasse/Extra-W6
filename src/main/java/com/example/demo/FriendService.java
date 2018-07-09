package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private User thisUser;
    @Autowired
    UserRepository user;

    @Autowired
    FriendRepositroy friends;

    public Iterable<Friend> getMyFriends(Authentication myDetails){
        return friends.findAllByMyFriendOrderByRankOfFriend(user.findByUsername(myDetails.getName()));
    }

    public Iterable<Friend>rankMyFriends(Authentication myDetails){
        return friends.findAllByMyFriendOrderByRankOfFriend(user.findByUsername(myDetails.getName()));

    }

    public Friend save(Friend aFriend, Authentication authentication){
        thisUser=user.findByUsername(authentication.getName());
        aFriend.setMyFriend(thisUser);
        return friends.save(aFriend);

    }
}
