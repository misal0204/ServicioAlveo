package serviciotxt;

import procesos.File_Process;
import procesos.FindTxt;

public class ServicioTXT {

    public static void main(String[] args) {
        FindTxt ftxt= new FindTxt();
        
        //new File_Process().FileProcessRead();
        ftxt.findFile();
    }
}
