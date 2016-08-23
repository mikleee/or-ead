package uk.co.virtual1.model.json;

import flexjson.JSONSerializer;
import uk.co.virtual1.util.SpringUtils;

/**
 * @author Mikhail Tkachenko created on 30.04.16 11:40
 */
public class JsonResponse<T> {

    private String status;
    private T body;

    public static String error() {
        return _error().toString();
    }

    public static String ok() {
        return _ok().toString();
    }

    public static <T> String error(T body) {
        JsonResponse<T> jsonResponse = _error();
        jsonResponse.setBody(body);
        return jsonResponse.toString();
    }

    public static <T> String ok(T body) {
        JsonResponse<T> jsonResponse = _ok();
        jsonResponse.setBody(body);
        return jsonResponse.toString();
    }

    private static <T> JsonResponse<T> _error() {
        JsonResponse<T> jsonResponse = new JsonResponse<>();
        jsonResponse.setStatus("error");
        return jsonResponse;
    }

    private static <T> JsonResponse<T> _ok() {
        JsonResponse<T> jsonResponse = new JsonResponse<>();
        jsonResponse.setStatus("ok");
        return jsonResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return SpringUtils.getBean(JSONSerializer.class).deepSerialize(this);
    }
}
