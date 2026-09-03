public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    private String commandType;

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the message entered by the user
     * @return Duke's response
     */
    public String getResponse(String input) {
        commandType = classifyCommand(input);
        return "Duke heard: " + input;
    }

    /**
     * Returns the command type identified for the most recent message.
     *
     * @return a tutorial command type, or {@code null} for ordinary chat
     */
    public String getCommandType() {
        return commandType;
    }

    private String classifyCommand(String input) {
        if (input == null) {
            return null;
        }

        String command = input.trim().toLowerCase();
        if (command.startsWith("todo ")
                || command.startsWith("deadline ")
                || command.startsWith("event ")) {
            return "AddCommand";
        }
        if (command.startsWith("mark ") || command.startsWith("unmark ")) {
            return "ChangeMarkCommand";
        }
        if (command.startsWith("delete ")) {
            return "DeleteCommand";
        }
        return null;
    }
}
