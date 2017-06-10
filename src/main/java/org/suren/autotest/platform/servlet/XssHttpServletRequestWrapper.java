package org.suren.autotest.platform.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author suren
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String getParameter(String name)
    {
        String value = super.getParameter(name);
        if(StringUtils.isNotBlank(value))
        {
            value = xssClean(value);
        }

        return value;
    }

    /**
     * 清除xss攻击
     * @param value
     * @return
     */
    private String xssClean(String value)
    {
        value = value.replaceAll("<script>", "");
        value = value.replaceAll("</script>", "");

        return value;
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] values = super.getParameterValues(name);
        if(values != null)
        {
            int len = values.length;
            for(int i = 0; i < len; i++)
            {
                String value = values[i];
                if(StringUtils.isNotBlank(value))
                {
                    values[i] = xssClean(value);
                }
            }
        }

        return values;
    }
}
