import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) {


        List<GameProgress> saves = new ArrayList<>();

        saves.add(new GameProgress(10, 3, 4, 88));
        saves.add(new GameProgress(20, 4, 14, 55));
        saves.add(new GameProgress(30, 5, 24, 234));

        List<String> ways = new ArrayList<>();

        ways.add("C://Games/savegames/save1.dat");
        ways.add("C://Games/savegames/save2.dat");
        ways.add("C://Games/savegames/save3.dat");
        saveGame(ways.get(0), saves.get(0));
        saveGame(ways.get(1), saves.get(1));
        saveGame(ways.get(2), saves.get(2));
        String wayZip = "C://Games/savegames/zip.zip";

        zipFiles(wayZip, ways);

        for (String way : ways) {
            File delete = new File(way);
            delete.delete();
        }
    }

    public static void saveGame(String ways, GameProgress saves) {
        try (FileOutputStream fos = new FileOutputStream(ways);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(saves);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipWay, List<String> ways) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipWay))) {
            for (int i = 0; i < ways.size(); i++) {
                try (FileInputStream fis = new FileInputStream(ways.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + i + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                }
            }
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}


