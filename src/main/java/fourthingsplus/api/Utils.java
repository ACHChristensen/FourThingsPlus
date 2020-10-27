package fourthingsplus.api;


public class Utils {

    public static String encodeHtml(String src) {
        return src.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");
    }

    public static void example1() throws Exception {
        throw new Exception("Hello, World!");
    }

    public static void example2() {
        throw new RuntimeException("Hello, World!");
    }
}
