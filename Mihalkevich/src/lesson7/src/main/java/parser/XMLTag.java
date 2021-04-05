package parser;

import utilities.ListADT;
import utilities.MyArrayList;

/**
 * Class to represent one xml tag
 */
public class XMLTag {

    public XMLTag(String name) {
        this.name = name;
        properties = new MyArrayList<>();
        nestedTags = new MyArrayList<>();
    }

    /**
     * name of the tag
     */
    private String name;

    /**
     * text content of the node
     */
    private String text;

    /**
     * list of tag attributes. Currently not used
     */
    private ListADT<TagProperty> properties;

    /**
     * list of nested tags for current node
     */
    private ListADT<XMLTag> nestedTags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListADT<TagProperty> getProperties() {
        return properties;
    }

    public void setProperties(ListADT<TagProperty> properties) {
        this.properties = properties;
    }

    public ListADT<XMLTag> getNestedTags() {
        return nestedTags;
    }

    public void setNestedTags(ListADT<XMLTag> nestedTags) {
        this.nestedTags = nestedTags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public class TagProperty {

        public TagProperty(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private String name;

        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
