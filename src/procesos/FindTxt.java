package procesos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FindTxt {

    public void findFile() {
        // Aquí la carpeta que queremos explorar
        String path = "D:\\Harisa\\Servicio txt y java\\archivos";
        String files;
        int count = 0;

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                files = listOfFile.getName();
                if (files.endsWith(".txt") || files.endsWith(".AHC")) {
                    System.out.println(files);
                    count++;
                }
            }
        }
        System.out.println("Fin " + count);
    }

    public String[] ReadFile() {
        String[] getCampos = {"NOMBRE", "MUESTRA", "P" + '\t', "L", "G" + '\t', "W" + '\t', "P/L" + '\t'};
        String[] getResult = new String[7];
        int e = 0;
        try {
            FileInputStream fstream = new FileInputStream("D:\\Harisa\\Servicio txt y java\\archivos\\10060001.AHC");
            try (DataInputStream entrada = new DataInputStream(fstream)) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                String strLinea;
                int count = 0;
                int index = 0;
                int c = 0;
                while ((strLinea = buffer.readLine()) != null) {

                    e = strLinea.indexOf(getCampos[count]);

                    if (e != -1) {
                        //System.out.println(strLinea);
                        switch (count) {
                            case 0:
                                index = strLinea.indexOf(':');
                                System.out.println(strLinea.substring(index + 1).trim());
                                getResult[count] = strLinea.substring(index + 1).trim();
                                break;
                            case 1:
                                index = strLinea.indexOf('\t');
                                System.out.println(strLinea.substring(index + 1).trim());
                                getResult[count] = strLinea.substring(index + 1).trim();
                                break;
                            case 2:
                                index = strLinea.indexOf('\t');
                                String newP = strLinea.substring(index + 1).trim();
                                String getP = "";
                                c = 0;
                                while (newP.charAt(c) != '\t') {
                                    getP += newP.charAt(c);
                                    c++;
                                }
                                System.out.println("P: " + getP.trim());
                                getResult[count] = getP.trim();
                                break;
                            case 3:
                                index = strLinea.indexOf('\t');
                                String newL = strLinea.substring(index + 1).trim();
                                String getL = "";
                                c = 0;
                                while (newL.charAt(c) != '\t') {
                                    getL += newL.charAt(c);
                                    c++;
                                }
                                System.out.println("L: " + getL.trim());
                                getResult[count] = getL.trim();
                                break;
                            case 4:
                                index = strLinea.indexOf('\t');
                                String getG = strLinea.substring(index + 1).trim();
                                System.out.println("G: " + getG.trim());
                                getResult[count] = getG.trim();
                                break;
                            case 5:
                                index = strLinea.indexOf('\t');
                                String newW = strLinea.substring(index + 1).trim();
                                String getW = "";
                                c = 0;
                                while (newW.charAt(c) != '\t') {
                                    getW += newW.charAt(c);
                                    c++;
                                }
                                System.out.println("W: " + getW.trim());
                                getResult[count] = getW.trim();
                                break;
                            case 6:
                                index = strLinea.indexOf('\t');
                                String getPL = strLinea.substring(index + 1).trim();
                                System.out.println("PL: " + getPL.trim());
                                getResult[count] = getPL.trim();
                                break;
                        }
                        count++;

                        if (!(count < getCampos.length)) {
                            count = 0;
                        }
                    }
                }
                //System.out.println("lineas: " + count);

            }
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage() + ": " + ex.getLocalizedMessage());
        }

        for (String result : getResult) {
            System.out.print(result + " - ");
        }

        return getResult;
    }
}
