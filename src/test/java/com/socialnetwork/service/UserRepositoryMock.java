package com.socialnetwork.service;

import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepositoryMock<T> implements UserRepository {

    private int countCheckIfExistsCalls = 0;
    private HashMap<String, Boolean> checkIfExistsWillReturn = new HashMap<>();
    private int countGetCalls = 0;
    private HashMap<String, User> getWillReturn = new HashMap<>();

    private List<User> updateArguments = new ArrayList<>();

    public void checkIfExistsWillReturn(String userName, boolean returnValue) {
        checkIfExistsWillReturn.put(userName, returnValue);
    }

    @Override
    public boolean checkIfExists(String userName) {
        countCheckIfExistsCalls++;
        Boolean resultValue = checkIfExistsWillReturn.get(userName);
        if (resultValue == null) {
            throw new IllegalArgumentException("Passed " + userName +
                    " but expecting one of " + checkIfExistsWillReturn.keySet());
        }
        return resultValue;
    }

    public int getCountCheckIfExistsCalls() {
        return countCheckIfExistsCalls;
    }

    @Override
    public void add(User user) {

    }

    public void getWillReturn(String username, User returnValue) {
        getWillReturn.put(username, returnValue);
    }

    @Override
    public User get(String username) {
        countGetCalls++;
        User user = getWillReturn.get(username);
        if (user == null) {
            throw new IllegalArgumentException("Passed " + username +
                    " but expecting one of " + getWillReturn.keySet());
        }
        return user;
    }

    public int getCountGetCalls() {
        return countGetCalls;
    }

    @Override
    public void update(User user) {
        updateArguments.add(user);
    }

    public User getUpdateArgument(int index) {
        return updateArguments.get(index);
    }

    public int getCountUpdateCalls() {
        return updateArguments.size();
    }

    public boolean updateWasCalledWith(User user) {
        return updateArguments.contains(user);
    }
}
