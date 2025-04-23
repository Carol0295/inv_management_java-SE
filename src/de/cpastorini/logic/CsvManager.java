package de.cpastorini.logic;

import de.cpastorini.model.Inventory;
import de.cpastorini.model.InventoryList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvManager {

    //region Konstanten
    private static final String FILE_PATH = "src/de/cpastorini/resources/packets.csv";
    //endregion

    //region Attribute
    private static CsvManager instance;
    public int lastId = 0;
    //endregion

    //region Konstruktor
    private CsvManager(){
    }
    //endregion

    /**
     * Singleton-Methode um die einzige Instanz dieser Klasse
     * einmalig zu generieren und durch synchronized threadsicher zurueckzuliefern.
     *
     * @return instance : {@link CsvManager} : Einzige Instanz dieser Klasse die jemals existieren wird.
     */
    public static synchronized CsvManager getInstance(){
        if(instance == null){
            instance = new CsvManager();
        }
        return instance;
    }

    // region Speichern der Informationen in Csv-File
    public void saveInToCsvFile(InventoryList inventories){

        try(FileWriter writer = new FileWriter(FILE_PATH, StandardCharsets.UTF_8)){
            for(Inventory currentInventory : inventories){
                writer.write(currentInventory.getAttributesFromCsVFile());
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    //endregion

    // region Inventare von Csv-File
    public InventoryList getInventoryFromCsvFile(){

        InventoryList inventories = new InventoryList();
        File inventoriesFile = new File(FILE_PATH);

        if(inventoriesFile.exists()){

            try(
                    FileReader reader = new FileReader(FILE_PATH, StandardCharsets.UTF_8);
                    BufferedReader in = new BufferedReader(reader);
                    ){
                String csvLine;
                boolean eof = false;

                while(!eof){
                    csvLine = in.readLine();

                    if(csvLine == null){
                        eof = true;
                    } else {

                        Inventory contractFromCsvFile = new Inventory(csvLine);
                        inventories.add(contractFromCsvFile);

                        this.lastId = getLastIdFromCsv(csvLine, this.lastId);
                    }
                }

            } catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        return inventories;
    }
    // endregion

    // region Letzte größe Index aus Csv-File
    /**
     * Zum Ausgeben der letzte größe Index von der Csv-File
     *
     * @param csvLine String Line was gerade zu lesen ist
     * @param lastId die hochste Index in den Csv-File zu finden
     * @return
     */
    public int getLastIdFromCsv(String csvLine, int lastId){

        String[] attributes = csvLine.split(";");
        int currentId = Integer.parseInt(attributes[0]);
        lastId = Math.max(lastId, currentId);

        return lastId;
    }
    // endregion

    public int getLastId() {
        return lastId;
    }
}
