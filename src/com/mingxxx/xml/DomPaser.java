package com.mingxxx.xml;

import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class DomPaser {

    private static final String path = "web/WEB-INF/schema/books.xml";

    @Test
    public void test() throws DocumentException {
        // 1.创建dom4j的解析器
        SAXReader reader = new SAXReader();

        // 2.获取Document对象
        Document document = reader.read(new File(path));

        // 3.获取根元素
        Element root = document.getRootElement();

        Element book = root.element("book"); // 获取根元素的子节点book,只能第一个

        List<Element> books = root.elements("book"); // 获取根元素的子节点book,所有的
//
//         System.out.println(books.size());

        for (Element ele : books) {
            // 获取book下的所有子节点
            List<Element> els = ele.elements();
            for (Element el : els) {
                System.out.println(el.getName() + ":" + el.getText());
            }
        }
    }

    // 查找books下的第二个book元素的author的文本值
    @Test
    public void find() throws DocumentException {
        // 1.获取Document对象
        Document document = Dom4jUtils
                .getDocument(new File(path));
        // 2.获取根元素
        Element root = document.getRootElement();

        // 3.得到第二个book子元素符号
        Element book = (Element) (root.elements().get(1));

        String author_text = book.element("author").getText();

        System.out.println(author_text);

        // 获取第二个book的属性en
//         System.out.println(book.attribute("lang").getValue());
//         Attribute book_attribute= book.attribute("lang");//获取的是Attribute对象.

        System.out.println(book.attributeValue("lang")); // 直接获取属性值
    }

    // 修改
    // 将第二个book的price值修改为 $100 属性lang修改为中文
    @Test
    public void modify() throws Exception {
        // 1.获取Document对象
        Document document = Dom4jUtils
                .getDocument(new File(path));
        // 2.获取根元素
        Element root = document.getRootElement();

        // 3.获取第二个book元素
        Element book = (Element) (root.elements().get(1));

        // 4.获取book下的price元素
        book.element("price").setText("$100"); // 修改元素中的文本信息

        // 5。修改属性
        // book.setAttributeValue("lang", "zh"); //方法过时
        book.addAttribute("lang", "中文");

        // 6.回写
        Dom4jUtils.domToFile(document, new File(path));
    }

    // 添加
    // 给每一个book添加<type>子元素 给每一个book添加一个lang属性
    @Test
    public void add() throws Exception {

        // 1.获取Document对象
        Document document = Dom4jUtils.getDocument(new File(path));
        // 2.获取根元素
        Element root = document.getRootElement();

        // 3.获取每一个book元素
        List<Element> books = root.elements("book");

        for (Element book : books) {

//            Element type = DocumentHelper.createElement("type"); // 创建一个子元素 type
//            type.setText("xxxx");
//
//            // 将type元素添加到book中.
//
//            book.add(type);

            Element element = book.addElement("type"); //这样添加不会出现缺省的命名空间，因为会把父book的带给子，但是父子命名空间一样时，子不会出现命名空间
            element.setText("xxxx");

            // 添加属性
            book.addAttribute("long", "xxx");
        }

        Dom4jUtils.domToFile(document, new File(path));

    }

    // 删除
    @Test
    public void del() throws Exception {
        // 1.获取Document对象
        Document document = Dom4jUtils.getDocument(new File(path));
        // 2.获取根元素
        Element root = document.getRootElement();

        // 3.获取每一个book元素
        List<Element> books = root.elements("book");

        for (Element book : books) {
            //删除子元素  type

            //得到要删除的元素符号
            Element type = book.element("type");

            //book.remove(type);

            book.remove(book.attribute("long"));//删除属性

            //通过子元素获取父元素
            type.getParent().remove(type);
        }
        Dom4jUtils.domToFile(document, new File(path));
    }

    /*
     * <book> <name>盘龙</name> <author>我是西红柿</author> <price>￥99</price> </book>
	 * 要求在books.xml 文件中books下添加一个book子元素
	 */
    @Test
    public void add2() throws Exception {
        // 1.获取Document对象
        Document document = Dom4jUtils
                .getDocument(new File(path));
        // 2.获取根元素
        Element root = document.getRootElement();

        // 3.创建book子元素 name,author,price
        Element book = DocumentHelper.createElement("book");
        Element name = DocumentHelper.createElement("name");
        name.setText("盘龙");
        Element author = DocumentHelper.createElement("author");
        author.setText("我是西红柿");
        Element price = DocumentHelper.createElement("price");
        price.setText("￥99");

        book.add(name);
        book.add(author);
        book.add(price);

        List<Element> books = root.elements();

        books.add(0, book);

        Dom4jUtils.domToFile(document, new File(path));

    }

    // 将一个字符串表示的element对象添加到指定位置.
    @Test
    public void add1() throws Exception {
        String msg = "<book> <name>盘龙</name> <author>我是西红柿</author> <price>￥99</price> </book>";
        Document doc = DocumentHelper.parseText(msg);
        Element root = doc.getRootElement();

        // 1.获取Document对象
        Document document = Dom4jUtils
                .getDocument(new File(path));
        // 2.获取根元素
        Element bookroot = document.getRootElement();

        bookroot.add(root);
        Dom4jUtils.domToFile(document, new File(path));
    }

    // 查找一个元素
    @Test
    public void fun1() throws Exception {
        Document document = Dom4jUtils.getDocument(new File(path));
//        new XMLNamespaceBinder().doc
//        XmlNamespaceManager nsMgr = new XmlNamespaceManager(xmldoc.NameTable);
//        nsMgr.AddNamespace("ns", "http://www.mydomain.com/MyDataFeed");
//        document.selectSingleNode("//ns:error", nsMgr);
        Element name = (Element) document.selectSingleNode("/books/book/name");//这里只能获取没有命名空间的xml，带命名空间的需要用特殊方法处理
        System.out.println(name.getText());
    }

    // 查找多个元素
    @Test
    public void fun2() throws Exception {
        Document document = Dom4jUtils
                .getDocument(new File(path));
        List<Element> names = document.selectNodes("//name");
        for (Element name : names) {
            System.out.println(name.getText());
        }
    }

    //示例
    //查找books下的book的属性值为zh下的author的文本值.
    @Test
    public void fun3() throws Exception {
        Document document = Dom4jUtils
                .getDocument(new File(path));
        Element author = (Element) document.selectSingleNode("/books/book[@lang='1.01']/author");
        System.out.println(author.getText());
    }
}
