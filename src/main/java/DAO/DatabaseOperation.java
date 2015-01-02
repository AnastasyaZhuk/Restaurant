package DAO;

import model.Category;
import model.Food;
import org.w3c.dom.Document;

import java.util.List;


public interface DatabaseOperation {
    public List<Category> getAllCategory(Document document);
    public List<Food> getAllFood(Document document, String category);
    public void createCategory(Document document,  Category nameCategory);
    public void createFood(Document document, Food food);
    public void updateCategory(Document document, Category oldCategory, Category newCategory);
    public void updateFood(Document document, Food oldFood, Food newFood);
    public void updatePrice(Document document, Food food);
    public void removeCategory(Document document, Category category);
    public void removeFood(Document document, Food food);


}
