package ar.ro.utils;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class Utils {
    private Utils(){
    }

    public static Stream<String> forEachLinesFromFile(String filePath) throws IOException, URISyntaxException {
        Path path = Path.of(Objects.requireNonNull(Utils.class.getClassLoader().getResource(filePath)).toURI());
        return Files.lines(path);
    }
}