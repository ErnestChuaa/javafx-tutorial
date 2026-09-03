public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the message entered by the user
     * @return Duke's response
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
