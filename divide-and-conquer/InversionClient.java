import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InversionClient {

    public static List<Long> createList (File input) {
        List<Long> ret = new ArrayList<Long>();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext()) {
                ret.add(Long.parseLong(scanner.next()));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("input is not a number");
        }
        return ret;
    }
    public static void main (String[] args) {
        System.out.println("Enter file name: ");
        Scanner scanner = new Scanner (System.in);
        scanner.close();
        String filename = scanner.nextLine();
        List<Long> ls = createList(new File(filename));
         System.out.println(Inversion.Count(ls));

    }
}
