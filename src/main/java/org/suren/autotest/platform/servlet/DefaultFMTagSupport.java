package org.suren.autotest.platform.servlet;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class DefaultFMTagSupport extends TagSupport
{

    @Override
    public int doStartTag() throws JspException
    {
        TemplateLoader templateLoader =
                new ClassTemplateLoader(this.getClass(), "/template");
        Configuration configuration = new Configuration();
        configuration.setTemplateLoader(templateLoader);
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");

        try
        {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            Template template = configuration.getTemplate(templateName());

            Map<String, Object> paramMap = paramMap();

            Writer writer = new OutputStreamWriter(byteOut, "UTF-8");
            template.process(paramMap, writer);

            JspWriter out = this.pageContext.getOut();
            out.write(new String(byteOut.toByteArray(), "UTF-8"));
        }
        catch (IOException | TemplateException e)
        {
            e.printStackTrace();
        }

        return super.doStartTag();
    }

    /**
     * 传递给freemarker的参数
     * @return 参数
     */
    protected abstract Map<String, Object> paramMap();

    /**
     * freemarker模板文件名称
     * @return 模板文件名称
     */
    protected abstract String templateName();
}