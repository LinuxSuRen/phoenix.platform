package org.suren.autotest.platform.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/field_locator")
public class FieldLocatorController
{
    @RequestMapping(value = "/list")
    public String table(@RequestParam("fieldId") String fieldId, Model model)
    {
        model.addAttribute("fieldId", fieldId);
        return "field_locator/field_locator_table";
    }
}