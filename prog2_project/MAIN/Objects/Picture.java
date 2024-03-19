package Objects;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Generation.FileUtils;

public class Picture 
{
    private String name;
    private String url;
    private String container;

    public Picture(File picture, File startPage)
    {
        this.name = picture.getName().toString();
        this.url = FileUtils.getRelativePath(startPage, picture);
        this.container = picture.getParent();

    }

    // kiterjesztéssel együtt
    public String getFileName()
    {
        return this.name;
    }

    // kiterjesztés nélkül
    public String getName()
    {
        return this.name.split("\\.")[0];
    }

    public String getRelative()
    {
        return this.url;
    }

    public String getContainer()
    {
        return this.container;
    }

    // kigyujti a kepeket egy adott könyvtárból
    public static List<Picture> getPictures(File file, File startPage)
    {
        File files[] = file.listFiles();
        List<Picture> pictures = new ArrayList<>();

        for (File f : files) 
        {
            if (f.getName().toLowerCase().endsWith(".jpg") ||
                f.getName().toLowerCase().endsWith(".jpeg") ||
                f.getName().toLowerCase().endsWith(".png") ||
                f.getName().toLowerCase().endsWith(".gif"))
            {
                Picture act = new Picture(f, startPage);
                pictures.add(act);
            }    
        }
        Collections.sort(pictures, Comparator.comparing(n -> n.getName(), String.CASE_INSENSITIVE_ORDER));
        return pictures;
        
    }
}
