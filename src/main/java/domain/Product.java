/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author lenovo
 */
public class Product implements Comparable<Product>{
    private String productId;
    private String productName;
    private String productCategory;
    private double productPrice;
    private int productQuantity;
    private double productCost;
    
    public Product(String id, String name){
        if(id==null|| name==null){
            throw new IllegalArgumentException("Either the id or the name you entered is null.");
        }
        this.productId=id;
        this.productName=name;
        this.productPrice=0.0;
        this.productCategory=null;
        this.productQuantity=0;
        this.productCost=0.0;
    }

    public double getProductCost(){
        return this.productCost;
    }

    public void setProductCost(double newProductCost){
        if(newProductCost<=0){
            throw new IllegalArgumentException();
        }
        this.productCost=newProductCost;
    }


    public String getProductName(){
        return this.productName;
    }

    public void changeProductName(String newProductName){
        if(newProductName==null){
            throw new IllegalArgumentException();
        }
        this.productName=newProductName;
    }

    public String getProductId(){
        return this.productId;
    }

    public String getProductCategory(){
        return this.productCategory;
    }

    public void setProductCategory(String newProductCategory){
        if(newProductCategory==null){
            throw new IllegalArgumentException();
        }
        this.productCategory=newProductCategory;
    }

    public double getProductPrice(){
        return this.productPrice;
    }

    public void changeProductPrice(double newProductPrice){
        if(newProductPrice<0 || newProductPrice<=this.getProductCost()){
            throw new IllegalArgumentException();
        }
        this.productPrice=newProductPrice;
    }

    public int getProductQuantity(){
        return this.productQuantity;
    }

    public void increaseProductQuantityBy(int amount){
        if(amount<0){
            throw new IllegalArgumentException();
        }
        this.productQuantity+=amount;
    }

    public void increaseProductQuantity(){
        this.increaseProductQuantityBy(1);
    }

    public void decreaseProductQuantity(){
        this.decreaseProductQuantityBy(1);
    }

    public void clearProductQuantity(){
        this.productQuantity=0;
    }

    public void decreaseProductQuantityBy(int amount){
        if(amount<0){
            throw new IllegalArgumentException();
        }
        if(this.productQuantity-amount>=0){
            this.productQuantity-=amount;
        }else{
            this.clearProductQuantity();
        }
    }

    public boolean equals(Object product){
        if(this==product){
            return true;
        }
        if(!(product instanceof Product)){
            return false;
        }
        Product castedProduct = (Product) product;
        if(this.productId.equals(castedProduct.getProductId()) && this.productName.equals(castedProduct.getProductName())){
            return true;
        }
        return false;
    }

    public int hashCode(){
        return this.productId.hashCode() + this.productName.hashCode();
    }

    public String toString(){
        return this.productName + ", (" + this.productId + ")\n" +
        "Price: $" + this.productPrice + "\n" + "Cost: $" + this.productCost + "\n" + 
        "Profit: $" + this.getProductProfit() + "\n" +
        "Quantity: " + this.productQuantity + " pieces available, "+
        "Category: " + this.productCategory + "\n";
    }


    public int compareTo(Product product){
        return this.productName.compareTo(product.getProductName());
    }

    public double getProductProfit(){
        return this.productPrice - this.productCost;
    }

}
