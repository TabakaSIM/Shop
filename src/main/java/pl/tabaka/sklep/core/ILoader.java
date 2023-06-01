package pl.tabaka.sklep.core;

import java.io.IOException;

public interface ILoader {
    void readDataFromFile() throws IOException;
    void saveDataToFile() throws IOException;
}
