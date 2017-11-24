package client.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ikram
 * Date: 11/23/17
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class CmdLine {
    private final String enteredLine;

    /**
     * Creates a new instance representing the specified line.
     *
     * @param enteredLine A line that was entered by the user.
     */
    CmdLine(String enteredLine) {
        this.enteredLine = enteredLine;
    }

    /**
     * @return The entire user input, without any modification.
     */
    String getUserInput() {
        return enteredLine.toUpperCase();
    }

}
