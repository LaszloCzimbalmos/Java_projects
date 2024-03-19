import java.io.File;

import Generation.FileUtils;
import Validation.PathValidation;

public class Main
{
    public static void main(String[] args) throws Exception 
    {
        String pathString = args[0];
        if (!PathValidation.isValidPath(pathString))
        {
            System.err.println("Error: Invalid path.");
            System.exit(1);
        }
        //else

        File rootDir = new File(pathString);
        File startPage = new File(rootDir.getPath() + "\\index.html");
        FileUtils.loopDirectoriesRecursively(rootDir, startPage);
        System.out.println("\n -- html fájlok legenerálva --\n");
        
    }
}
