package Generation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import Objects.Picture;

public class Generate 
{
    public static String listDirectoriesForHtml(List<File> directories, File rootDir, File currentDir)
    {
        StringBuilder sb = new StringBuilder();
        if (directories.isEmpty() || (rootDir.getParent().compareTo(currentDir.getPath().toString()) != 0))
        {
            sb.append("""
                        <li>
                            <a href="../index.html"> << </a>
                        </li>
                    
                    """);
        }

        for (File dir : directories) 
        {
            String li = """
                        <li>
                            <a href="%s/index.html">%s</a>
                        </li>
                
                """;
            sb.append(String.format(li, dir, dir.getName()));
        }
            
        return sb.toString();
    }

    public static String listPicturesForHtml(List<Picture> pictures)
    {
        StringBuilder sb = new StringBuilder();
        for (Picture picture : pictures) 
        {
            String li = """
                        <li>
                            <a href="%s.html">%s</a>
                        </li>
                
                """;
            sb.append(String.format(li, picture.getName(), picture.getFileName()));
        }
            
        return sb.toString();
    }
        
    public static void createIndexHTMLForDirectories(File container, List<File> directories, List<Picture> pictures, File startPage)
    {
        String dirListAsHtml = listDirectoriesForHtml(directories, startPage, container);
        String picsListAsHtml = listPicturesForHtml(pictures);
        String title = container.getName();
        String backToHome = FileUtils.getRelativePath(container, startPage);
        String html = directoryHTML(title, backToHome, dirListAsHtml, picsListAsHtml); //html
        String pathFile = String.format("%s\\index.html", container);

        writeFile(pathFile, html);
    }
        
    public static void createHTMLForPictures(List<Picture> pictures, File startPage)
    {
        for (int i = 0; i < pictures.size(); i++) 
        {
            Picture actual = pictures.get(i);
            Picture previous = (i == 0) ? actual : pictures.get(i - 1);
            Picture next = (i == pictures.size() - 1) ? actual : pictures.get(i + 1);
            String backToHome = FileUtils.getRelativePath(new File(actual.getContainer()), startPage);

            //html
            String html = pictureHTML(actual.getName(), backToHome, previous.getName() + ".html", next.getName() + ".html", actual.getFileName(), actual.getRelative());
            String pathFile = String.format("%s\\%s.html", actual.getContainer(), actual.getName());

            writeFile(pathFile, html);
        }
    }

    public static void writeFile(String container, String output)
    {
        try 
        {
            PrintWriter writer = new PrintWriter(new FileOutputStream(container, false));
            writer.println(output);
            writer.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
    }
    
     public static String directoryHTML(String title, String startPage, String directories, String images)
     {
         String htmlString = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>%s</title>
            </head>
            <body>
                <div style="border-bottom: 2px solid; border-color: black; padding: 5px;">
                    <a href="%s" style="font-size: 25px;" >Start Page</a>
                </div>
            
                <div  style="border-bottom: 2px solid;">
                    <h1 style="font-size: 25px;">Directories:</h1>
                    <ul>
                        %s
                    </ul>
                </div>
                
                <div>
                    <h1 style="font-size: 25px;">Images:</h1>
                    <ul>
                    
                        %s
                    </ul>
                </div>
            </body>
            </html>
             """;

        return String.format(htmlString, title, startPage, directories, images);
    }
            
    

    public static String pictureHTML(String title, String startPage, String elozoKep, String kovetkezoKep, String kepNeve, String kepHelye)
    {
        String htmlString = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>%s</title>
            </head>
            <body>
                <div style="border-bottom: 2px solid; border-color: black; padding: 5px;">
                    <a href="%s" style="font-size: 25px;" >Start Page</a>
                </div>
            
                <a style="display: block; padding-top: 10px;" href="index.html">^^</a>
                <div style="display: flex; align-items: center; padding-top: 10px;">
                
                    <a href="%s"><<</a>
                    <h4 style="margin: 0 10px;">%s</h4>
                    <a href="%s">>></a>
                
                </div>
            
                <a href="%s">
                    <img style="height: 30%%; width: 30%%; padding-top: 10px;" src="%s" alt="">
                </a>
            
            
                
            </body>
            </html>
                """;

        return String.format(htmlString, title, startPage, elozoKep, kepNeve, kovetkezoKep, kovetkezoKep, kepHelye);
    }
}
