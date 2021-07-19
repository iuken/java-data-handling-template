package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replaceAll(remove, "");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     * <p>
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        if (text.equals(null) || text.equals("")) {
            return false;
        }
        return (text.charAt(text.length() - 1) == 63);
    }

    /**
     * Реализовать функционал соединения переданных строк.
     * <p>
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder result = new StringBuilder();
        for (String element : elements) {
            result.append(element);
        }
        return result.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        text = text.toLowerCase();
        char[] chars = text.toCharArray();
        for (int i = 1; i < chars.length; i += 2) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        text = String.valueOf(chars);
        return text;
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     * <p>
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     * <p>
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        string = string.replaceAll(" ", "");
        char[] chars = string.toCharArray();
        int checklimit = chars.length / 2;
        if (string.equals(null) || string.equals("")) {
            return false;
        }
        for (int i = 0; i < checklimit; i++) {
            char c1 = Character.toLowerCase(chars[i]);
            char c2 = Character.toLowerCase(chars[chars.length - i - 1]);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
}
