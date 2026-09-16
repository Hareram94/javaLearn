package com.example.minilearn.service;

import org.springframework.stereotype.Service;

@Service
public class TeacherNotification implements Notifications {

    @Override
    public void sentNotification(String notificationMsg) {
        System.out.println(notificationMsg);
    }
}
