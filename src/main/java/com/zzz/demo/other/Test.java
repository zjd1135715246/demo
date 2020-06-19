package com.zzz.demo.other;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int code = i * 16 + j;
                System.out.printf("\u001b[38;5;%dm%-4d", code, code);
            }
            System.out.println("\u001b[0m");
        }
        System.out.println("\u001b[38;5;139m"+"http://localhost:1000/swagger-ui.html");
        System.out.println("\u001b[38;5;139m"+"xyz");
    }
}
