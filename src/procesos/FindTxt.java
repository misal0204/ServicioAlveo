package procesos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
                if (files.endsWith(".AHC")) {
                    //System.out.println(files);
                    int c = files.indexOf(".");
                    String newFile = files.substring(0, c);
                    System.out.println(newFile);
                    count++;
                }
            }
        }
        System.out.println("Fin " + count);
    }

    public List getFilePath() {
        // Aquí la carpeta que queremos explorar
        String path = "D:\\Harisa\\Servicio txt y java\\archivos";
        String files;

        int count = 0;
        List listaArchivos = new ArrayList<>();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                files = listOfFile.getName();
                if (files.endsWith(".AHC")) {
                    int c = files.indexOf(".");
                    String newFile = files.substring(0, c);

                    listaArchivos.add(newFile);
                    count++;
                }
            }
        }
        return listaArchivos;
    }

    public List ReadFile() {
        String[] getCampos = {"NOMBRE", "MUESTRA", "P" + '\t', "L", "G" + '\t', "W" + '\t', "P/L" + '\t'};
        List getResult = new ArrayList<>();

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

                                String parcialString = strLinea.substring(index + 1).trim();
                                int p = parcialString.indexOf(".");

                                String newFile = parcialString.substring(0, p);

                                getResult.add(newFile);

                                break;
                            case 1:
                                index = strLinea.indexOf('\t');
                                String muestra = strLinea.substring(index + 1).trim();
                                getResult.add(muestra);
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

                                getResult.add(getP.trim());
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
                                getResult.add(getL.trim());
                                break;
                            case 4:
                                index = strLinea.indexOf('\t');
                                String getG = strLinea.substring(index + 1).trim();
                                System.out.println("G: " + getG.trim());
                                getResult.add(getG.trim());
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
                                getResult.add(getW.trim());
                                break;
                            case 6:
                                index = strLinea.indexOf('\t');
                                String getPL = strLinea.substring(index + 1).trim();
                                getResult.add(getPL.trim());
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

        return getResult;
    }
}
