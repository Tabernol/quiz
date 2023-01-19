//package controllers.filters;
//
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
////@WebFilter(filterName = "LanguageFilter")
//public class LanguageFilter extends AbstractFilter {
//
//    private final String DEFAULT_LOCALE = "ua";
//
//    @Override
//    public void doCustomFilter(HttpServletRequest req,
//                               HttpServletResponse resp,
//                               FilterChain filterChain) throws IOException, ServletException {
//        if (req.getSession().getAttribute("locale") == null) {
//            req.getSession().setAttribute("locale", DEFAULT_LOCALE);
//        } else if (req.getParameter("locale") != null) {
//            req.getSession().setAttribute("locale", req.getParameter("locale"));
//        }
//
////        Map<String, String[]> parameterMap = req.getParameterMap();
////        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
////            req.setAttribute(param.getKey(), param.getValue());
////        }
//        filterChain.doFilter(req, resp);
//
//    }
//}
