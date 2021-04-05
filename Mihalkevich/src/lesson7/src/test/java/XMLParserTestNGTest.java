import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parser.XMLParser;

public class XMLParserTestNGTest {




    @DataProvider(name="data-provider with valid xml")

    public Object[][] dataProviderMethod() {

        return new Object[][] { { "xml/Note.xml" }, // bug - found [false], xml valid
                { "xml/Note_version_ru.xml" },// bug - found [false], xml valid c тегом на русском
                { "xml/Note_with_empty_string.xml" },// bug - found [false], xml valid с пустыми строками
                { "xml/Note_ver.xml" }}; //bug - падает StringIndexOutOfBoundsException, xml valid

    }

    @Test (dataProvider = "data-provider with valid xml")

    @Parameters

    public void testXML(String fileName) {
        XMLParser parser = new XMLParser(fileName);
        parser.parseDocument();
        Assert.assertTrue(parser.getErrors().isEmpty());
        System.out.println("Passed Parameter Is : " + fileName);
    }



    @DataProvider(name="data-provider with not valid xml")

    public Object[][] dataProviderMethod2() {

        return new Object[][] {
                { "xml/Text.xml" }, //ok, assertFalse
                { "xml/Without_close_teg.xml" },// падает StringIndexOutOfBoundsException
                { "xml/Empty.xml" }, // падает StringIndexOutOfBoundsException
                { "xml/Without_slash.xml" }, //ok, assertFalse
                { "xml/Without_qm.xml" },// падает StringIndexOutOfBoundsException
                { "xml/Note_upper.xml" },// ok, assertFalse
                { "xml/Note_with_enter.xml" },// ok, assertFalse
                { "xml/Without_qms.xml" },// ok, assertFalse
                { "xml/Other_format.doc" },// TBD нет уведомления о другом формате
                { "xml/Without_slash2.xml" }, // bug - found [true] распаршен без закрывающихся тегов
                { "xml/Without_open_teg.xml" }}; // падает StringIndexOutOfBoundsException



    }
    @Test (dataProvider = "data-provider with not valid xml")

    @Parameters

    public void testXML2(String fileName) {
        XMLParser parser = new XMLParser(fileName);
        parser.parseDocument();
        Assert.assertFalse (parser.getErrors().isEmpty());
        System.out.println("Passed Parameter Is : " + fileName);
    }

    }


