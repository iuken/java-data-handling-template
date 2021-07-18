package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.Main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        MathContext mc = new MathContext(range, RoundingMode.HALF_UP);
        BigDecimal bigDecimal = new BigDecimal((double)a/b, mc);
        return bigDecimal;
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger bi = BigInteger.valueOf(0);
        int i = 0;
        while (i <= range){
            bi = bi.add(BigInteger.valueOf(1));
            if (bi.isProbablePrime(20)){
                i++;
            }
        }
        return bi;
    }
}
