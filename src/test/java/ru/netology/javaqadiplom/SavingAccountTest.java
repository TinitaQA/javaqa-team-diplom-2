package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { //баг1 не прибавляет значение к балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalanceNearMax() { //баг1 не прибавляет значение к балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(7_999);

        Assertions.assertEquals(9_999, account.getBalance());
    }

    @Test
    public void shouldAddThanMaxBalance() { //баг1 не прибавляет значение к балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() { // тест прошел, но из-за того что метод add корявый
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_001);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddMoreThanZeroAmount() { //баг1 не прибавляет значение к балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(1);

        Assertions.assertEquals(2_000 + 1, account.getBalance());
    }

    @Test
    public void shouldNotPayLessThanMinBalance() { // баг2 остаток счета не может быть меньше минимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(4_001);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldPayThanMinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(4_000);

        Assertions.assertEquals(5_000 - 4_000, account.getBalance());
    }

    @Test
    public void shouldPayMoreThanMinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_999);

        Assertions.assertEquals(5_000 - 3_999, account.getBalance());

    }

    @Test
    public void shouldPayThanBetweenMinMaxBalance() {
        SavingAccount account = new SavingAccount(
                7_680,
                1_000,
                10_000,
                5
        );

        account.pay(4_325);

        Assertions.assertEquals(7_680 - 4_325, account.getBalance());
        System.out.println(7680 - 4325);

    }

    @Test
    public void shouldNotPayThanNegativeBalance() { // баг3 баланс не может быть отрицательным
        SavingAccount account = new SavingAccount(
                7_680,
                1_000,
                10_000,
                5
        );

        account.pay(8_325);

        Assertions.assertEquals(7_680, account.getBalance());


    }

    @Test
    public void initialBalanceCanNotBeNegative() { //баг4 при отрицательном балансе не выкидывается исключение вида IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(-13_000, 5_000, 13_000, 13);
        });
    }

    @Test
    public void minBalanceCanNotBeNegative() { // баг5 при отрицательном минимальном балансе не выкидывается исключение вида IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(13_000, -13_000, 26_000, 13);
        });
    }
    @Test
    public void maxBalanceCanNotBeNegative() {  // баг6 при отрицательном максимальном балансе не выкидывается исключение вида IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_000, 1_000, -13_000, 13);
        });
    }

    @Test
    public void rateCanNotBeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_000, 1_000, 13_000, -13);
        });
    }
    @Test
    public void rateCanBeZero() { // баг7 ставка может быть равна нулю, а исключение вида IllegalArgumentException не выкидывается
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_000, 1_000, 13_000, 0);
        });
    }
    @Test
    public void minBalanceCanNotBeMoreThanMaxBalance() { // баг8 минимальный баланс не может быть больше максимального и исключение вида IllegalArgumentException не выкидывается
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_000, 1_000, 13_000, 0);
        });
    }

    @Test
    public void shouldCalculatePercentWhenBalancePositive() {
        SavingAccount account = new SavingAccount(
                200,
                100,
                13_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(30, account.yearChange());
    }

    @Test
    public void shouldCalculatePercentWhenBalancePositive400() { // сначала надо править метод add
        SavingAccount account = new SavingAccount(
                100,
                100,
                13_000,
                15
        );

        account.add(300);

        Assertions.assertEquals(60, account.yearChange());
    }

    @Test
    public void shouldCalculateIntegerPercent() { // баг9 не отбрасывается дробная часть
        SavingAccount account = new SavingAccount(
                110,
                500,
                13_000,
                15
        );

        account.add(468);

        Assertions.assertEquals(70, account.yearChange());
    }


//    @Test
//    public void shouldNotCalculatePercentWhenBalanceNegative() { // баг9 так как остаток счета не может быть отрицательным как и ставка, то процент не можеть быть отрицательным
//        SavingAccount account = new SavingAccount(
//                1_000,
//                5_000,
//                15_000,
//                10
//
//        );
//        account.pay(1200);
//
//        Assertions.assertEquals(0, account.yearChange());
//    }


}
