package de.cpastorini.ui;

import de.cpastorini.logic.CsvManager;
import de.cpastorini.model.Inventory;
import de.cpastorini.model.InventoryList;
import de.cpastorini.model.LoadingCategory;
import de.cpastorini.settings.texts.Texts;
import de.rhistel.logic.ConsoleReader;

import java.util.ArrayList;
import java.util.List;

public class UiController {

    //region 0 Attribute
    private InventoryList inventories;
    private static UiController instance;
    //endregion

    //region Konstruktor
    private UiController(){
        this.inventories = CsvManager.getInstance().getInventoryFromCsvFile();
    }
    //endregion

    //region start App
    public void startApplication(){
        manageMenu();
    }
    //endregion

    //region instance UiController
    /**
     * Singleton-Methode um die einzige Instanz dieser Klasse
     * einmalig zu generieren und durch synchronized threadsicher zurueckzuliefern.
     *
     * @return instance : {@link UiController} : Einzige Instanz dieser Klasse die jemals existieren wird.
     */
    public static synchronized UiController getInstance(){
        if(instance == null){
            instance = new UiController();
        }
        return instance;
    }
    //endregion

    // region Manage & Print Menu
    private void manageMenu() {
        boolean exitApp = false;
        printApplicationNameAndWelcomeMsg();

        while(!exitApp){
            printMenu();
            int userOption = ConsoleReader.in.readPositivInt();
            System.out.print("\n");
            exitApp = selectedActionByUser(userOption);
        }
        System.out.println(Texts.PROGRAM_IS_ENDED);
    }

    private void printMenu() {
        int displayIndex;
        System.out.print("\n");
        for(int index = 0; index < Texts.MAIN_MENU.length; index++){
            if(index == Texts.MAIN_MENU.length -1 ){
                displayIndex = 0;
            } else {
                displayIndex = index + 1;
            }
            System.out.println("[ "+ displayIndex + " ] - " + Texts.MAIN_MENU[index]);
        }

    }

    private void printApplicationNameAndWelcomeMsg() {
        System.out.println(Texts.APPLICATION_NAME);
        System.out.println(Texts.WELCOME_MSG);
    }

    private boolean selectedActionByUser(int selectedAction){
        boolean exitApp = false;
        switch (selectedAction){
            case 1 -> showAllInventory();
            case 2 -> addInventory();
            case 3 -> updateInventory();
            case 4 -> deleteInventory();
            case 0 -> exitApp = true;
            default -> System.out.println(Texts.OPTION_NOT_FOUND);
        }

        return exitApp;
    }
    // endregion

    // region CRUD für Inventare
    private void showAllInventory() {

        if(this.inventories == null){
            System.out.println(Texts.NO_INVENTORIES);
        } else {
            System.out.println(Texts.IN_SYSTEM_INVENTORY);

            for(Inventory currentInventoriesInList : this.inventories){
                System.out.println(currentInventoriesInList.toString());
            }
        }
    }

    private void addInventory(){

        System.out.println(Texts.AUTOINCREMENT_INDEX);
        int nextIndexToSet = CsvManager.getInstance().getLastId();

        Inventory createdInventory = createOrUpdateValues(nextIndexToSet);

        if(createdInventory == null){
            System.out.println(Texts.COULD_NOT_CREATE_INVENTORY);
        } else {

            this.inventories.add(createdInventory);
            CsvManager.getInstance().saveInToCsvFile(this.inventories);
            System.out.println(Texts.CREATED_INVENTORY);
            CsvManager.getInstance().lastId = nextIndexToSet+1;
        }
    }

    private void updateInventory(){
        if(this.inventories.isEmpty()){
            System.out.println(Texts.NO_INVENTORIES);
        } else {
            this.showAllInventory();

            System.out.println(Texts.INFO_INDEX_TO_EDIT);
            int indexExists = getValidIndex();

            for(int index = 0; index <  Texts.UPDATE_OPTIONS.length; index++){
                System.out.println("\t>> [ "+ index + " ] - " + Texts.UPDATE_OPTIONS[index]);
            }

            Inventory invToUpdate = this.inventories.get(indexExists);

            boolean exit = false;
            while(!exit){
                System.out.println(Texts.QUESTION_WHAT_TO_EDIT_IN_INVENTORY);
                int userOption = ConsoleReader.in.readPositivInt();
                switch (userOption){
                    case 0 -> {
                        Inventory updatedInventory = createOrUpdateValues(indexExists);
                        if(updatedInventory != null){
                            invToUpdate = updatedInventory;
                        }
                        System.out.println(Texts.SUCCESSFULLY_EDITED);
                        exit = true;
                    }
                    case 1 -> {
                        updateInventoryArticles(indexExists);
                        System.out.println(Texts.SUCCESSFULLY_EDITED);
                        exit = true;
                    }
                    case 2 -> updateInventoryCategory(); // TODO: Implementierung steht noch
                    case 3 -> exit = true;
                    default -> System.out.println(Texts.INVALID_OPTION);
                }
            }
            this.inventories.set(indexExists, invToUpdate);
        }
    }

    private void updateInventoryArticles(int indexToUpdate){
        List<String> toUpdateArticles = new ArrayList<>();

        Inventory invToUpdate = this.inventories.get(indexToUpdate);
        List<String> invArticles = invToUpdate.getAllArticleFromInventory();

        for(String article : invArticles){
            toUpdateArticles.add(article);
        }

        System.out.println(Texts.WARNING_NOT_AVAILABLE_YET_TO_EDIT_ALL_ARTICLES);

        boolean exit = false;
        while (!exit) {
            String inventoryArticles = ConsoleReader.in.readString();
            if (inventoryArticles.isEmpty()) {
                exit = true;
            } else {
                toUpdateArticles.add(inventoryArticles);
            }
        }
        invToUpdate.setAllArticleFromInventory(toUpdateArticles);
        this.inventories.set(indexToUpdate, invToUpdate);

        CsvManager.getInstance().saveInToCsvFile(this.inventories);
        System.out.println(Texts.ARTCICLES_SUCCESSFULLY_ADDED);
        this.showAllInventory();
    }
    //TODO: Implementieren das Editieren von Kategorien
    private void updateInventoryCategory(){

    }

    private void deleteInventory(){
        if(this.inventories == null){
            System.out.println(Texts.NO_INVENTORIES);
        } else {

            this.showAllInventory();
            System.out.println(Texts.WHICH_ONE_TO_DELETE);

            int indexToDelete = getValidIndex();

            System.out.printf(Texts.WARNING_DO_YOU_WANT_TO_DELETE, indexToDelete);

            boolean deleteToConfirm = ConsoleReader.in.readMandatoryAnswerToBinaryQuestion();

            if(deleteToConfirm){
                this.inventories.remove(indexToDelete);
                CsvManager.getInstance().saveInToCsvFile(this.inventories);
                System.out.println(Texts.SUCCESSFULLY_DELETED);
                this.showAllInventory();
            }
        }
    }
    //endregion

    //region Hilfsmethoden und Funktionen
    private Inventory createOrUpdateValues(int index){
        Inventory newInventory = null;
        System.out.println(">>Inventarname: ");
        String invName = ConsoleReader.in.readString();

        System.out.println("Beschreibung der Inventar: ");
        String invDescription = ConsoleReader.in.readString();

        System.out.println("Artikeln Liste: ");
        List<String> articles = new ArrayList<>();
        System.out.println("Geben Sie Artikel ein (tippen Sie 'Eingabetaste', um zu stoppen):");

        boolean exit = false;
        while (!exit) {
            String invArticles = ConsoleReader.in.readString();
            if (invArticles.isEmpty()) {
                exit = true;
            } else {
                articles.add(invArticles);
            }
        }

        System.out.println("Anzahl an Inventare: ");
        int invAmount = ConsoleReader.in.readPositivInt();

        System.out.println("Inventar Kategorie: ");
        for(LoadingCategory categories : LoadingCategory.values()){
            System.out.printf(Texts.CATEGORY_PREFIX, categories.ordinal(), categories.getCategoryName() + "\n");
        }

        int userSelectedCategory = ConsoleReader.in.readPositivInt();
        LoadingCategory selectedCategory = LoadingCategory.fromOrdinal(userSelectedCategory);

        if(!invName.isEmpty() &&
                !invDescription.isEmpty() &&
                !articles.isEmpty() &&
                invAmount >= 0 &&
                (userSelectedCategory >= 0 && userSelectedCategory <= LoadingCategory.values().length)){
            newInventory = new Inventory(index, invName, invDescription, articles, invAmount, selectedCategory);
        }
        return newInventory;
    }

    private int getValidIndex(){
        int validIndex = -1;

        while(validIndex == -1){
            System.out.printf(Texts.INDEX_VALID, this.inventories.size()-1 );
            int index = ConsoleReader.in.readPositivInt();

            if(index < this.inventories.size()){
                validIndex = index;
            }
        }
        return validIndex;
    }
    //endregion

}
