package it.unibo.mvc;

import java.util.*;

/**
 *
 */
public interface Controller {

    public void setNextString(final String nextString);

    public String getNextString();

    public List<String> getHistoryOfPrintedStrings();

    public void printCurrentString();

}
