package csv_bd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiciuAudit {
    public static void logAudit(String mesaj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv_bd\\serviciuAudit.csv", true))) {
            Date dataCurenta = new Date();
            SimpleDateFormat formatOra = new SimpleDateFormat("HH:mm:ss");
            String oraCurentaString = formatOra.format(dataCurenta);

            String line = "[Audit] " + "[" + oraCurentaString + "] " + mesaj;
            writer.write(line);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("eroare la scrierea in fisierul CSV: " + e.getMessage());
        }
    }
}
