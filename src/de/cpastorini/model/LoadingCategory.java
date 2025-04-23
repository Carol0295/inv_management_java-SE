package de.cpastorini.model;

public enum LoadingCategory {
    CATEGORY_GENERAL("Allgemeine Kategorie"),
    CATEGORY_BOOKS("Kategorie Bücher"),
    CATEGORY_TOYS("Kategorie Spielzeug"),
    CATEGORY_OFFICE("Kategorie Büro");

    private final String category;

    LoadingCategory(String category){
        this.category = category;
    }

    public String getCategoryName(){
        return category;
    }

    public static LoadingCategory fromOrdinal(int categoryNumber){
        if(categoryNumber < 0 || categoryNumber >= values().length){
            return CATEGORY_GENERAL;
        }
        return values()[categoryNumber];
    }

    public static LoadingCategory fromStatusText(String text){
        for(LoadingCategory categoryText : values()){
            if(categoryText.category.equalsIgnoreCase(text)){
                return categoryText;
            }
        }
        //Fallback auf TO_LOAD bei unbekanntem Text
        return CATEGORY_GENERAL;
    }
}
