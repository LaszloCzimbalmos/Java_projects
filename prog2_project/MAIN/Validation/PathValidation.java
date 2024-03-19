package Validation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidation {
    public static boolean isValidPath(String pathString) 
    {
        Path path = Paths.get(pathString);
        return Files.exists(path) && Files.isDirectory(path);
    }
}
