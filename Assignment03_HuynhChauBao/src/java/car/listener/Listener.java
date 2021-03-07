/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class Listener implements ServletContextListener {

    private final String MAPPING_FILE = "/WEB-INF/mapping.txt";
    private final String ADMIN_FUNCTION = "/WEB-INF/adminFunction.txt";

    private Map<String, String> readMapping(String file) {
        FileReader f = null;
        BufferedReader bf = null;
        Map<String, String> mappingPage = null;
        try {
            f = new FileReader(file);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                String[] read = bf.readLine().split("=");
                if (read.length == 2) {
                    if (mappingPage == null) {
                        mappingPage = new HashMap<>();
                    }
                    mappingPage.put(read[0], read[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mappingPage;
    }

    private List<String> readAdminFuncion(String file) {
        FileReader f = null;
        BufferedReader bf = null;
        List<String> listAdminFunction = null;
        try {
            f = new FileReader(file);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                if (listAdminFunction == null) {
                    listAdminFunction = new ArrayList<>();
                }
                listAdminFunction.add(bf.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        return listAdminFunction;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        Map<String, String> mapping = readMapping(realPath + MAPPING_FILE);
        if (mapping != null) {
            context.setAttribute("MAPPING", mapping);
        }
        List<String> functionAdmin = readAdminFuncion(realPath + ADMIN_FUNCTION);
        if (functionAdmin != null) {
            context.setAttribute("ADMIN", functionAdmin);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
