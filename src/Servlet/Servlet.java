package Servlet;


import Trees.BinaryTree;
import Trees.LinkedlistIS;
import Trees.ServerDragons;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "Prueba",urlPatterns ={"/Prueba"})
public class Servlet extends HttpServlet {
    ServerDragons logicfactory = new ServerDragons();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String dragonList = request.getParameter("dragonList");
        LinkedlistIS sendList = Serializer.deserializerString(dragonList);
        System.out.println(sendList.head.getName());
        if (method != null) {
            sendList = sendList.validate_parent(sendList);
            switch (method) {
                case "Generate":
                    logicfactory.generate();
                    break;

                case "insertion":
                    sendList.insertionSort(sendList.head);
                    System.out.println(sendList.head.getName());
                    break;
                case "quicksort":
                sendList.quickSort(sendList.head);
                break;

                case "selection":
                sendList.selectionSort(sendList.head);
                break;

                case "BinaryTree":
                    BinaryTree tree = new BinaryTree();
                    tree.add(sendList, tree);
                break;

                case "avl":
                sendList=sendList.insertarAux(sendList);
                break;
            }
            send_typed_response(request,response, Serializer.serializerString(sendList));
        }

    }


    private void send_typed_response (HttpServletRequest request,
                                      HttpServletResponse response,
                                      Object data){
        String desired_type = request.getHeader("accept");

        // If client requests plain text or HTML, send it; else XML.
        if (desired_type.contains("text/plain"))
            send_plain(response, data);
        else if (desired_type.contains("text/html"))
            send_html(response, data);
        else
            send_xml(response, data);
    }
    private void send_xml (HttpServletResponse response, Object data){
        try {
            XMLEncoder enc = new XMLEncoder(response.getOutputStream());
            enc.writeObject(data.toString());
            enc.close();
        } catch (IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private void send_html (HttpServletResponse response, Object data){
        String html_start =
                "<html><head><title>send_html response</title></head><body><div>";
        String html_end = "</div></body></html>";
        String html_doc = html_start + data.toString() + html_end;
        send_plain(response, html_doc);
    }

    private void send_plain (HttpServletResponse response, Object data){
        try {
            OutputStream out = response.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
        } catch (IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String dragon = request.getParameter("dragon");
        if (dragon == null) {
            send_typed_response(request, response, "null");
        } else {
            try {
                send_typed_response(request, response, dragon);
            } catch (NumberFormatException e) {
                send_typed_response(request, response, -1);
            }
        }

    }

}
