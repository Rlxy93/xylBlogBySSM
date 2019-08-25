package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MappingController {
    @RequestMapping({"/{path}"})
    public String ftlMapping(@PathVariable String path) {
        return path;
    }


    @RequestMapping({"/"})
    public String indexMapping() {
        return "index";
    }


    @RequestMapping({"/admin/{path}"})
    public String userMapping(@PathVariable String path) {
        return "/admin/" + path;
    }
}