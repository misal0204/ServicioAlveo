package procesos;

import DBOperaciones.DBCrud;
import Log.LogEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FindTxt {

    public void findFile() {
        // Aquí la carpeta que queremos explorar
        String path = "D:/Harisa/ServicioTxt/archivos";
        String files;
        int count = 0;

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                files = listOfFile.getName();
                if (files.endsWith(".AHC")) {
                    int c = files.indexOf(".");
                    String newFile = files.substring(0, c);
                    System.out.println(newFile);
                    count++;
                }
            }
        }
        System.out.println("Fin " + count);
    }

    public void findFileForCopy(String fileName) {
        // Aquí la carpeta que queremos explorar
        String path = "D:/Harisa/ServicioTxt/archivos";
        String files;

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                files = listOfFile.getName();
                int c = files.indexOf(".");
                String newFile = files.substring(0, c);

                if (newFile.equals(fileName)) {

                    System.out.println(files);
                    copyFiles(files);

                }
            }
        }
    }

    public List getFilePath() {
        // Aquí la carpeta que queremos explorar
        String path = "D:/Harisa/ServicioTxt/archivos";
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

    public List ReadFile(String archivo) {
        String[] getCampos = {"NOMBRE", "MUESTRA", "P" + '\t', "L", "G" + '\t', "W" + '\t', "P/L" + '\t'};
        List getResult = new ArrayList<>();
        String PATH = "D:/Harisa/ServicioTxt/archivos" + "/" + archivo + ".AHC";

        int e = 0;
        try {
            FileInputStream fstream = new FileInputStream(PATH);
            try (DataInputStream entrada = new DataInputStream(fstream)) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                String strLinea;
                int count = 0;
                int index = 0;
                int c = 0;

                while ((strLinea = buffer.readLine()) != null) {
                    e = strLinea.indexOf(getCampos[count]);
                    if (e != -1) {
                        switch (count) {
                            case 0:
                                new LogEvent().LogAlveo("Resultados");
                                new LogEvent().LogAlveo(strLinea);
                                index = strLinea.indexOf(':');
                                String parcialString = strLinea.substring(index + 1).trim();
                                int p = parcialString.indexOf(".");
                                String newFile = parcialString.substring(0, p);
                                getResult.add(newFile);
                                break;
                            case 1:
                                new LogEvent().LogAlveo(strLinea);
                                index = strLinea.indexOf('\t');
                                String muestra = strLinea.substring(index + 1).trim();
                                getResult.add(muestra);
                                break;
                            case 2:
                                new LogEvent().LogAlveo(strLinea);
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
                                new LogEvent().LogAlveo(strLinea);
                                index = strLinea.indexOf('\t');
                                String newL = strLinea.substring(index + 1).trim();
                                String getL = "";
                                c = 0;
                                while (newL.charAt(c) != '\t') {
                                    getL += newL.charAt(c);
                                    c++;
                                }
                                getResult.add(getL.trim());
                                break;
                            case 4:
                                new LogEvent().LogAlveo(strLinea);
                                index = strLinea.indexOf('\t');
                                String getG = strLinea.substring(index + 1).trim();
                                getResult.add(getG.trim());
                                break;
                            case 5:
                                new LogEvent().LogAlveo(strLinea);
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
                                new LogEvent().LogAlveo(strLinea);
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
                new LogEvent().LogAlveo("Insertando resultados");
                new DBCrud().insertValues(getResult);
                findFileForCopy(archivo);

            }
        } catch (Exception ex) {
            new LogEvent().LogAlveo("Error en ReadFile: " + ex.getMessage());
            System.err.println("Ocurrio un error ReadFile: " + ex.getMessage() + ": " + ex.getLocalizedMessage());
        }

        return getResult;
    }

    public void ProcessFile() {
        List files = getFilePath();

        for (int i = 0; i < files.size(); i++) {
            new File_Process().FileSearch(String.valueOf(files.get(i)));
        }
    }

    public void copyFiles(String fileName) {

        String nfile = fileName;
        String PATH_IN = "D:/Harisa/ServicioTxt/archivos/";
        String PATH_OUT = "D:/Harisa/ServicioTxt/historial/";
        PATH_IN += nfile;
        PATH_OUT += nfile;

        boolean alreadyExists = new File(PATH_OUT).exists();

        Path FROM = Paths.get(PATH_IN);
        Path TO = Paths.get(PATH_OUT);

        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };

        if (alreadyExists) {
            try {

                Files.deleteIfExists(TO);
                Files.copy(FROM, TO, options);
            } catch (Exception e) {
                System.err.println("Error en copia de registro");
            } catch (NoClassDefFoundError e) {
                System.err.println("Error en clases file_process copy: " + e.getMessage());
            }
        } else {
            try {

                Files.copy(FROM, TO, options);
                new LogEvent().LogAlveo("Copia de archivo: " + fileName);
                new LogEvent().LogAlveo("Fin de operación");
                new LogEvent().LogAlveo("--------------**************--------------");
            } catch (Exception e) {
                new LogEvent().LogAlveo("Error en creación de documento: " + e.getMessage());
                System.err.println("Error en creacion de registro" + e.getMessage());
            } catch (NoClassDefFoundError e) {
                new LogEvent().LogAlveo("Error en clases file_process create: " + e.getMessage());
                System.err.println("Error en clases file_process create: " + e.getMessage());
            }
        }
    }

}
