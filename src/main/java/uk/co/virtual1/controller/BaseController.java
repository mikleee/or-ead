package uk.co.virtual1.controller;

import org.apache.log4j.Logger;
import uk.co.virtual1.model.json.JsonResponse;

/**
 * @author Mikhail Tkachenko created on 10.05.2016
 */
abstract class BaseController {
    private final Logger logger = Logger.getLogger(getClass());

    String onAjaxException(Exception e) {
        logger.error(e, e);
        return JsonResponse.error(e.getMessage());
    }

}
