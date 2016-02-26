package serviciotxt;

import java.util.List;
import procesos.File_Process;
import procesos.FindTxt;

public class ServicioTXT {

    public static void main(String[] args) {
        FindTxt ftxt = new FindTxt();

        new File_Process().FileProcessRead();
        //ftxt.findFile();
        /*List list= ftxt.getFilePath();
        
         for (int i = 0; i < list.size(); i++) {
         System.out.println(list.get(i));  
         }
         System.out.println(list.size());*/
    }
}
