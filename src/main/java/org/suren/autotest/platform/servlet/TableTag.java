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

    @Override
    protected Map<String, Object> paramMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("title", title);
        paramMap.put("api", api);
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
}