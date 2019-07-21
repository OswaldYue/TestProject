package register;

import framework.URL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class RomoteMapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<String, List<URL>>();
    private static String REGIST_FILE = "E:\\code\\githug-code\\TestProject\\regist-file.txt";

    private static void saveFile() {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(REGIST_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {

        try {

            FileInputStream fileInputStream = new FileInputStream(REGIST_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>)objectInputStream.readObject();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void regist(String interfaceName,URL url) {

        List<URL> urls = REGISTER.get(interfaceName);
        if (urls != null) {
            urls.add(url);
        }else {
            urls = new ArrayList<URL>();
            urls.add(url);
            REGISTER.put(interfaceName,urls);
        }
        saveFile();
    }

    public static URL random(String interfaceName) {

        Map<String, List<URL>> map = getFile();

        List<URL> urls = map.get(interfaceName);

        //此处会报NPE,因为在两个进程中操作map,在一个进程中创建map,在另一个进程中拿不到创建的map
        //此处可以使用zk或者redis可以解决
        return urls.get(new Random().nextInt(urls.size()));
    }
}
