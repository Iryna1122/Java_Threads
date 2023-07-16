import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        String text = "Hello world of Java!";
        try(FileOutputStream fos=new FileOutputStream("D://SomeDir//notes.txt"))
        {
            byte[] buffer = text.getBytes();
            fos.write(buffer, 0, buffer.length);
            System.out.println("The file has been written");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

           FileInputStream fin=null;
        try
        {
            fin = new FileInputStream("D://SomeDir//notes.txt");
            int i=-1;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}






