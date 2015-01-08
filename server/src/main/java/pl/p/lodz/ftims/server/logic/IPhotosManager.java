package pl.p.lodz.ftims.server.logic;

import java.io.IOException;

/**
 * Interfejs udostepniajacy metody zapisu zdjec zadan i wskazówek na dysku.
 * @author Piotr Grzelak
 */
public interface IPhotosManager {

    /**
     * Stała przechowująca informację o tym gdzie zapisywane są zdjęcia do zadań
     * i wskazówek.
     */
    static final String PHOTOS_DIR = "img/";
    
    /**
     * Zapisuje zdjécie wskazówki/zadania na dysku jako plik o wskazanej nazwie
     * @param photo zdjécie do zapisania
     * @param filename nazwa pliku
     * @throws java.io.IOException 
     */
    void savePhoto(byte[] photo, String filename) throws IOException;
    
    /**
     * Odczytuje zdjecie zadania/wskazówki ze wskzanageo pliku
     * @param fileName nazwa pliku
     * @return zdjecie w postaci tablicy bajtów
     * @throws java.io.IOException 
     */
    byte[] readPhoto(String fileName) throws IOException;
}
