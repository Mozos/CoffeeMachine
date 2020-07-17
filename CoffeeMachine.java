package machine;

import java.util.Scanner;

public final class CoffeeMachine {

    private final Scanner SCANNER = new Scanner(System.in);

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    private boolean use() {
        System.out.println("Write action (buy. fill, take, remaining, exit):");
        String action = SCANNER.nextLine();
        boolean mustQuit = false;
        switch (action.toLowerCase()) {
            case "buy":
                System.out.println();
                buy();
                break;
            case "fill":
                System.out.println();
                fill();
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                System.out.println();
                status();
                break;
            case "exit":
                mustQuit = true;
                break;
            default:
                System.out.println("Unknown action");
                break;
        }
        System.out.println();
        return mustQuit;
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3, cappuccino, back - to main menu:");
        String choice = SCANNER.nextLine();
        switch (choice.toLowerCase()) {
            case "1":
                brew(Coffee.ESPRESSO);
                break;
            case "2":
                brew(Coffee.LATTE);
                break;
            case "3":
                brew(Coffee.CAPPUCCINO);
                break;
            case "back":
                break;
            default:
                System.out.println("Unknown choice");
                break;
        }
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add:");
        setWater(SCANNER.nextInt(), true);
        System.out.println("Write how many ml of milk you want to add:");
        setMilk(SCANNER.nextInt(), true);
        System.out.println("Write how many grams of coffee beans you want to add:");
        setBeans(SCANNER.nextInt(), true);
        System.out.println("Write how many disposables cups of coffee you want to add:");
        setCups(SCANNER.nextInt(), true);
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void status() {
        System.out.printf("The coffee machine has:%n%d of water%n%d of milk%n%d of coffee beans%n" +
                "%d of disposable cups%n%d of money%n", water, milk, beans, cups, money);
    }

    private void brew(Coffee coffee) {
        if (!setWater(coffee.getWater(), false)) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (!setMilk(coffee.getMilk(), false)) {
            System.out.println("Sorry, not enough milk!");
            setWater(coffee.getWater(), true);
            return;
        }
        if (!setBeans(coffee.getBeans(), false)) {
            System.out.println("Sorry, not enough coffee beans!");
            setWater(coffee.getWater(), true);
            setMilk(coffee.getMilk(), true);
            return;
        }
        if (!setCups(1, false)) {
            System.out.println("Sorry, not enough cups!");
            setWater(coffee.getWater(), true);
            setMilk(coffee.getMilk(), true);
            setBeans(coffee.getBeans(), true);
            return;
        }
        addMoney(coffee.getCost());
        System.out.println("I have enough resources, making you a coffee!");
    }

    private boolean setWater(int water, boolean add) {
        if (add) {
            this.water += water;
            return true;
        } else {
            if (this.water < water) {
                return false;
            } else {
                this.water -= water;
                return true;
            }
        }
    }

    private boolean setMilk(int milk, boolean add) {
        if (add) {
            this.milk += milk;
            return true;
        } else {
            if (this.milk < milk) {
                return false;
            } else {
                this.milk -= milk;
                return true;
            }
        }
    }

    private boolean setBeans(int beans, boolean add) {
        if (add) {
            this.beans += beans;
            return true;
        } else {
            if (this.beans < beans) {
                return false;
            } else {
                this.beans -= beans;
                return true;
            }
        }
    }

    private boolean setCups(int cups, boolean add) {
        if (add) {
            this.cups += cups;
            return true;
        } else {
            if (this.cups < cups) {
                return false;
            } else {
                this.cups -= cups;
                return true;
            }
        }
    }

    private void addMoney(int money) {
        this.money += money;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        boolean mustQuit = false;
        while (!mustQuit) {
            mustQuit = coffeeMachine.use();
        }
    }
}
