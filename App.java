import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args)
            throws FileNotFoundException, NotVaildAutosarFileException, EmptyAutosarFileException {
        Scanner Sc = new Scanner(System.in);
        String loc = args[0];
        loc.trim();

        if (!(loc.endsWith(".arxml") || loc.endsWith(".ARXML"))) {
            Sc.close();
            throw new NotVaildAutosarFileException("invalid file extention not an autosar file");
        }
        File sar = new File(loc);
        Scanner file = new Scanner(sar);
        File out;
        if (loc.endsWith(".arxml")) {
            out = new File(".//out//" + sar.getName().replace(".arxml", "_mod.arxml"));
        } else {
            out = new File(sar.getName().replace(".ARXML", "_mod.ARXML"));

        }
        if (!(file.hasNext())) {
            Sc.close();
            file.close();
            throw new EmptyAutosarFileException("Empty file");

        }
        ArrayList<Div> temp = new ArrayList<Div>();
        String id = "", sname = "", lname = "";
        String f = file.nextLine();
        while (file.hasNextLine()) {
            // System.out.println(file.nextLine());
            String l = file.nextLine();
            if (l.contains("<CONTAINER")) {
                int s = l.indexOf("UUID=");
                int en = l.indexOf(">");
                id = l.substring(s + 6, en - 1);
                /////////////////////////////////////////////////////////////
                l = file.nextLine().trim();
                s = l.indexOf("E>");
                en = l.indexOf("</SHORT-NAME>");
                sname = l.substring(s + 2, en);
                /////////////////////////////////////////////////////////
                l = file.nextLine().trim();
                s = l.indexOf("E>");
                en = l.indexOf("</LONG-NAME>");
                lname = l.substring(s + 2, en);
                temp.add(new Div(id, lname, sname));
            }

        }
        PrintWriter writer = new PrintWriter(out);
        writer.println(f);
        writer.println("<AUTOSAR>");
        Collections.sort(temp);
        for (int i = 0; i < temp.size(); i++) {
            writer.println(temp.get(i).toString());
        }
        writer.println("</AUTOSAR>");
        writer.close();
        Sc.close();
        file.close();

    }

}
