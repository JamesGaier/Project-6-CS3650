public class Code {
    public String dest(String command) {
        if(command.charAt(1) == ';') return "000";
        if(command.charAt(0) == 'M'
            && command.charAt(1) == 'D') return "011";
        if(command.charAt(0) == 'A'
            && command.charAt(1) == 'M'
            && command.charAt(1) == 'D') return "111";
        else if(command.charAt(0) == 'A'
            && command.charAt(1) == 'M') return "101";
        else if(command.charAt(0) == 'A'
            && command.charAt(1) == 'D') return "110";
        if(command.charAt(1) == '=') {
            if(command.charAt(0) == 'M') return "001";
            else if(command.charAt(0) == 'D') return "010";
            else if(command.charAt(0) == 'A') return "100";
        }
        return "";
    }

    // computes comp
    public String comp(String command) {
        String cmd = "";
        if(command.contains(";")) {
            cmd = command.substring(0,1);
        }
        else{
            cmd = command.substring(command.indexOf("=")+1);
        }

        if(cmd.equals("D")) {
            return "0001100";
        }
        else if(cmd.equals("A")) {
            return "0110000";
        }
        else if(cmd.equals("!D")) {
            return "0001101";
        }
        else if(cmd.equals("!A")) {
            return "0110001";
        }
        else if(cmd.equals("-D")) {
            return "0001111";
        }
        else if(cmd.equals("-A")) {
            return "0110011";
        }
        else if(cmd.equals("D+1")) {
            return "0011111";
        }
        else if(cmd.equals("A+1")) {
            return "0110111";
        }
        else if(cmd.equals("D-1")) {
            return "0001110";
        }
        else if(cmd.equals("A-1")) {
            return "0110010";
        }
        else if(cmd.equals("D+A")) {
            return "0000010";
        }
        else if(cmd.equals("D-A")) {
            return "0010011";
        }
        else if(cmd.equals("A-D")) {
            return "0000111";
        }
        else if(cmd.equals("D&A")) {
            return "0000000";
        }
        else if(cmd.equals("D|A")) {
            return "0010101";
        }
        else if(cmd.equals("D&M")) {
            return "1000000";
        }
        else if(cmd.equals("M")) {
            return "1110000";
        }
        else if(cmd.equals("!M")) {
            return "1110001";
        }
        else if(cmd.equals("-M")) {
            return "1110011";
        }
        else if(cmd.equals("M+1")) {
            return "1110111";
        }
        else if(cmd.equals("M-1")) {
            return "1110010";
        }
        else if(cmd.equals("D+M")) {
            return "1000010";
        }
        else if(cmd.equals("D-M")) {
            return "1010011";
        }
        else if(cmd.equals("M-D")) {
            return "1000111";
        }
        else if(cmd.equals("D|M")) {
            return "1010101";
        }
        else if(cmd.equals("0")) {
            return "0101010";
        }
        else if(cmd.equals("1")) {
            return "0111111";
        }
        else if(cmd.equals("-1")) {
            return "0111010";
        }
        return "";
    }
    // computes jump
    public String jump(String command) {
        if(!command.contains(";")) return "000";

        command = command.substring(command.indexOf(";")+1);

        if(command.equals("JGT")) return "001";
        else if(command.equals("JEQ")) return "010";
        else if(command.equals("JGE")) return "011";
        else if(command.equals("JLT")) return "100";
        else if(command.equals("JNE")) return "101";
        else if(command.equals("JLE")) return "110";
        else if(command.equals("JMP")) return "111";

        return "";
    }
}