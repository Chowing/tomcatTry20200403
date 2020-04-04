package ex01.pyrmont;

import java.io.InputStream;
import java.io.IOException;

public class Request {

    private InputStream input;
    private String uri;

    /**
     * ex01.pyrmont.HttpServer:Request request = new Request(input);
     */
    public Request(InputStream input) {
        this.input = input;
    }

    /**
     * 读取套接字的字节流
     */
    public void parse() {
        // Read a set of characters from the socket
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
//        System.out.print(request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * 从请求行中获取URI：从第一个和第二个空格中找出URI
     *
     * @param requestString
     * @return
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }

    public String getUri() {
        return uri;
    }

}