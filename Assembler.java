import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
public class Assembler {
    private int varLoc = 16;
    private String currentCommand = "";
    private SymbolTable table;
    public Assembler() {
        table = new SymbolTable();
    }
    public String toBinary(int num) {
        try{
            String str = Integer.toBinaryString(num);

            while(str.length() < 15) {
                str = "0" + str;
            }
            return "0" + str;
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return "";
        }

    }
    public void parse(String path) {
        Parser parser = new Parser(new File(path));
        int lineNum = 0;
        while(parser.hasMoreCommands()) {
            if(parser.isEmpty()
            || parser.onlyNewLine()
            || parser.isComment()) {
                parser.advance();
                continue;
            }
            if(parser.commandType() == Type.L_COMMAND) {
                table.addEntry(parser.symbol(), lineNum);
            }
            else{
                lineNum++;
            }
            parser.advance();
        }
        parser.reset();

        StringBuilder sb = new StringBuilder();
        // second walk through
        int line = 0;
        while(parser.hasMoreCommands()) {
            if(parser.isEmpty()
            || parser.onlyNewLine()
            || parser.isComment()) {
                parser.advance();
                line++;
                continue;

            }
            parser.clean();
            if(parser.commandType() == Type.A_COMMAND) {
                if(!parser.isNumeric()){
                    if(table.contains(parser.symbol())) {
                        sb.append(toBinary(table.GetAddress(parser.symbol())));
                        sb.append("\n");
                    }
                    else{
                        sb.append(toBinary(varLoc));
                        sb.append("\n");
                        table.addEntry(parser.symbol(), varLoc++);
                    }
                }
                else{
                    sb.append(toBinary(Integer.parseInt(parser.symbol())));
                    sb.append("\n");

                }
            }
            if(parser.commandType() == Type.C_COMMAND){
                String comp = parser.comp();
                String dest = parser.dest();
                String jump = parser.jump();
                String instruction = "111"+ comp + dest + jump;
                sb.append(instruction);
                sb.append("\n");
            }
            line++;
            parser.advance();
        }

        try {
            FileWriter fw = new FileWriter(new File(path.replace(".asm", ".hack")));
            fw.write(sb.toString());
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    public boolean equalFile(String p1, String p2) {
        try {
            List<String> exe1 = Files.readAllLines(Paths.get(p1), StandardCharsets.US_ASCII);
            List<String> exe2 = Files.readAllLines(Paths.get(p2), StandardCharsets.US_ASCII);
            for(int i = 0; i < exe1.size(); i++) {
                if(!exe1.get(i).equals(exe2.get(i))) {
                    System.out.println(exe1.get(i) + " " + i);
                    System.out.println(exe2.get(i));
                }
            }
            return exe1.equals(exe2);
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        String addPath = "add/Add.asm";
        String maxPath = "max/Max.asm";
        String maxLPath = "max/MaxL.asm";
        String pongPath = "pong/Pong.asm";
        String pongLPath = "pong/PongL.asm";
        String rectPath =  "rect/Rect.asm";
        String rectLPath = "rect/RectL.asm";


        Assembler assembler = new Assembler();

        assembler.parse(addPath);
    }
}