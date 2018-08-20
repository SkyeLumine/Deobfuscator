package lightleaf.deobfuscator.tools.csv;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class CompilerErrorParserTest {

    private CompilerErrorParser compilerErrorConverter;

    @Before
    public void setup(){
        compilerErrorConverter = new CompilerErrorParser();
    }

    @Test
    public void testValidateInfoLine() {
        Boolean isInfoLineValid = Boolean.FALSE;
        try {
            final Method validateInfoLine = compilerErrorConverter.getClass().getDeclaredMethod("validateInfoLine", String.class);
            validateInfoLine.setAccessible(true);
            isInfoLineValid = (Boolean) validateInfoLine.invoke(compilerErrorConverter, "src\\client.java:42: error: <identifier> expected");
        } catch(final Exception e){
            System.err.println("Trouble working with reflected method");
            e.printStackTrace();
        }

        assertTrue(isInfoLineValid);
    }

}