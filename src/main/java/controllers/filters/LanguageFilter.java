//package controllers.filters;
//
//import command.Home;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
////@WebFilter(filterName = "LanguageFilter", value = "/")
//public class LanguageFilter extends AbstractFilter {
//    @Override
//    public void doCustomFilter(HttpServletRequest req,
//                               HttpServletResponse resp,
//                               FilterChain filterChain) throws IOException, ServletException {
//        String lang = (String) req.getSession().getAttribute("lang");
//        req.getSession().setAttribute("lang", lang);
//        filterChain.doFilter(req, resp);
//    }
//}


//
//        filterChain.doFilter(req, resp);
//        Locale current = new Locale(lang, lang.toUpperCase());
//        ResourceBundle rb = ResourceBundle.getBundle("language", current)
//


//        StringBuffer stringBuffer = new StringBuffer();
//        req.getContextPath()
//
//        Set<String> paramNames = req.getParameterMap().keySet();
//        for(String name : paramNames){
//            String value = req.getParameter(name);
//        }


//        String uri = req.getParameter("uri");
//        String result = uri.replaceFirst(req.getContextPath(), "");
//        if (req.getParameter("lang") != null) {
//
////            Map<String, String[]> parameterMap = req.getParameterMap();
////
////            for (Map.Entry<String, String[]> item : parameterMap.entrySet()) {
////                String key = item.getKey();
////                String[] value = item.getValue();
////                System.out.println("key  "+key);
////                System.out.println("value   "+ value);
////                req.setAttribute(key, value);
////            }
//            req.getSession().setAttribute("lang", req.getParameter("lang"));
//            filterChain.doFilter(req, resp);
//        }


//        if (req.getSession().getAttribute("role") == null) {
//            req.getRequestDispatcher("/").forward(req, resp);
//        } else {
//            Home home = new Home();
//            home.execute(req, resp);
//            //req.getRequestDispatcher(result).forward(req, resp);
//        }
//    }
//}
