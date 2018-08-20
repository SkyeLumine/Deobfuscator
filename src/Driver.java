import lightleaf.deobfuscator.tools.csv.CompilerErrorParser;
import lightleaf.deobfuscator.tools.csv.CompilerErrorFetcher;
import lightleaf.deobfuscator.tools.csv.CompilerErrorWriter;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) {
        final CompilerErrorFetcher fetcher = new CompilerErrorFetcher("res/complier_error_lines.txt");
        final CompilerErrorParser converter = new CompilerErrorParser();
        final CompilerErrorWriter writer = new CompilerErrorWriter();

        try {
            writer.writeLines(converter.parseLines(fetcher.fetchLines()));
        } catch(final IOException e){
            System.err.println("Failed to process compiler error lines.");
            e.printStackTrace();
        }
    }
}
