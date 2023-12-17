package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {


    @Test
    public void rateEqualsZero(){ // fixбаг0 ноль это не отрицательное число
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                0
        );

        Assertions.assertEquals(0, account.getRate());
    }
    @Test
    public void initialBalanceNotBeNegative() {  // fixбаг1, начальный баланс счета не м.б. отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-1_000, 5_000, 5);
        });
    }

    @Test
    public void creditLimitNotBeNegative() { // fixбаг2, кредитный лимит не м.б. отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -5_000, 5);
        });
    }

    @Test
    public void rateNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new CreditAccount(0, 5_000, -5);
        });
    }

    @Test
    public void shouldPayWhenBalanceIsZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-3_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenBalanceIsPositive() { // fixбаг3, при выставленном начальном балансе рассчет производит как при 0
        CreditAccount account = new CreditAccount(
                4_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenBalanceIsPositive2() { // fixбаг3, при выставленном начальном балансе рассчет производит как при 0
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenBalanceIsPositive3() { // fixбаг3, при выставленном начальном балансе рассчет производит как при 0
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void PayWhenBalanceAndAmountZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void PayWhenAmountZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotPayUnderLimit() {  // fixбаг4, покупка не м.б. больше лимита
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotPayUnderLimit2() {  // fixбаг4, покупка не м.б. больше лимита
        CreditAccount account = new CreditAccount(
                0,
                0,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotPayUnderLimit3() {  // fixбаг4, покупка не м.б. больше лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountEqualsCreditLimit() { // fixбаг5, баланс после покупки может быть равен -лимиту
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(5_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance1() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance2() {   // fixбаг6 при изначальном балансе при пополнении не выдает результат
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance3() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldCalculatePercentWhenBalanceNegative() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(-150, account.yearChange());
    }

    @Test
    public void shouldCalculatePercentWhenBalanceNegative2() { // fixбаг7, не происходит отбрасывание дробной части
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(330);

        Assertions.assertEquals(-49, account.yearChange());
    }

    @Test
    public void shouldCalculatePercentWhenBalanceNegative3() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(200);

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldNotCalculatePercentWhenBalancePositive() { // fixбаг8, при положительном балансе выводится результат
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNotCalculatePercentWhenBalanceZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNotCalculateWhenRateZero() { // fixбаг0 ноль это не отрицательное число
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                0
        );

        account.pay(200);

        Assertions.assertEquals(0, account.yearChange());
    }
}
