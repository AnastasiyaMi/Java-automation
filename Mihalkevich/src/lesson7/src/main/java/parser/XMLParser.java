package parser;

import utilities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class XMLParser {

    //lines from file
    private ListADT<String> lines;

    //merged one string document
    private String document;

    //current parsing position in the document
    private int currentParsingPosition;

    //list of parsing errors
    private ListADT<String> errors;

    //stack to store parents of current node
    private StackADT<XMLTag> currentNodePath;

    //root element of parsed structure
    private XMLTag root;

    /**
     * @return list of the parsing errors
     */
    public ListADT<String> getErrors() {
        return errors;
    }


    /**
     * @param filename name of the xml file to parse
     */
    public XMLParser(String filename) {
        Scanner  scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        errors = new MyArrayList<>();
        currentNodePath = new MyStack<>();
        lines = new MyArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        document="";
    }

    //merge strings to one for comfort parsing
    private String transformLinesToOne() {
        Iterator<String> lineIterator = lines.iterator();
        while (lineIterator.hasNext()) {
            document+=lineIterator.next()+'\n';
        }
        document = document.trim();
        return document;
    }

    /**
     * this method parse the document and check if it is valid
     */
    public void parseDocument() {
        transformLinesToOne();
        currentParsingPosition =0;
        //we should have the root tag or instruction in the start of the document
        if (document.charAt(currentParsingPosition) != '<') {
            errors.add("Document should start from root tag or instruction. ");
            return;
        }
        //if this is instruction - ignore it
        if (document.startsWith("<?")) {
            skipInstructionTag();
        }
        //go to the content of the root node
        currentParsingPosition++;
        //get the root tag name
        root = new XMLTag(getTagName());
        //skip closing >
        currentParsingPosition++;
        XMLTag current = root; //current parent tag
        while (currentParsingPosition < document.length()) {
            if (current == null) { //this means that we outside of any tag
                errors.add("All data should be inside of the root tag");
                return;
            }
            //looking for child tag or closing for current tag, ignore the content of the node
            while (document.charAt(currentParsingPosition) != '<') {
                current.setText(current.getText()+document.charAt(currentParsingPosition));
                currentParsingPosition++;
            }
            currentParsingPosition++; //go to content of tag name
            if (document.charAt(currentParsingPosition) == '?') { //instruction tag, ignore it
                skipInstructionTag();
            } else {
                if (document.charAt(currentParsingPosition) == '/') { //closing tag
                    closeTag(current.getName());
                    if (currentNodePath.isEmpty()) { //the stack is empty so this it root tag
                        current = null; //now we outside of any tag
                    } else {
                        current = currentNodePath.pop(); //extract parent node from the stack
                    }
                } else { //this is child node
                    String newTagName = getTagName(); //extract the name
                    XMLTag newTag = new XMLTag(newTagName);
                    current.getNestedTags().add(newTag); //add to childs
                    if (document.charAt(currentParsingPosition -1)!='/') { //if it is self-closing tag, no more actions we stay in current tag
                        // Otherwise store the parent in the stack and process new current tag
                        currentNodePath.push(current);
                        current = newTag;
                    }
                }
            }
            currentParsingPosition++;
        }
    }

    private String getTagName() {
        String tagName = "";
        //read tag name
        while (currentParsingPosition < document.length() && document.charAt(currentParsingPosition) != '>' && document.charAt(currentParsingPosition) != ' ' && document.charAt(currentParsingPosition) != '/') {
            tagName +=document.charAt(currentParsingPosition);
            currentParsingPosition++;
        }
        //we read all document but no > for tag name found
        if (currentParsingPosition == document.length()) {
            errors.add("Tag "+tagName+" name never closed. Tag names must have format <name>.");
        }
        else {
            if (document.charAt(currentParsingPosition) == ' ') { //there are attributes here, ignore it
                currentParsingPosition = document.indexOf('>', currentParsingPosition); //find >
                if (currentParsingPosition == -1) { //no more > in the document
                    errors.add("Tag " + tagName + " name never closed. Tag names must have format <name>.");
                }
            }
        }
        return tagName;
    }

    private boolean skipInstructionTag() {
        //skip anything before ?>
        currentParsingPosition = document.indexOf("?>", currentParsingPosition);
        if (currentParsingPosition == -1) {
            errors.add("Instruction tag is not closed");
            return false;
        } else {
            //skip ?> itself
            currentParsingPosition +=2;
        }
        return true;
    }

    private boolean closeTag(String tagName) {
        int start = currentParsingPosition +1;
        currentParsingPosition = document.indexOf(">", currentParsingPosition);
        if (currentParsingPosition == -1) { //missing > for closing tag
            errors.add("The tag "+tagName+" closed incorrectly. Missing >");
            return false;
        } else {
            //extract value between < and >. Check if it match current tag name
            String closingName = document.substring(start, currentParsingPosition);
            if (!closingName.equals(tagName)) {
                errors.add("Unexpected closing tag "+closingName);
                return false;
            }
        }
        return true;
    }
}
