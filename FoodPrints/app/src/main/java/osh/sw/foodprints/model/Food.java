package osh.sw.foodprints.model;

/**
 * Created by dsm2016 on 2017-07-29.
 */

public class Food {
    private String foodName;
    private String foodCategory;
    private String foodStore;

    public String getFoodLocation() {
        return foodLocation;
    }

    public void setFoodLocation(String foodLocation) {
        this.foodLocation = foodLocation;
    }

    private String foodLocation;
    private String foodDate;
    private boolean foodRecommend;
    private int starRecommend;


    private int foodImage;

    public boolean isFoodRecommend() {
        return foodRecommend;
    }

    public void setFoodRecommend(boolean foodRecommend) {
        this.foodRecommend = foodRecommend;
    }


    public Food(int foodImage,String foodName,String foodStore,int starRecommend){
        this.foodImage=foodImage;
        this.foodName=foodName;
        this.foodStore=foodStore;
        this.starRecommend=starRecommend;
    }

    public Food(String foodName, String foodCategory, String foodStore,String foodLocation, String foodDate, boolean foodRecommend, int starRecommend) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.foodStore = foodStore;
        this.foodLocation=foodLocation;
        this.foodDate = foodDate;
        this.foodRecommend = foodRecommend;
        this.starRecommend = starRecommend;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodStore() {
        return foodStore;
    }

    public void setFoodStore(String foodStore) {
        this.foodStore = foodStore;
    }

    public String getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(String foodDate) {
        this.foodDate = foodDate;
    }



    public int getStarRecommend() {
        return starRecommend;
    }

    public void setStarRecommend(int starRecommend) {
        this.starRecommend = starRecommend;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }
}
