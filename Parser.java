import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
enum Type {
    A_COMMAND, C_COMMAND, L_COMMAND
}
public class Parser {
    private BufferedReader br;
    private String currentCommand;
    private File programFile;
    private Code code;
    private int i;
    // read the program
    public Parser(File programFile) {
        try{
            code = new Code();
            this.programFile = programFile;
            br = new BufferedReader(new FileReader(programFile));
            currentCommand = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void reset() {
        try{
            br = new BufferedReader(new FileReader(programFile));
            currentCommand = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    // is there another command
    public boolean hasMoreCommands() {
        try{
            if(currentCommand == null) {
                br.close();
                return false;
            }
            else {
                return true;
            }
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void clean() {
        if(currentCommand.contains("//")) {
            currentCommand = currentCommand.substring(0, currentCommand.indexOf("//"));
        }
        currentCommand = currentCommand.replace(" ", "");
    }
    public boolean isComment() {
        return (currentCommand.substring(0,2).equals("//"));
    }
    public boolean isEmpty() {
        return currentCommand.length() == 0;
    }
    public boolean onlyNewLine() {
        String newline = System.getProperty("line.separator");
        return currentCommand.equals(newline);
    }
    // move the next command forward
    public void advance() {
        try{
            i+= 1;
            currentCommand = br.readLine();
        } catch(IOException e) {

            e.printStackTrace();
        }
    }
    // gets command type
    public Type commandType() {
        String command = currentCommand;
        if(command.charAt(0) == '@') return Type.A_COMMAND;
        else if(command.charAt(0) == '('
                && command.charAt(command.length() - 1) == ')')
                return Type.L_COMMAND;
        else return Type.C_COMMAND;
    }
    // returns the symbol
    public String symbol() {

        String command = currentCommand;
        if(commandType() == Type.A_COMMAND) return command.substring(1,command.length());
         else if(commandType() == Type.L_COMMAND) return command.substring(1, command.length() - 1);
        return "";
    }
    // returns dest
    public String dest() {
        return code.dest(currentCommand);
    }
    // return comp
    public String comp() {

        return code.comp(currentCommand);
    }
    //returns jump
    public String jump() {
        return code.jump(currentCommand);
    }
    public boolean isNumeric() {
        try {
            Integer.parseInt(symbol());

            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}