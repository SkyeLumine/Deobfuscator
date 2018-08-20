package lightleaf.deobfuscator.tools.csv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompilationError {

    private final String sourceFile;
    private final String lineNumber;
    private final String description;
    private final String codeLine;
    private final String arrowLine;

    private CompilationError(final String sourceFile, final String lineNumber, final String description, final String codeLine, final String arrowLine){
        this.sourceFile = sourceFile;
        this.lineNumber = lineNumber;
        this.description = description;
        this.codeLine = codeLine;
        this.arrowLine = arrowLine;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public String getArrowLine() {
        return arrowLine;
    }

    public static class Builder {
        private String sourceFile;
        private String lineNumber;
        private String description;
        private String codeLine;
        private String arrowLine;

        public Builder buildInfoLine(final String infoLine) throws IllegalArgumentException {
            Pattern regex = Pattern.compile("(.+\\.java):(\\d+): error: (.+)");
            Matcher matcher = regex.matcher(infoLine);

//            if(!matcher.matches()){
//                throw new IllegalArgumentException("Info line does not match expected format: " + infoLine);
//            }
            matcher.find();

            try{
                sourceFile = matcher.group(1);
            } catch(final IndexOutOfBoundsException | IllegalStateException e){
                throw new IllegalArgumentException("Info line is missing source file: " + infoLine);
            }

            try{
                lineNumber = matcher.group(2);
            } catch(final IndexOutOfBoundsException | IllegalStateException e){
                throw new IllegalArgumentException("Info line is missing line number: " + infoLine);
            }

            try{
                description = matcher.group(3);
            } catch(final IndexOutOfBoundsException | IllegalStateException e){
                throw new IllegalArgumentException("Info line is missing description: " + infoLine);
            }

            return this;
        }

        public Builder buildCodeLine(final String codeLine){
            if(codeLine.length() > 0) {
                this.codeLine = codeLine;
            } else {
                throw new IllegalArgumentException("FAILED TO PARSE COMPILER ERROR CODE LINE : " + codeLine);
            }
            return this;
        }

        public Builder buildArrowLine(final String arrowLine){
            Pattern regex = Pattern.compile(".*?\\^.*");
            Matcher matcher = regex.matcher(arrowLine);

            if(matcher.find()) {
                this.arrowLine = arrowLine;
            } else {
                throw new IllegalArgumentException("FAILED TO PARSE COMPILER ERROR ARROW LINE : " + arrowLine);
            }

            return this;
        }

        public CompilationError build(){
            return new CompilationError(sourceFile, lineNumber, description, codeLine, arrowLine);
        }
    }
}
