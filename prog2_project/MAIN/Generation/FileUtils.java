package Generation;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Objects.Picture;

public class FileUtils
{   
    public static String getRelativePath(File baseDirectory, File file) {
        Path basePath = baseDirectory.toPath().toAbsolutePath();
        Path filePath = file.toPath().toAbsolutePath();
        
        Path relativePath = basePath.relativize(filePath);
        
        return relativePath.toString();
    }

    public static void loopDirectoriesRecursively(File file, File startPage)
    {
        if (file.isDirectory())
        {
            System.out.println("Dir: " + file.getName());
            File files[] = file.listFiles();
            List<Picture> pictures = Picture.getPictures(file, file);
            List<File> directories = FileUtils.getDirectories(file);
            Generate.createIndexHTMLForDirectories(file, directories, pictures, startPage);
            Generate.createHTMLForPictures(pictures, startPage);

            for (File f : files) 
            {
                if (f.isDirectory())
                {
                    loopDirectoriesRecursively(f, startPage);
                }
            }

        }
    }

    public static List<File> getDirectories(File file)
    {
        File[] files = file.listFiles();
        List<File> directories = new ArrayList<>();
        for (File f : files) 
        {
            if (f.isDirectory())
            {
                String relativePath = FileUtils.getRelativePath(file, f);
                directories.add(new File(relativePath));
            }    
        }

        return directories;
    }
}
