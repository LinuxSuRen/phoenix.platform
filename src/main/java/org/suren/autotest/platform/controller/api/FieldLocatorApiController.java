package org.suren.autotest.platform.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.suren.autotest.platform.entity.FieldLocator;
import org.suren.autotest.platform.mapping.FieldLocatorMapper;

import java.util.List;

/**
 * 元素定位api
 * @author suren
 */
@RestController
@RequestMapping("/api/field_locator/{fieldId}")
public class FieldLocatorApiController
{
    @Autowired
    private FieldLocatorMapper locatorMapper;

    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestBody FieldLocator locator, @PathVariable String fieldId)
    {
        locator.setFieldId(fieldId);
        locatorMapper.save(locator);

        return locator.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody FieldLocator locator, @PathVariable String fieldId)
    {
        locator.setFieldId(fieldId);
        locatorMapper.update(locator);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FieldLocator get(@PathVariable String id)
    {
        return locatorMapper.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FieldLocator> list(@PathVariable String fieldId)
    {
        return locatorMapper.getAllByFieldId(fieldId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void del(@PathVariable String id)
    {
        locatorMapper.delById(id);
    }
}