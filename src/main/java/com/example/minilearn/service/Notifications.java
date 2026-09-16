package com.example.minilearn.service;

import org.springframework.stereotype.Service;

@Service
public interface Notifications {

    public void sentNotification(String notificationMsg);
}
