package lightleaf.deobfuscator.tools.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CompilerErrorParser {

    public List<CompilationError> parseLines(final List<String> errorLines) throws NoSuchElementException {
        final List<CompilationError> compilationErrors = new ArrayList<>();

        int lineNumber = 0;

        final Iterator<String> iterator = errorLines.iterator();
        while(iterator.hasNext()){
            final String infoLine, codeLine, arrowLine;

            ++lineNumber;
            if(!iterator.hasNext()){
                throw new NoSuchElementException("FAILED TO EXTRACT INFORMATION LINE :: LINE NUMBER " + lineNumber);
            }
            infoLine = iterator.next();

            ++lineNumber;
            if(!iterator.hasNext()){
                throw new NoSuchElementException("FAILED TO EXTRACT CODE LINE :: LINE NUMBER " + lineNumber);
            }
            codeLine = iterator.next();

            ++lineNumber;
            if(!iterator.hasNext()){
                throw new NoSuchElementException("FAILED TO EXTRACT ARROW LINE :: LINE NUMBER " + lineNumber);
            }
            arrowLine = iterator.next();

            final CompilationError compilationError = new CompilationError.Builder().
                                                                    buildInfoLine(infoLine).
                                                                    buildCodeLine(codeLine).
                                                                    buildArrowLine(arrowLine).
                                                                    build();
            compilationErrors.add(compilationError);
        }

        return compilationErrors;
    }
}
