package Interaction;


import java.io.Serializable;
public class Request implements Serializable{
    // class for getting request
    private String commmandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;
    public Request (String commmandName, String commandStringArgument,Serializable commandObjectArgument){
        this.commmandName=commmandName;
        this.commandStringArgument=commandStringArgument;
        this.commandObjectArgument=commandObjectArgument;
    }

    public Request(String commmandName, String commandStringArgument) {
       this(commmandName,commandStringArgument,null);
    }
    public Request(){
        this("","");
    }

    public String getCommmandName() {
        return commmandName;
    }

    public String getCommandStringArgument() {
        return commandStringArgument;
    }

    public Serializable getCommandObjectArgument() {
        return commandObjectArgument;
    }
    public boolean isEmpty(){
        return commmandName.isEmpty() && commandStringArgument.isEmpty() && commandObjectArgument==null;
    }

    @Override
    public String toString() {
        return "Request; [ " +
                  commmandName + "," +
                 commandStringArgument + "," +
                 commandObjectArgument +"]"
                ;
    }
}
