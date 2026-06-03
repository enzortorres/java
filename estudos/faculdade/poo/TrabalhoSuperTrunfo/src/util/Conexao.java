package util;

import java.io.File;
import java.net.URISyntaxException;

public class Conexao {

    private static final String CSV_FILENAME = "supertrunfo_copa.csv";

    public static String getCaminhoCsv() {
        File f = new File("data" + File.separator + CSV_FILENAME);
        if (f.exists()) return f.getAbsolutePath();

        try {
            File jarDir = new File(Conexao.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParentFile();
            f = new File(jarDir, "data" + File.separator + CSV_FILENAME);
            if (f.exists()) return f.getAbsolutePath();
        } catch (URISyntaxException ignored) {}

        throw new RuntimeException("Arquivo não encontrado: data/" + CSV_FILENAME);
    }
}
