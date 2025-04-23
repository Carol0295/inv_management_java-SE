package de.cpastorini.model;

import java.util.ArrayList;

/**
 * Liste zur Verwaltung von {@link Inventory}-Objekten.
 * Diese Klasse erweitert {@link ArrayList} und bietet zusätzliche Funktionen
 * wie findById, aber es ist noch nicht implementiert
 */
public class InventoryList extends ArrayList<Inventory> {

    /**
     * Editiert ein Inventory mit einem bestimmten index in der Liste
     * falls es null ist, dann wird nichts ersetzt.
     * @param index index of the element to replace
     * @param inv element to be stored at the specified position
     * @return
     */
    @Override
    public Inventory set(int index, Inventory inv) {
        if(inv == null){
            System.err.println("Fehler: Null-Packet kann nicht gesetzt werden");
            return null;
        }
        if(index < 0 || index >= this.size()){
            System.err.println("Fehler: Ungültiger Index (" + index + ")");
            return null;
        }
        return super.set(index, inv);
    }

    /**
     * Fügt eine neue Inventar hinzu in die Liste, falls es null ist, wird nichts hinzugefügt
     * @param inventory element whose presence in this collection is to be ensured
     * @return
     */
    @Override
    public boolean add(Inventory inventory) {
        if(inventory == null){
            System.err.println("Fehler: Packet ist null und wird nicht hinzugefügt.");
            return false;
        }
        return super.add(inventory);
    }

    /**
     * Löscht eine Inventar anhand des index, falls das Index nicht existiert,
     * wird nichts gelöscht
     * @param index the index of the element to be removed
     * @return
     */
    @Override
    public Inventory remove(int index) {
        if(index < 0 || index >= this.size()){
            System.err.println("Fehler: Ungültiger Index (" + index + "), keine Entfernung.");
            return null;
        }
        return super.remove(index);
    }

    /**
     * Erstellt eine Darstellung der Inventare in der Liste
     * @return ein String-Ausgabe von der gespeicherte Inventare
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inventory-List:\n");
        for(Inventory inv : this){
            sb.append(inv.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     *  Liefert die Information von einen Inventar zurück,
     *  wenn es gefunden wurde anhand des Indexs
     * @param id der Index der Inventar
     * @return
     */
    public Inventory findById(int id){
        for(Inventory inv : this){
            if(inv.getId() == id){
                return inv;
            }
        }
        return null;
    }
}
