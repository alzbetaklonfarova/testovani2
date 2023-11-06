
import org.junit.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyHistoryTest {

    @TempDir
    private Path tmpDir;

    @Test
    public void readTest() {
        MyHistory h = new MyHistory(tmpDir + "\\readTest.data");
        System.out.println(h.filename);
        assertThrows(IOException.class, () -> h.read());
    }

    @Test
    public void saveTest() {
        MyHistory h = new MyHistory( "saveTest.data");
        try {
            h.save();
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    @Test
    public void saveAndReadTest() {
        MyHistory h = new MyHistory("saveAndReadTest.data");
        ArrayList<String> data = new ArrayList<>();
        try {
            h.save();
        } catch (IOException e) {
            assertTrue(false);
        }
        try {
            assertEquals(data, h.read());
        } catch (IOException e) {
            assertTrue(false);
        }

    }

    @Test
    public void addLineTest() {
        MyHistory h = new MyHistory("abc");
        h.addLine("radek 1");
        assertEquals("radek 1", h.toString());
    }

    @Test
    public void add3Lines() {
        MyHistory h = new MyHistory("abc");
        h.addLine("radek 1");
        h.addLine("radek 2");
        h.addLine("radek 3");

        assertEquals("radek 1\nradek 2\nradek 3", h.toString());
    }

    @Test
    public void noDuplicities() {
        MyHistory h = new MyHistory("abc");
        h.addLine("radek 1");
        h.addLine("radek 2");
        h.addLine("radek 3");
        h.addLine("radek 2");

        assertEquals("radek 1\nradek 2\nradek 3", h.toString());
    }
}