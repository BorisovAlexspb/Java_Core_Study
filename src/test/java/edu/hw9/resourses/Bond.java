package edu.hw9.resourses;

public class Bond {
    public String name;
    public double balance = 100;
    public double rate;   // rate - процентная ставка
    public int term, monthsRemaining;  // term - срок

    public void setTermAndRate(int t) {  // назначаем процентную ставку в зависимости от месяца
        if (t >= 0 && t < 12)
            rate = 0.005;
        else if (t >= 12 && t < 24)
            rate = 0.010;
        else if (t >= 24 && t < 36)
            rate = 0.015;
        else if (t >= 36 && t < 48)
            rate = 0.020;
        else if (t >= 48 && t <= 60)
            rate = 0.025;
        else {
            System.out.println("Недопустимый срок");
            t = 0;
        }
        term = t;
        monthsRemaining = t;
    }

    public void earnInterest() {
        if (monthsRemaining > 0) {
            balance += balance * rate / 12;
            monthsRemaining--;
            System.out.println("Баланс: $" + balance);
            System.out.println("Ставка: " + rate);
            System.out.println("Остаток месяцев:" + monthsRemaining);
        } else {
            System.out.println("Срок платежа облигации наступил");
        }
    }
}

