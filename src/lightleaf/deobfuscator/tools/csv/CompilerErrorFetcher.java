package lightleaf.deobfuscator.tools.csv;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CompilerErrorFetcher {

    final Path path;

    public CompilerErrorFetcher(final String filePath){
        path = FileSystems.getDefault().getPath(filePath);
    }

    public List<String> fetchLines() throws IOException {
        return Files.readAllLines(path);
    }
}
