package com.liftlife.liftlife.product;

import com.google.cloud.firestore.WriteResult;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.diet.meal.Meal;
import com.liftlife.liftlife.diet.meal.MealRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class ProductRepository extends FirestoreRepositoryTemplate<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    public void deleteProduct(String productId){
        super.delete(productId);
    }

    public void deleteProduct(Product product){
       super.delete(product);
    }

    public String insertProduct(Product product) {
        return super.insert(product);
    }

    public WriteResult updateProduct(Product product) {
        return super.update(product);
    }

    public Product findProductById(String productId) {
        return super.findById(productId);
    }

    public List<Product> findProductByName(String name) {
        return super.findByField("name", name);
    }

    public List<Product> findProductByCalories(int calories) {
        return super.findByField("calories", calories);
    }
}
