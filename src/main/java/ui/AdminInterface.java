/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import logic.*;
import domain.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lenovo
 */
  public class AdminInterface implements UserInterface{
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private Scanner scanner;
    private UserAccountManag manag;
    private Stock stock;

    public AdminInterface(Scanner scanner){
        this.scanner=scanner;
        this.manag=new UserAccountManag();
        this.stock=new Stock();
    }

    public void accessPrompt(){
        while(true){
            System.out.println("Welcome.");
            System.out.println("[R]: Register");
            System.out.println("[L]: Login");
            System.out.println("[X]: Quit"); 
            String answer = scanner.nextLine();
            if("X".equalsIgnoreCase(answer)){
                break;
            }
            if("R".equalsIgnoreCase(answer)){
                System.out.println("Enter username: ");
                String username = scanner.nextLine();
                while(username.isBlank()){
                    System.out.println("Try again!");
                    username = scanner.nextLine();
                }
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                while(password.isBlank()){
                    System.out.println("Try again!");
                    password = scanner.nextLine();
                }
                System.out.println("Confirm password: ");
                String confirmedPassword = scanner.nextLine();
                while(!confirmedPassword.equals(password)){
                    System.out.println("You entered two different passwords. Try again! Or press X to return to homePage");
                    confirmedPassword = scanner.nextLine();
                    if(confirmedPassword.equalsIgnoreCase("X")){
                        this.accessPrompt();
                        break;
                    }
                }
                System.out.println("Enter your email: ");
                String email = scanner.nextLine();
                while(!isValidEmail(email)){
                    System.out.println("Your email is invalid. Try again!");
                    email = scanner.nextLine();
                }
                if(this.manag.isRegistrated(username,password)){
                    System.out.println("You're already registrated! You need to login!");
                    this.loginCommand();
                }else{
                    this.manag.registerUser(username, password, email); 
                    if(this.manag.isRegistrated(username, password)){
                        System.out.println("Successfully Registrated!");
                        if(username.startsWith("admin@")){
                            this.admin_toHomePage_orToProductManagePage();
                        }else{
                            this.viewer_toHomePage_orToProductManagePage();
                        }
                    }
                }
                
            }
            if("L".equalsIgnoreCase(answer)){
                this.loginCommand();
            }
        }


    }

    public void viewerProductManagementPanel(){
        System.out.println("Welcome to our platform!");
        while(true){
            System.out.println("[1]: View Products.");
            System.out.println("[2]: Filter Products.");
            System.out.println("[3]: Search products by category: ");
            System.out.println("[4]: Quit");
            int answer = Integer.valueOf(scanner.nextLine());
            if(1==answer){
                this.stock.viewStockProducts();
            }else if(2==answer){
                this.filterProductPrompt();
            }else if(3==answer){
                System.out.println("Enter the product category: ");
                this.stock.searchProductsByCategory(scanner.nextLine());
            }else{
                break;
            }
        }
    }

    public void AdminProductManagementPrompt(){
        System.out.println("Welcome to the admin panel!");
        while(true){   
            System.out.println("[1]: Add Product to Stock.");
            System.out.println("[2]: Remove Product");
            System.out.println("[3]: Update Product Details.");
            System.out.println("[4]: View Products.");
            System.out.println("[5]: Filter Products.");
            System.out.println("[6]: Sort Products.");
            System.out.println("[7]: View Stock Statistics.");
            System.out.println("[8]: Search products by category: ");
            System.out.println("[9]: Number of products of a given category: ");
            System.out.println("[10]: Quit");
            int answer = Integer.valueOf(scanner.nextLine());
            if(10==answer){
                break;
            }
            if(1==answer){
                System.out.println("Enter productId: ");
                String productId = scanner.nextLine();
                System.out.println("Enter product name: ");
                String productName = scanner.nextLine();
                this.stock.addProductToTheStock(productId, productName);
            }else if(2==answer){
                System.out.println("[N]: I know the product name.");
                System.out.println("[I]: I know the product Id.");
                String x = scanner.nextLine();
                if("N".equalsIgnoreCase(x)){
                    System.out.println("Enter the product name: ");
                    this.stock.removeProductFromStockByName(scanner.nextLine());
                }else{
                    System.out.println("Enter the product Id: ");
                    this.stock.removeProductFromStockById(scanner.nextLine());
                }
            }else if(3==answer){
                this.updateProductFirstPrompt();
            }else if(4==answer){
                this.stock.viewStockProducts();
            }else if(5==answer){
                this.filterProductPrompt();
            }else if(6==answer){
                this.sortProductsPrompt();
            }else if(7==answer){
                System.out.println(this.stock.numberOfStockProducts());
                System.out.println(this.stock.averageCostOfStockProducts());
                System.out.println(this.stock.averagePriceOfStockProducts());
                System.out.println(this.stock.averageProfitOfStockProducts());               
            }else if(8==answer){
                System.out.println("Enter product category: ");
                String productCategory = scanner.nextLine();
                this.stock.searchProductsByCategory(productCategory);
            }else{
                System.out.println("Enter the category name: ");
                this.stock.numberOfProductsOfCategory(scanner.nextLine());
            }
        }
    }


    public void start(){
        this.accessPrompt();
    }

    public void loginCommand(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        while(!this.manag.doesUserNameExists(username)){
            System.out.println("Wrong username. Try again!");
            System.out.println("[X]: return to Home Page.");
            username = scanner.nextLine();
            if(username.equalsIgnoreCase("X")){
                this.accessPrompt();
                break;
            }
        }
        if(this.manag.doesUserNameExists(username)){
            System.out.println("Enter password (3 consecutive wrong password input will send you to the home page!): ");
            String password = scanner.nextLine();
            int count=0;
            while(!this.manag.doesPasswordExists(password) && count<4){
                count++;
               System.out.println("Wrong password. Try again!");
               password = scanner.nextLine();
            }
            if(count<4){
                if(this.manag.isRegistrated(username, password) && username.startsWith("admin@")){
                    this.admin_toHomePage_orToProductManagePage();
                }else{
                    this.viewer_toHomePage_orToProductManagePage();
                }
            }else{
                this.accessPrompt();
            }
        }
        
    }

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void updateProductSecondPrompt(Product product){
        System.out.println("[1]: Update Product Name.");
        System.out.println("[2]: Update Product Price.");
        System.out.println("[3]: Update Product Quantity.");
        System.out.println("[4]: Update Product Category.");
        int b = Integer.valueOf(scanner.nextLine());
        if(1==b){
            System.out.println("Enter the new name: ");
            product.changeProductName(scanner.nextLine());
            this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
        }else if(2==b){
            System.out.println("Enter the new price");
            product.changeProductPrice(Double.valueOf(scanner.nextLine()));
            this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
        }else if(3==b){
            System.out.println("[C]: Clear the product quantity.");
            System.out.println("[I]: Increase product quantity.");
            System.out.println("[IA]: Increase product quantity by amount.");
            System.out.println("[D]: Decrease product quantity.");
            System.out.println("[DA]: Decrease product quantity by amount.");
            String response = scanner.nextLine();
            if("C".equalsIgnoreCase(response)){
                product.clearProductQuantity();
                this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
            }else if("I".equalsIgnoreCase(response)){
                product.increaseProductQuantity();
                this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
            }else if("D".equalsIgnoreCase(response)){
                product.decreaseProductQuantity();
                this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
            }else if("IA".equalsIgnoreCase(response)){
                System.out.println("Enter the amount:");
                int amount = Integer.valueOf(scanner.nextLine());
                product.increaseProductQuantityBy(amount);
                this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
            }else{
                System.out.println("Enter the amount:");
                int amount = Integer.valueOf(scanner.nextLine());
                product.decreaseProductQuantityBy(amount);
                this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
            }
        }else{
            System.out.println("Enter the new category: ");
            product.setProductCategory(scanner.nextLine());
            this.return_ToUpdateMenu_OrToManagMenu_OrToHomePage();
        }
    }

    public void updateProductFirstPrompt(){
        System.out.println("[N]: I know product name.");
        System.out.println("[I]: I know product Id.");
        String a = scanner.nextLine();
        if("N".equalsIgnoreCase(a)){
            System.out.println("Enter the product name: ");
            Product product = this.stock.getProductForName(scanner.nextLine());
            this.updateProductSecondPrompt(product);

        }else{
            System.out.println("Enter the product Id: ");
            Product product = this.stock.getProductForId(scanner.nextLine());
            this.updateProductSecondPrompt(product);
        }
    }

    public void filterProductPrompt(){
        while(true){
            System.out.println("[P]: Filter products by price.");
            System.out.println("[Q]: Filter products by Quantity.");
            System.out.println("[T]: List product whose quantity is below the stock treeshold.");
            System.out.println("[X]: Quit.");
            String answer = scanner.nextLine();
            if("P".equalsIgnoreCase(answer)){
                System.out.println("Minumum price: ");
                double minPrice = Double.valueOf(scanner.nextLine());
                System.out.println("Maximum price: ");
                double maxPrice = Double.valueOf(scanner.nextLine());
                this.stock.filterProductsByPrice(minPrice, maxPrice);
            }else if("Q".equalsIgnoreCase(answer)){
                 System.out.println("Minumum quantity: ");
                int minQty = Integer.valueOf(scanner.nextLine());
                System.out.println("Maximum price: ");
                int maxQty = Integer.valueOf(scanner.nextLine());
                this.stock.filterProductsByQuantity(minQty, maxQty);
            }else if("x".equalsIgnoreCase(answer)){
                break;
            }else{
                this.stock.listProductsBelowTreeshold();
            }
        }
    }

    public void sortProductsPrompt(){
        while(true){
            System.out.println("[PQ]: Sort products by price and quantity.");
            System.out.println("[QP]: Sort products by quantity and price");
            System.out.println("[A]: Sort products alphabetically.");
            System.out.println("[C]: Sort products by cost.");
            System.out.println("[Pt]: Sort products by profit.");
            System.out.println("[X]: Quit.");
            String response = scanner.nextLine();
            if("PQ".equalsIgnoreCase(response)){
                this.stock.sortProductsByPriceQty();
            }else if("QP".equalsIgnoreCase(response)){
                this.stock.sortProductsByQtyPrice();
            }else if("A".equalsIgnoreCase(response)){
                this.stock.sortProductsAlphabetically();
            }else if("X".equalsIgnoreCase(response)){
                break;
            }else if("C".equalsIgnoreCase(response)){
                this.stock.sortProductsByCost();
            }else{
                this.stock.sortProductsByProfit();
            }
        }
    }

    public void return_ToUpdateMenu_OrToManagMenu_OrToHomePage(){
        System.out.println("[H]: Return to Home Page.");
        System.out.println("[M]: Return to Product Management Menu.");
        System.out.println("[U]: Return to Update Product Detail Menu.");
        String answer = scanner.nextLine();
        if("H".equalsIgnoreCase(answer)){
            this.accessPrompt();
        }else if("M".equalsIgnoreCase(answer)){
            this.AdminProductManagementPrompt();
        }else if("U".equalsIgnoreCase(answer)){
            this.updateProductFirstPrompt();
        }
    }

    public void admin_toHomePage_orToProductManagePage(){
        System.out.println("[H]: return to Home Page.");
        System.out.println("[M]: Go to Product management page.");
        String answer = scanner.nextLine();
        if("H".equalsIgnoreCase(answer)){
            this.accessPrompt();
        }else{
            this.AdminProductManagementPrompt();
        }
    }

    public void viewer_toHomePage_orToProductManagePage(){
        System.out.println("[H]: return to Home Page.");
        System.out.println("[M]: Go to Product management page.");
        String answer = scanner.nextLine();
        if("H".equalsIgnoreCase(answer)){
            this.accessPrompt();
        }else{
            this.viewerProductManagementPanel();
        }
    }
}
