package serviciotxt;

import procesos.FindTxt;

public class ServicioTXT {

    public static int i = 0;

    public static void main(String[] args) {
        /*FindTxt ftxt = new FindTxt();
        ftxt.findFile();*/
        //ftxt.ProcessFile();
        
        //TimerTask Para lectura de datos
        LecturaDatos ld=new LecturaDatos();
        ld.ServicioRead();        
        
        //Timertaks para copia de archivos
        CopiaDatos cd=new CopiaDatos();
        cd.ServicioCopy();
    }

}
