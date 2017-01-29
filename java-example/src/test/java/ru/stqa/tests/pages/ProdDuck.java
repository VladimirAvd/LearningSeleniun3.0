package ru.stqa.tests.pages;

/**
 * Created by Vladimir on 24.01.2017.
 */
public class ProdDuck {
    private String namePD;
    private String price;
    private float sizePD;
    private String textDeclarPD;
    private String textWeightPD;

    private String colorPD;
    private String namePricePD;


    public ProdDuck setName (String name){
        this.namePD = name;
        return this;
    }

    public ProdDuck setNamePrice (String name){
        this.namePricePD = name;
        return this;
    }

    public ProdDuck setPrice (String locator){
        this.price = locator;
        return this;
    }
    public ProdDuck setTextDeclar (String locator){
        this.textDeclarPD = locator;
        return this;
    }

    public ProdDuck setTextWeight (String locator){
        this.textWeightPD = locator;
        return this;
    }

    public ProdDuck setColor (String locator){
        this.colorPD = locator;
        return this;
    }
    public ProdDuck setSize (String size){
        float rz;
        String str;
        str = size.substring(0,size.length()-2);
        rz = Float.valueOf(str);
        this.sizePD = rz;
        return this;
    }

    public float getSize() {
        return this.sizePD;
    }

    public String getName() {
        return this.namePD;
    }
    public String getNamePrice() {
        return this.namePricePD;
    }
    public String getColor() {
        return this.colorPD;
    }

    public String getTextDeclar() {
        return this.textDeclarPD;
    }
    public String getTextWeight() {
        return this.textWeightPD;
    }
    public String getPrice() {
        return this.price;
    }


}
