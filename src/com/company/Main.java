package com.company;

/** Alisher */

import java.util.Random;

public class Main {
    public static int[] heroesHealth = {250, 260, 270, 150, };
    public static int[] heroesDamage = {20, 15, 10, };
    public static int medicHeals = 5;
    public static String[] heroesAttackType = {"Physical", "Magical,", "Kinetic"};

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("___________________________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health " + heroesHealth[3]);
        System.out.println("___________________________");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossDefenceType.equals(heroesAttackType[i])) {
                Random r = new Random();
                int coef = r.nextInt(8) + 2;
                if (bossHealth - heroesDamage[i] * coef < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i] * coef;
                }
                System.out.println(heroesAttackType[i] + " Critically hit " + heroesDamage[i] * coef);
            } else {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage ;
                }
                if (heroesHealth[3] <= 0){
                } else {
                    heroesHealth[i] = heroesHealth[i] + medicHeals;
                }
            }
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes WON!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss WON!!!");
            return true;
        }
        return false;
    }
}
