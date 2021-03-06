import java.util.*;
import java.io.*;

public class IOPersona
{
    public static void escribirPersonas(Collection personas)
    {
        try
        {
            File fichero = new File("personas.obj");
            if(fichero.exists())
                javax.swing.JOptionPane.showMessageDialog(null, "El fichero se sobreescribirá", "Aviso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            FileOutputStream fos = new FileOutputStream("personas.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Iterator it = personas.iterator();
            while(it.hasNext())
                oos.writeObject(it.next());

            oos.close();
            fos.close();
        }
        catch(IOException ioe)
        {
            //ioe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "No se pudo escribir en el fichero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos/personas.obj"));
    }

    public static Collection leerPersonas()
    {
        Collection personas = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try
        {
            fis = new FileInputStream ("personas.obj");
            ois = new ObjectInputStream(fis);
            while(true)
                personas.add(ois.readObject());
        }
        catch(EOFException eof)
        {
            try
            {
                ois.close();
                fis.close();
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();       
            }
        }
        catch(ClassNotFoundException ioe)
        {
           //ioe.printStackTrace();
           //System.out.println("Clase no encontrada");
            javax.swing.JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }   
        catch(FileNotFoundException fnfe)
        {
           //ioe.printStackTrace();
           //System.out.println("No se encuentra el fichero");
            javax.swing.JOptionPane.showMessageDialog(null, "No se encuentra el fichero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe)
        {
           //ioe.printStackTrace();
           //System.out.println("Error leyendo del fichero");
            javax.swing.JOptionPane.showMessageDialog(null, "Error de lectura del fichero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        return personas;
    }    
}