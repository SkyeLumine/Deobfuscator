package lightleaf.deobfuscator.tools.csv;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Paul on 8/19/2018.
 */
public class CompilationErrorTest {


    @Test
    public void testBuildInfoLine(){
        try {
            final CompilationError.Builder builder = new CompilationError.Builder();

            final Class<?> compilationErrorBuilder = builder.getClass();

            final Field sourceFileField = compilationErrorBuilder.getDeclaredField("sourceFile");
            sourceFileField.setAccessible(true);

            final Field lineNumberField = compilationErrorBuilder.getDeclaredField("lineNumber");
            lineNumberField.setAccessible(true);

            final Field descriptionField = compilationErrorBuilder.getDeclaredField("description");
            descriptionField.setAccessible(true);

            builder.buildInfoLine("src\\client.java:42: error: <identifier> expected");

            final String sourceFile = (String) sourceFileField.get(builder);
            final String lineNumber = (String) lineNumberField.get(builder);
            final String description = (String) descriptionField.get(builder);

            assertEquals(sourceFile, "src\\client.java");
            assertEquals(lineNumber, "42");
            assertEquals(description, "<identifier> expected");

        } catch(final NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testBuildInvalidInfoLine(){
        try {
            final CompilationError.Builder builder = new CompilationError.Builder();

            final Class<?> compilationErrorBuilder = builder.getClass();

            final Field sourceFileField = compilationErrorBuilder.getDeclaredField("sourceFile");
            sourceFileField.setAccessible(true);

            final Field lineNumberField = compilationErrorBuilder.getDeclaredField("lineNumber");
            lineNumberField.setAccessible(true);

            final Field descriptionField = compilationErrorBuilder.getDeclaredField("description");
            descriptionField.setAccessible(true);

            builder.buildInfoLine("src\\.java:42: error: <identifier> expected");

            final String sourceFile = (String) sourceFileField.get(builder);
            final String lineNumber = (String) lineNumberField.get(builder);
            final String description = (String) descriptionField.get(builder);

            assertEquals("src\\client.java", sourceFile);
            assertEquals(lineNumber, "42");
            assertEquals(description, "<identifier> expected");

        } catch(final NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

}