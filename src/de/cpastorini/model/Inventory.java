package de.cpastorini.model;

import de.cpastorini.logic.CsvManager;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    //region 0 Konstanten
    public static final int DEFAULT_INVENTORY_ID = 0;
    public static final String DEFAULT_INVENTORY_NAME = "Inventarname";
    public static final String DEFAULT_DESCRIPTION_OF_INVENTORY = "Inventar Beschreibung";
    public static final int DEFAULT_AMOUNT_OF_ARTICLES = 0;
    private static final String CSV_REGEX= ";";

    private static final int SPLIT_INDEX_ID = 0;
    private static final int SPLIT_INDEX_INVENTORY_NAME = 1;
    private static final int SPLIT_INDEX_DESCRIPTION = 2;
    private static final int SPLIT_INDEX_ARTICLES = 3;
    private static final int SPLIT_INDEX_AMOUNT_OF_ARTICLES = 4;
    private static final int SPLIT_INDEX_CATEGORY = 5;
    //endregion

    //region 1 Attribute
    private int id;
    private String inventoryName;
    private String descriptionOfInventory;

    private List<String> allArticleFromInventory;
    private int totalAmountOfArticles;
    private LoadingCategory category;


    //endregion

    //region 2 Konstruktor
    public Inventory(){
        this.id = DEFAULT_INVENTORY_ID;
        this.inventoryName = DEFAULT_INVENTORY_NAME;
        this.descriptionOfInventory = DEFAULT_DESCRIPTION_OF_INVENTORY;
        this.allArticleFromInventory = new ArrayList<>();
        this.totalAmountOfArticles = DEFAULT_AMOUNT_OF_ARTICLES;

        this.category = LoadingCategory.CATEGORY_GENERAL;
    }

    public Inventory(int id, String inventoryName, String inventoryDescription, List<String> articles, int totalAmount, LoadingCategory category){
        this.id = id;
        this.inventoryName = inventoryName;
        this.descriptionOfInventory = inventoryDescription;
        this.allArticleFromInventory = articles;
        this.totalAmountOfArticles = totalAmount;
        this.category = category;
    }

    public Inventory(String csvLine){
        this.setAllAttributeFromCsvLine(csvLine);
    }
    //endregion

    //region 3 Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getDescriptionOfInventory() {
        return descriptionOfInventory;
    }

    public void setDescriptionOfInventory(String descriptionOfInventory) {
        this.descriptionOfInventory = descriptionOfInventory;
    }

    public List<String> getAllArticleFromInventory() {
        return allArticleFromInventory;
    }

    public void setAllArticleFromInventory(List<String> allArticleFromInventory) {
        this.allArticleFromInventory = allArticleFromInventory;
    }

    public int getTotalAmountOfArticles() {
        return totalAmountOfArticles;
    }

    public void setTotalAmountOfArticles(int totalAmountOfArticles) {
        this.totalAmountOfArticles = totalAmountOfArticles;
    }

    public LoadingCategory getCategory() {
        return category;
    }

    public void setCategory(LoadingCategory category) {
        this.category = category;
    }

    public String getAttributesFromCsVFile(){
        String getReadyForCsvFile = "";
        String articles = "";
        if(this.allArticleFromInventory != null && !this.allArticleFromInventory.isEmpty()){
            for(int index = 0; index < this.allArticleFromInventory.size(); index++){
                articles += this.allArticleFromInventory.get(index);

                if(index < this.allArticleFromInventory.size()-1){
                    articles += ",";
                }
            }
            getReadyForCsvFile += this.id + CSV_REGEX + this.inventoryName + CSV_REGEX + this.descriptionOfInventory +
                    CSV_REGEX + articles + CSV_REGEX + this.totalAmountOfArticles + CSV_REGEX + this.category.getCategoryName() + "\n";
        }
        return getReadyForCsvFile;
    }

    public void setAllAttributeFromCsvLine(String csvLine){
        String[] attributes = csvLine.split(CSV_REGEX);

        this.id = Integer.parseInt(attributes[SPLIT_INDEX_ID]);
        this.inventoryName = attributes[SPLIT_INDEX_INVENTORY_NAME];
        this.descriptionOfInventory = attributes[SPLIT_INDEX_DESCRIPTION];
        this.totalAmountOfArticles = Integer.parseInt(attributes[SPLIT_INDEX_AMOUNT_OF_ARTICLES]);
        this.category = LoadingCategory.fromStatusText(attributes[SPLIT_INDEX_CATEGORY]);

        if(this.allArticleFromInventory == null) {
            this.allArticleFromInventory = new ArrayList<>();
        }

        String article = attributes[SPLIT_INDEX_ARTICLES];
        this.allArticleFromInventory.add(article);
    }

    //endregion

    //region 4 Funktion toString()
    @Override
    public String toString() {
        return  "id=" + id +
                ", inventoryName='" + inventoryName + '\'' +
                ", descriptionOfInventory='" + descriptionOfInventory + '\'' +
                ", allArticleFromInventory=" + allArticleFromInventory +
                ", totalAmountOfArticles=" + totalAmountOfArticles +
                ", category=" + category;
    }

    //endregion
}
