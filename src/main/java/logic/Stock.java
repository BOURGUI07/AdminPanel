/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;
import domain.Product;
import java.util.*;

/**
 *
 * @author lenovo
 */
public class Stock {
  private Map<String, Product> stock;
    private final int productTreesHold =50;


    public Stock(){
        this.stock=new HashMap<>();
    }

    public void listProductsBelowTreeshold(){
        if(this.stock.isEmpty()){
            return;
        }
        this.stockProducts().stream().filter(p -> p.getProductQuantity()<=this.productTreesHold).forEach(p -> System.out.println(p));
    }

    public void addProductToTheStock(String productId, String productName){
        if(this.stock.containsKey(productId)){
            return;
        }
        Product newProduct = new Product(productId, productName);
        this.stock.putIfAbsent(productId, newProduct);
    }

    public List<Product> stockProducts(){
        Collection<Product> group = this.stock.values();
        List<Product> list = new ArrayList<>();
        if(this.stock.isEmpty()){
            return list;
        }
        Iterator<Product> itr = group.iterator();
        while(itr.hasNext()){
            list.add(itr.next());
        }
        return list;
    }

    public void numberOfProductsOfCategory(String categoryName){
        int number = (int) this.stockProducts().stream().filter(p -> p.getProductCategory().equalsIgnoreCase(categoryName)).count();
        System.out.println("The number of product under the category of: " +categoryName + " is: " + number);
    }

    public void searchProductsByCategory(String productCategory){
        this.stockProducts().stream().filter(product -> product.getProductCategory().equalsIgnoreCase(productCategory)).forEach(p -> System.out.println(p));
    }

    public void filterProductsByPrice(double minPrice, double maxPrice){
        if(this.stock.isEmpty()){
            return;
        }
        for(Product product:this.stockProducts()){
            if(product.getProductPrice()<=maxPrice && product.getProductPrice()>=minPrice){
                System.out.println(product);
            }
        }
    }

    public void filterProductsByQuantity(int minQuantity, int maxQuantity){
        if(this.stock.isEmpty() || minQuantity<0 || maxQuantity<0){
            return;
        }
        for(Product product:this.stockProducts()){
            if(product.getProductQuantity()<=maxQuantity && product.getProductQuantity()>=minQuantity){
                System.out.println(product);
            }
        }
    }

    public void sortProductsByPriceQty(){
        if(this.stock.isEmpty()){
            return;
        }
        Comparator<Product> comparator = Comparator.comparing(Product::getProductPrice).thenComparing(Product::getProductQuantity);
        Collections.sort(this.stockProducts(), comparator);
        this.stockProducts().stream().forEach(product -> System.out.println(product));
    }

    public void sortProductsByCost(){
        if(this.stock.isEmpty()){
            return;
        }
        Comparator<Product> comparator = Comparator.comparing(Product::getProductCost);
        Collections.sort(this.stockProducts(), comparator);
        this.stockProducts().stream().forEach(product -> System.out.println(product));
    }

    public void sortProductsByProfit(){
        if(this.stock.isEmpty()){
            return;
        }
        Comparator<Product> comparator = Comparator.comparing(Product::getProductProfit);
        Collections.sort(this.stockProducts(), comparator);
        this.stockProducts().stream().forEach(product -> System.out.println(product));
    }

    public void sortProductsByQtyPrice(){
        if(this.stock.isEmpty()){
            return;
        }
        Comparator<Product> comparator = Comparator.comparing(Product::getProductQuantity).thenComparing(Product::getProductPrice);
        Collections.sort(this.stockProducts(), comparator);
        this.stockProducts().stream().forEach(product -> System.out.println(product));
    }

    public void sortProductsAlphabetically(){
        if(this.stock.isEmpty()){
            return;
        }
        this.stockProducts().stream().sorted().forEach(product -> System.out.println(product));
    }

    public Product getProductForName(String productName){
        for(Product product:this.stockProducts()){
            if(product.getProductName().equals(productName)){
                return product;
            }
        }
        return null;
    }

    public Product getProductForId(String productId){
        for(Product product:this.stockProducts()){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }
  
    public void removeProductFromStockByName(String productName){
        if(this.stock.isEmpty() || !this.stock.containsValue(this.getProductForName(productName)) || productName==null){
            return;
        }
        for(String id:this.stock.keySet()){
            String name = this.stock.get(id).getProductName();
            if(name.equals(productName)){
                this.stock.remove(id);
            }
        }
    }

    public void removeProductFromStockById(String productId){
        if(this.stock.isEmpty() || !this.stock.containsValue(this.getProductForId(productId)) || productId==null){
            return;
        }
        for(String id:this.stock.keySet()){
            String productid = this.stock.get(id).getProductId();
            if(productid.equals(productId)){
                this.stock.remove(id);
            }
        }
    }

    public void viewStockProducts(){
        if(this.stock.isEmpty()){
            System.out.println("No products foud. The stock is empty.");
        }
        this.stockProducts().stream().forEach(product -> System.out.println(product));
    }

    public String averageProfitOfStockProducts(){
        if(this.stock.isEmpty()){
            return "The stock is empty!";
        }
        double average = this.stockProducts().stream().mapToDouble(product -> product.getProductProfit()).average().getAsDouble();
        return "The average profit of products is: $" + average;
    }

    public String averageCostOfStockProducts(){
        if(this.stock.isEmpty()){
            return "The stock is empty!";
        }
        double average = this.stockProducts().stream().mapToDouble(product -> product.getProductCost()).average().getAsDouble();
        return "The average cost of products is: $" + average;
    }

    public String averagePriceOfStockProducts(){
        if(this.stock.isEmpty()){
            return "The stock is empty!";
        }
        double average= this.stockProducts().stream().mapToDouble(product -> product.getProductPrice()).average().getAsDouble();
        return "The average price of products is: $" + average;
    }

    public String numberOfStockProducts(){
        if(this.stock.isEmpty()){
            return "The number of products right now is 0";
        }
        return "The current number of products available in the stock is: " +  this.stockProducts().size();
    }
}
