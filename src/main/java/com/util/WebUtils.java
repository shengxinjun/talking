package com.util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;


/**
 * @author Chenzhenghua
 * @since 1.2.1
 */
public class WebUtils extends org.springframework.web.util.WebUtils
{
    /**
     * get the URL of web root
     * 
     * @param request
     *            current HTTP request
     * @return the web root without end "/"
     */
    public static String getWebRoot(HttpServletRequest request)
    {
        /*
         * request.getLocalName() not always return the same as it in the URL, for example, it
         * returns machine name for inner IP in the network, and if several domain binding in one
         * IP, it seems that always the first one returned. For these reason, retrieve the host
         * from request URL instead
         */
        // StringBuilder url = new StringBuilder();
        // String scheme = request.getScheme();
        // url.append(request.getScheme()).append("://").append(
        // request.getLocalName());
        // int port = request.getLocalPort();
        // if (!(scheme.equals("http") && port == 80)
        // && (scheme.equals("https") && port == 443))
        // url.append(":").append(port);
        // url.append(request.getContextPath());
        // return url.toString();
        String url = request.getRequestURL().toString();
        Assert.isTrue(url.length() > 8, "Not supported request");
        int hostEnd = url.indexOf("/", 8);// http(s)://
        if (hostEnd != -1)
            url = url.substring(0, hostEnd);
        return url + request.getContextPath();
    }

    /**
     * get the simple page name without path info from the request URL.<br> <b> Note: this method
     * always try to catch the original URL if request forward happened.</b>
     * 
     * @param request
     *            current HTTP request
     * @return page name. For example, for URL "http://domain.com/a.html", returns "a". For
     *         "https://domain.com/directory/" or "https://domain.com", returns "". For
     *         "https://domain.com/path" , returns "path".
     * @since 1.2.2
     */
    public static String getPageName(HttpServletRequest request)
    {
        String url = (String)request.getAttribute("javax.servlet.forward.servlet_path");
        if (url == null)
            url = request.getServletPath();
        Assert.hasLength(url, "Unsupported URL");

        int pos = url.lastIndexOf("/");
        if (pos + 1 == url.length())// end with /
            return "";
        url = url.substring(pos + 1);
        // trim ext
        pos = url.lastIndexOf(".");
        if (pos != -1)
            url = url.substring(0, pos);

        return url;
    }

    /**
     * send cache control headers to browser to disable page cache.<br/> Note: Invoke this before
     * anything output to the response! And, using a global cache strategy is always a better
     * choice
     * 
     * @param response
     * @since 1.2.1
     */
    public static void disablePageCache(HttpServletResponse response)
    {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setDateHeader("Expires", 0);
    }

    /**
     * do HTML escape
     * 
     * @param value
     *            string need to output to HTML page
     * @return encoded string
     * @since 1.2.1
     */
    public static String out(String value)
    {
        if (value == null)
            return "";
        return HtmlUtils.htmlEscape(value);
    }

    /**
     * encode special characters to escape from JS syntax error. escape back slash, single quote,
     * and '\r','\n'
     * 
     * @param value
     *            string need to output to JS source
     * @return encoded string
     * @since 1.2.1
     */
    public static String jsOut(String value)
    {
        if (value == null)
            return "";
        return value.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'").replaceAll("\n",
            "\\\\n").replaceAll("\r", "\\\\r");
    }

    /**
     * the same with escape function of javascript
     * 
     * @param text
     * @return encoded text, convert non ASCII characters to %XX or %uXXXX
     * @since 1.2.2
     */
    public static String jsEscape(String text)
    {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(text.length() * 6);
        for (i = 0; i < text.length(); i++ )
        {
            j = text.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256)
            {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            }
            else
            {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * the same with unescape function of javascript
     * 
     * @param text
     *            encoded text
     * @return original text
     * @since 1.2.2
     */
    public static String jsUnescape(String text)
    {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(text.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < text.length())
        {
            pos = text.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (text.charAt(pos + 1) == 'u')
                {
                    ch = (char)Integer.parseInt(text.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char)Integer.parseInt(text.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(text.substring(lastPos));
                    lastPos = text.length();
                }
                else
                {
                    tmp.append(text.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * truncate a HTML to given length. Usually using this to extract a text preview from a long
     * article <p> First of all, replace all HTML tags with a whitespace in the given content.
     * Then, count the string length in HTML layout(such as "&nbsp;" will be considered as length
     * 1), cut the content if necessary, and, return it
     * 
     * @param html
     *            the content in HTML format
     * @param length
     *            max length of returned characters in layout
     * @return text preview(in HTML format) of given content
     * @since 1.3.0
     */
    public static String truncateHTML(String html, int length)
    {
        if (!StringUtils.hasText(html))
            return "";
        // remove HTML tags
        StringBuilder text = new StringBuilder(html);
        int a = text.indexOf("<");
        while (a != -1 && a < text.length())
        {
            // maybe HTML character in it, break it may cause error
            // if (a > length) {
            // // ok,longer enough now
            // break;
            // }
            int b = text.indexOf(">", a);
            if (b == -1)
            {
                // maybe an un-encoded '<' character
                break;
            }
            text.replace(a, b + 1, " ");

            a = text.indexOf("<");
        }
        // unescape single HTML character, such as "&nbsp;" for whitespace
        String plainChars = HtmlUtils.htmlUnescape(text.toString());
        if (plainChars.length() > length)
            plainChars = plainChars.substring(0, length - 1) + "...";

        return HtmlUtils.htmlEscape(plainChars);
    }

    /**
     * truncate a URL string to the max length for view using. Generally, the head and tail of a
     * URL contains more useful info for the visitor, so cut off the middle instead of cutting off
     * tail as usual.
     * 
     * @param url
     *            a URL string
     * @param maxLen
     *            max length of the cutting result
     * @return a shorter string with '...' for ellipsis
     * @since 1.2.1
     */
    public static String truncateURL(String url, int maxLen)
    {
        if (url == null)
            url = "";
        if (url.length() <= maxLen)
            return url;

        int scheme = 8;// 'http(s://)'
        String ellipsis = "...";
        if (maxLen < 20)
            maxLen = 20;// must larger than scheme + ellipsis

        int queryString = url.indexOf("?");
        if (queryString != -1 && queryString > maxLen)
            url = url.substring(0, queryString) + ellipsis;

        if (url.length() > maxLen)
        {
            int trunc = url.length() - maxLen + ellipsis.length();
            int offset = (url.length() - scheme - trunc) / 2;
            return url.substring(0, scheme + offset) + ellipsis
                   + url.substring(scheme + offset + trunc);
        }
        return url;
    }

    /**
     * Retrieve the first cookie with the given name. Note that multiple cookies can have the same
     * name but different paths or domains.
     * 
     * @param request
     *            current servlet request
     * @param name
     *            cookie name
     * @return the first cookie with the given name, or <code>null</code> if none is found
     */
    public static Cookie getCookie(HttpServletRequest request, String name)
    {
        return org.springframework.web.util.WebUtils.getCookie(request, name);
    }

    /**
     * Retrieve value of the first found cookie with the given name. Note that multiple cookies can
     * have the same name but different paths or domains.
     * 
     * @param request
     *            current servlet request
     * @param name
     *            cookie name
     * @return value of the first cookie, or <code>null</code> if none is found
     */
    public static String getCookieValue(HttpServletRequest request, String name)
    {
        Cookie c = getCookie(request, name);
        if (c != null)
            return c.getValue();
        return null;
    }

    /**
     * Retrieve boolean value from cookie. Behavior is the save as @see
     * {@link #getCookie(HttpServletRequest, String)}
     * 
     * @param request
     *            current servlet request
     * @param name
     *            cookie name
     * @return
     */
    public static boolean getBooleanCookieValue(HttpServletRequest request, String name)
    {
        String value = getCookieValue(request, name);
        Assert.notNull(value);
        return Boolean.parseBoolean(value);
    }

    /**
     * Send cookie to client, and set age to -1, which means the cookie will expire after the
     * browser is closed. <br> Note, path is set to request context path by default, to ensure that
     * the cookie with the same name is always the same one when access from different sub
     * directory.
     * 
     * @param request
     * @param response
     * @param cookieName
     * @param value
     * @see #setCookie(HttpServletResponse, String, String, int, String)
     * @see HttpServletRequest#getContextPath()
     */
    public final static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                       String cookieName, String value)
    {
        setCookie(request, response, cookieName, value, -1);
    }

    /**
     * Send cookie to client. <br> Note, path is set to request context path by default, to ensure
     * that the cookie with the same name is always the same one when access from different sub
     * 
     * @param request
     * @param response
     * @param cookieName
     * @param value
     * @param age
     * @see #setCookie(HttpServletResponse, String, String, int, String)
     * @see HttpServletRequest#getContextPath()
     */
    public final static void setCookie(HttpServletRequest request, HttpServletResponse response,
                                       String cookieName, String value, int age)
    {
        String path = StringUtils.hasText(request.getContextPath()) ? request.getContextPath() : "/";
        setCookie(response, cookieName, value, age, path);
    }

    /**
     * Send cookie to client.
     * 
     * @param response
     * @param cookieName
     * @param value
     * @param age
     * @param path
     */
    public final static void setCookie(HttpServletResponse response, String cookieName,
                                       String value, int age, String path)
    {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(age);
        if (StringUtils.hasText(path))
            cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * clear cookie with given name in the request context path
     * 
     * @param request
     * @param response
     * @param cookieName
     */
    public final static void clearCookie(HttpServletRequest request, HttpServletResponse response,
                                         String cookieName)
    {
        setCookie(request, response, cookieName, "", 0);
    }

    /**
     * clear all visible cookies in the request context path. <p> <b>Note: this will clear all
     * cookies, possibly including the session id! Be careful of using it.
     * 
     * @param request
     * @param response
     */
    public final static void clearCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cs = request.getCookies();
        for (Cookie c : cs)
        {
            c.setMaxAge(0);
            String path = StringUtils.hasText(request.getContextPath()) ? request.getContextPath() : "/";
            c.setPath(path);
            response.addCookie(c);
        }
    }
    
    
}