package uk.co.virtual1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.co.virtual1.dialog.ProvisionDialog;
import uk.co.virtual1.model.json.JsonResponse;
import uk.co.virtual1.salesforce.object.Case;

import java.io.IOException;

/**
 * @author Mikhail Tkachenko created on 30.04.16 11:54
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/json")
public class RestController extends BaseController {
    @Autowired
    private ProvisionDialog provisionDialog;

    @RequestMapping("/ping")
    public String ping() throws IOException {
        return JsonResponse.ok("ping succeed");
    }

    @RequestMapping("/provision")
    public String ping(@RequestBody Case aCase) throws Exception {
        provisionDialog.provisionFromCase(aCase);
        return JsonResponse.ok();
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String onException(Exception e) {
        return onAjaxException(e);
    }

}
