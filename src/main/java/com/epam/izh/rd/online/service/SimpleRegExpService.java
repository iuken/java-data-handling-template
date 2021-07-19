package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String filePath = "src/main/resources/sensitive_data.txt";
        String line = null;
        Pattern pattern = Pattern.compile("\\d{4} (\\d{4} \\d{4}) \\d{4}");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            line = line.replaceAll(matcher.group(1), "**** ****");
        }
        return line;
    }


    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String filePath = "src/main/resources/sensitive_data.txt";
        String line = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        line = line.replaceAll("\\Q${payment_amount}\\E", "%s");
        line = line.replaceAll("\\Q${balance}\\E", "%s");
        line = String.format(line, 1, 2);
        return line;
    }
}
