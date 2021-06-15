package Commands;
public interface Commands {
    String getName();

    String getUsage();

    String getDescription();

    boolean execute(String commandStringArgument, Object commandObjectArgument);
}

//public interface Commands {
//    boolean execute(String stringArgument, Object objectArgument);
//
//
//}
