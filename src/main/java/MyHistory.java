
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHistory {

    String filename;
    List<String> data;
    Set<String> index;
    public MyHistory(String filename) {
        // vytvori instanci pro soubor _fileName_
        this.filename = filename;
        data = new ArrayList<>();
        index = new HashSet<>();
    }

    public List<String> read() throws IOException {
        // načte soubor fileName do paměti, skončí výjimkou pokud soubor nejde načíst
        Path path = Paths.get(filename);
        data = Files.readAllLines(Paths.get(filename));
        return data;
    }

    public void save() throws IOException {
        Path path = Paths.get(filename);
        Files.writeString(path, this.toString());
        // zapíše obsah paměti do souboru, skončí výjimkou pokud soubor nejde uložit
        // pokud soubor již existuje, přepíše jej
    }

    public void addLine(String str) {
        if (!index.contains(str)) {
            data.add(str);
            index.add(str);
        }
        // přidá řádek str do paměti pokud tam ještě není,
        // duplicitní řádky ignoruje.
    }

    public String toString() {
        return String.join("\n", data);
        // vrátí řetězec obsahující všechny řádky
    }
}