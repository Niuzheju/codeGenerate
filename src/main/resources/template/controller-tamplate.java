package ${controllerPackage};

import com.fx.model.result.PageBean;
import com.fx.model.result.Result;
import ${fullQueryClass};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ${fullServiceClass};

@Controller
@RequestMapping("${controllerPath}")
public class ${controllerClass} {

    private final String INDEX = "/WEB-INF/pages${jspPath}";

    @Autowired
    private ${serviceClass} ${serviceVar};

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return INDEX;
    }

    @RequestMapping(params = "action=list")
    @ResponseBody
    public Result getList(${queryClass} ${queryVar}, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows){
        ${queryVar}.setPageBean(new PageBean(rows, page));
        return ${serviceVar}.getList(${queryVar});
    }

}
