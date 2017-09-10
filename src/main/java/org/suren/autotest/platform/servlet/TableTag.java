package org.suren.autotest.platform.servlet;

import com.surenpi.autotest.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class TableTag extends DefaultFMTagSupport
{
    private String title;
    private String columns;
    private String buttons;

    private String api;
    private String listUri;

    @Override
    protected Map<String, Object> paramMap()
    {
        String contextPath = pageContext.getRequest().getServletContext().getContextPath();
        if(!api.startsWith(contextPath))
        {
            api = contextPath + api;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("title", title);
        paramMap.put("api", api);
        paramMap.put("listUri", listUri);
        paramMap.put("columns", StringUtils.toMap(columns));
        paramMap.put("buttons", StringUtils.toMap(buttons));

        return paramMap;
    }

    @Override
    protected String templateName()
    {
        return "table.ftl";
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getColumns()
    {
        return columns;
    }

    public void setColumns(String columns)
    {
        this.columns = columns;
    }

    public String getButtons()
    {
        return buttons;
    }

    public void setButtons(String buttons)
    {
        this.buttons = buttons;
    }

    public String getApi()
    {
        return api;
    }

    public void setApi(String api)
    {
        this.api = api;
    }

    public String getListUri()
    {
        return listUri;
    }

    public void setListUri(String listUri)
    {
        this.listUri = listUri;
    }
}