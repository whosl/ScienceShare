package buaa.group6.scienceshare.service;


import buaa.group6.scienceshare.Result.Result;

public interface MailService {
    Result sendPin(String toEmail);
}
