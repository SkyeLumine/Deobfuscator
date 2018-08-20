package lightleaf.deobfuscator.tools.csv;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CompilerErrorFetcherTest {

    @Test
    public void testFetchLines(){
        List<String> lines = null;
        try {
            lines = new CompilerErrorFetcher("res/compiler_error_lines.txt").fetchLines();
        } catch(final IOException e){
            System.err.println("Failed to fetch lines.");
        }

        assertNotNull(lines);
        assertTrue(lines.size() > 0);
    }

    @Test
    public void testFetchLinesWithError(){
        List<String> lines = null;
        try {
            lines = new CompilerErrorFetcher("res/compiler_error_lies.txt").fetchLines();
        } catch(final IOException e){}

        assertNull(lines);
    }

}