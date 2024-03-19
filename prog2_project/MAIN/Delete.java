import java.io.File;

import Validation.PathValidation;

public class Delete 
{
    public static void main(String[] args) {
        String pathString = args[0];
        if (!PathValidation.isValidPath(pathString))
        {
            System.err.println("Error: Invalid path.");
            System.exit(1);
        }
        //else

        File rootDir = new File(pathString);
        deleteAllHTMLS(rootDir);
        System.out.println("\n -- html fájlok törölve --\n");
    }

    private static boolean isHTML(File file)
    {
        return file.getName().toLowerCase().endsWith(".html");
    }

    private static void deleteAllHTMLS(File file)
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (File f : files) 
            {
                deleteAllHTMLS(f);    
            }
        }
        else if(isHTML(file))
        {
            file.delete();
        }
    }
}
