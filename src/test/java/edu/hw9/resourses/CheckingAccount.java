package edu.hw9.resourses;

public class CheckingAccount {
    public double balance;
    public String name;
    private final String pincode = "4590";
    public void withdraw(double sum){
        if (sum < 0)
            System.out.println("Ошибка, сумма должна быть > 0");
        else if (sum > balance)
            System.out.println("Ошибка, требуемая сумма больше баланса");
        else {
            balance -= sum;
            System.out.println("Новый баланс равен: " + balance);
        }
    }
    public boolean checkPincode(String Inputpincode){
        if (Inputpincode.equals(pincode))
            return true;
        return false;
    }
}
