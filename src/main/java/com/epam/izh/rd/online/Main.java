package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import com.epam.izh.rd.online.service.SimpleDateService;
import com.epam.izh.rd.online.service.SimpleRegExpService;

import sun.util.resources.LocaleData;

import java.io.File;
import java.math.BigInteger;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        SimpleDateService simpleDateService = new SimpleDateService();
//        LocalDate localDate = LocalDate.now();
//        System.out.println(simpleDateService.parseDate(localDate));
        SimpleRegExpService simpleRegExpService = new SimpleRegExpService();
        String s = simpleRegExpService.maskSensitiveData();
    }
}
