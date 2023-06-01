package pl.tabaka.sklep.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tabaka.sklep.artykuly.IListaProduktow;
import pl.tabaka.sklep.artykuly.Produkt;
import pl.tabaka.sklep.models.users.IUserList;
import pl.tabaka.sklep.models.users.User;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class Loader implements ILoader{

    @Autowired
    private IUserList UserList;

    @Autowired
    private IListaProduktow ListaProduktow;

    @Override
    public void readDataFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("db.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] objectData = line.split(";");
            String[] vars = Arrays.copyOfRange(objectData, 1, objectData.length);
            switch (objectData[0]) {
                case "Produkt":
                    ListaProduktow.register(new Produkt(vars[0],Float.parseFloat(vars[1]),Integer.parseInt(vars[2])));
                    break;
                case "User":
                    UserList.register(new User(vars[0],vars[1],Float.parseFloat(vars[2]),Boolean.parseBoolean(vars[3])),true);
                    break;
                default:
                    System.out.println("Unexpected type during DB loading !!");
                    break;
            }
        }
        reader.close();
    }

    @Override
    public void saveDataToFile() throws IOException {
        Collection<Writable> entries = new ArrayList<>();
        entries.addAll(ListaProduktow.getProducts());
        entries.addAll(UserList.getUsers());
        BufferedWriter writer =
                new BufferedWriter(new FileWriter("db.csv"));
        boolean firstTime = true;
        for (Writable entry : entries) {
            String lineInFile = entry.toCSV();
            if (!firstTime) {
                writer.newLine();
            }
            firstTime = false;
            writer.write(lineInFile);
        }
        writer.close();
    }
}
