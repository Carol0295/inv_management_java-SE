package de.cpastorini.settings.texts;

public class Texts {

    // region App Name & Welcome message
    public static final String APPLICATION_NAME= "\n###\t -- Verwaltung von Inventare --\t###\n";
    public static final String WELCOME_MSG = "Willkommen bei der Verwaltung der Inventare!\nWas möchstest du tun?";
    // endregion

    // Menu Options
    public static final String[] MAIN_MENU = {
            "Inventare ansehen",
            "Inventare erstellen",
            "Inventare bearbeiten",
            "Inventare löschen",
            "Programm beenden"
    };
    public static final String CATEGORY_PREFIX = "[ %d ] = %s";
    //endregion

    // Update Options Menu
    public static final String[] UPDATE_OPTIONS = {
            "Allgemeine Informationen bearbeiten",
            "Artikeln hinzufügen.",
            "Kategorie bearbeiten.",
            "Bearbeitungsmenu verlassen"
    };
    public static final String INVALID_OPTION = "Auswahl nicht bekannt. Versuch es nochmal.";
    public static final String ARTCICLES_SUCCESSFULLY_ADDED = "Die Artikeln sind hinzugefügt.";
    // endregion

    // region User Messages
    public static final String PROGRAM_IS_ENDED = "Auf Wiedersehen! Das Programm ist beendet :) ";
    public static final String CREATED_INVENTORY = "Inventar ist erstellt!";
    public static final String AUTOINCREMENT_INDEX = ">>Index wird automatisch hinzugefügt...\n";
    public static final String QUESTION_WHAT_TO_EDIT_IN_INVENTORY = "Was möchtest du an dem Inventar bearbeiten? Wähle eine Option aus:";
    public static final String INFO_INDEX_TO_EDIT = "Gibt den Index von den Inventar, was du bearbeiten möchtest: ";
    public static final String NO_INVENTORIES = ">>>> Keine Inventare <<<< ";
    public static final String WHICH_ONE_TO_DELETE = "Welches Inventar möchtest du löschen?: ";
    public static final String SUCCESSFULLY_DELETED = "Inventar ist erfolgreich gelöscht!";
    public static final String WARNING_DO_YOU_WANT_TO_DELETE = "***ACHTUNG*** Bist du sicher, dass du das Inventar mit dem ID: %d löschen willst?\n Diese Aktion kann nicht rückgängig gemacht werden. ";
    public static final String WARNING_NOT_AVAILABLE_YET_TO_EDIT_ALL_ARTICLES = "***ACHTUNG***\n Die Implementierung für das Auswählen von Artikeln zu bearbeiten ist noch nicht möglich.\n " +
            "Sie können aber neue Artikeln in die Liste hinzufügen. 'Eingabetaste', um zu stoppen:";
    public static final String SUCCESSFULLY_EDITED = "Inventar ist erfolgreich bearbeitet!";
    public static final String IN_SYSTEM_INVENTORY = "\t>>Aktuelle Inventare:";

    // endregion

    // region Fehlermeldnungen
    public static final String INVENTORY_NOT_CREATED = "Das neue Inventar konnte nicht erstellt werden.\n";
    public static final String OPTION_NOT_FOUND = "Sorry! Diese Auswahl existiert nicht!\n";
    public static final String COULD_NOT_CREATE_INVENTORY = "Aufgrund von inkorrekte Eingaben, konnte das Inventar nicht erstellt werden.\n";
    public static final String INDEX_VALID = "Bitte einen gültigen Index eingeben: [0 - %d]\n";

    // endregion

}
