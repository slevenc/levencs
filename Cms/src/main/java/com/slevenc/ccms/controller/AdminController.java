package com.slevenc.ccms.controller;

import com.slevenc.ccms.logger.LoggerUtil;
import com.slevenc.ccms.service.system.SystemPropertiesService;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-25
 * Time: 下午10:21
 */
@Controller
@RequestMapping(value = "adminconsole/")
public class AdminController {

    private SystemPropertiesService sps = null;

    @RequestMapping("/")
    public String indexPage(ModelMap mm) {
        String next = "admin/admin.ftl";
        mm.put("systemProperties", sps.listAllProperties());

        return next;
    }

    @RequestMapping("update/")
    public String updateValue(@RequestParam(value = "name", required = true) @Length(max = 255) String name,
                              @RequestParam(value = "value", required = false, defaultValue = "") @Length(max = 255) String value) {

        sps.updateProperty(name, value);

        return "redirect:../";
    }

    @Resource
    public void setSps(SystemPropertiesService sps) {
        this.sps = sps;
    }
}
