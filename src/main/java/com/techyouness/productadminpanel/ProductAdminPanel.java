/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.techyouness.productadminpanel;

import java.util.Scanner;
import ui.AdminInterface;
import ui.UserInterface;

/**
 *
 * @author lenovo
 */
public class ProductAdminPanel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInterface userIn = new AdminInterface(scanner);
        userIn.accessPrompt();
    }
}
