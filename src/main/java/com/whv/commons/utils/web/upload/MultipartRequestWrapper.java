package com.whv.commons.utils.web.upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;

public abstract class MultipartRequestWrapper
    implements HttpServletRequest
{

    public MultipartRequestWrapper(HttpServletRequest request)
    {
        this.request = request;
        parameters = new HashMap();
    }

    public void setParameter(String name, String value)
    {
        String mValue[] = (String[])parameters.get(name);
        if(mValue == null)
            mValue = new String[0];
        String newValue[] = new String[mValue.length + 1];
        System.arraycopy(mValue, 0, newValue, 0, mValue.length);
        newValue[mValue.length] = value;
        parameters.put(name, newValue);
    }

    public String getParameter(String name)
    {
        String value = request.getParameter(name);
        if(value == null)
        {
            String mValue[] = (String[])parameters.get(name);
            if(mValue != null && mValue.length > 0)
                value = mValue[0];
        }
        return value;
    }

    public Enumeration getParameterNames()
    {
        Enumeration baseParams = request.getParameterNames();
        Vector list = new Vector();
        for(; baseParams.hasMoreElements(); list.add(baseParams.nextElement()));
        Collection multipartParams = parameters.keySet();
        for(Iterator iterator = multipartParams.iterator(); iterator.hasNext(); list.add(iterator.next()));
        return Collections.enumeration(list);
    }

    public String[] getParameterValues(String name)
    {
        String value[] = request.getParameterValues(name);
        if(value == null)
            value = (String[])parameters.get(name);
        return value;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public Object getAttribute(String name)
    {
        return request.getAttribute(name);
    }

    public Enumeration getAttributeNames()
    {
        return request.getAttributeNames();
    }

    public String getCharacterEncoding()
    {
        return request.getCharacterEncoding();
    }

    public int getContentLength()
    {
        return request.getContentLength();
    }

    public String getContentType()
    {
        return request.getContentType();
    }

    public ServletInputStream getInputStream()
        throws IOException
    {
        return request.getInputStream();
    }

    public String getProtocol()
    {
        return request.getProtocol();
    }

    public String getScheme()
    {
        return request.getScheme();
    }

    public String getServerName()
    {
        return request.getServerName();
    }

    public int getServerPort()
    {
        return request.getServerPort();
    }

    public BufferedReader getReader()
        throws IOException
    {
        return request.getReader();
    }

    public String getRemoteAddr()
    {
        return request.getRemoteAddr();
    }

    public String getRemoteHost()
    {
        return request.getRemoteHost();
    }

    public void setAttribute(String name, Object o)
    {
        request.setAttribute(name, o);
    }

    public void removeAttribute(String name)
    {
        request.removeAttribute(name);
    }

    public Locale getLocale()
    {
        return request.getLocale();
    }

    public Enumeration getLocales()
    {
        return request.getLocales();
    }

    public boolean isSecure()
    {
        return request.isSecure();
    }

    public RequestDispatcher getRequestDispatcher(String path)
    {
        return request.getRequestDispatcher(path);
    }

    public String getRealPath(String path)
    {
        return request.getRealPath(path);
    }

    public String getAuthType()
    {
        return request.getAuthType();
    }

    public Cookie[] getCookies()
    {
        return request.getCookies();
    }

    public long getDateHeader(String name)
    {
        return request.getDateHeader(name);
    }

    public String getHeader(String name)
    {
        return request.getHeader(name);
    }

    public Enumeration getHeaders(String name)
    {
        return request.getHeaders(name);
    }

    public Enumeration getHeaderNames()
    {
        return request.getHeaderNames();
    }

    public int getIntHeader(String name)
    {
        return request.getIntHeader(name);
    }

    public String getMethod()
    {
        return request.getMethod();
    }

    public String getPathInfo()
    {
        return request.getPathInfo();
    }

    public String getPathTranslated()
    {
        return request.getPathTranslated();
    }

    public String getContextPath()
    {
        return request.getContextPath();
    }

    public String getQueryString()
    {
        return request.getQueryString();
    }

    public String getRemoteUser()
    {
        return request.getRemoteUser();
    }

    public boolean isUserInRole(String user)
    {
        return request.isUserInRole(user);
    }

    public Principal getUserPrincipal()
    {
        return request.getUserPrincipal();
    }

    public String getRequestedSessionId()
    {
        return request.getRequestedSessionId();
    }

    public String getRequestURI()
    {
        return request.getRequestURI();
    }

    public String getServletPath()
    {
        return request.getServletPath();
    }

    public HttpSession getSession(boolean create)
    {
        return request.getSession(create);
    }

    public HttpSession getSession()
    {
        return request.getSession();
    }

    public static boolean isMultiPart(HttpServletRequest request)
    {
        String content_type = request.getHeader("Content-Type");
        return content_type != null && content_type.startsWith("multipart/form-data") && "POST".equals(request.getMethod());
    }

    public boolean isRequestedSessionIdValid()
    {
        return request.isRequestedSessionIdValid();
    }

    public boolean isRequestedSessionIdFromURL()
    {
        return request.isRequestedSessionIdFromURL();
    }

    public boolean isRequestedSessionIdFromUrl()
    {
        return request.isRequestedSessionIdFromUrl();
    }

    public Map getParameterMap()
    {
        return null;
    }

    public void setCharacterEncoding(String s)
    {
    }

    public StringBuffer getRequestURL()
    {
        return null;
    }

    public boolean isRequestedSessionIdFromCookie()
    {
        return false;
    }

    protected Map parameters;
    protected HttpServletRequest request;
	
}


