package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Employee;

public interface AuthenticationService {
    Employee register(String email, String password);
}
