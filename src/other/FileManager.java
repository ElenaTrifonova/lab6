package other;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;

/**
 * Class that works with file and collection
 */
public class FileManager {
    File fileCollection = new File(System.getenv("FilePath"));
    Hashtable<Long, Organization> collection = new Hashtable<>();
    public static final Logger log = Logger.getLogger(String.valueOf(FileManager.class));

    /**
     * method that loads collection from file
     *
     * @param filePath
     * @throws FileNotFoundException
     */
    public Hashtable<Long, Organization> loadCollection(String filePath) throws FileNotFoundException {

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileCollection), "UTF-8");
            BufferedReader buffereader = new BufferedReader(reader);
            Gson gson = new Gson();
            System.out.println("Loading a collection from a file " + fileCollection.getAbsolutePath());
            log.info("Collection successfully loaded from file.");
            StringBuilder stringBuilder = new StringBuilder();
            String nextString;
            while ((nextString = buffereader.readLine()) != null) {
                stringBuilder.append(nextString);
            }
            Type typeOfCollection = new TypeToken<Hashtable<Long, Organization>>() {
            }.getType();

            try {
                collection = gson.fromJson(stringBuilder.toString(), typeOfCollection); //подаем строку

            } catch (JsonSyntaxException e) {
                System.out.println("Json syntax error. The file cannot be downloaded.");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * method that saves collection in file
     *
     * @param collection - hashtable
     * @return boolean
     */
    public boolean saveCollection(Hashtable<Long, Organization> collection) {
        Scanner scan = new Scanner(new InputStreamReader(System.in));
        System.out.println("Enter the path to file or it will be save in the original file:");
        String args = scan.nextLine();
        if ((args == null) || (args.equals(""))) {
            Gson gson = new Gson();
            if (!fileCollection.exists()) {
                System.out.println(("The file cannot be saved. File at the specified path (" + fileCollection.getAbsolutePath() + ") doesn't exist."));
            } else if (!fileCollection.canRead() || !fileCollection.canWrite()) {
                System.out.println("The file cannot be saved. The file is read- and(or) write-protected.");
            } else {

                try {
                    System.out.println(fileCollection.getAbsolutePath());

                    FileWriter fileWriter = new FileWriter(fileCollection);
                    String stringColl = gson.toJson(collection);

                    fileWriter.write(stringColl, 0, stringColl.length());

                    fileWriter.flush();
                    fileWriter.close();
                    System.out.println("File was saved successfully.");
                    return true;

                } catch (Exception ex) {
                    System.out.println("Something went wrong in the process of writing file.");
                }
                return false;
            }

        } else {
            try {
                Gson gson = new Gson();
                File file = new File(args);
                FileWriter fileWriter = new FileWriter(file);
                String stringColl = gson.toJson(collection);

                fileWriter.write(stringColl, 0, stringColl.length());

                fileWriter.flush();
                fileWriter.close();
                System.out.println("File was saved successfully.");
                return true;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;

        }
        return false;
    }

    public Hashtable<Long, Organization> getCollection() {
        return collection;
    }


    @Override
    public String toString() {
        return "Information about collection:\n" +
                "File path:" + fileCollection.getAbsolutePath() + "\n" +
                "Type of collection: " + collection.getClass().toString();
    }
}