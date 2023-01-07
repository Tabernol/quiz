<%@ tag import="java.util.Date, java.text.DateFormat" %>


<%
    Date now = new Date();
    DateFormat dfm = DateFormat.getDateInstance(DateFormat.SHORT);

    out.print(dfm.format(now));
%>