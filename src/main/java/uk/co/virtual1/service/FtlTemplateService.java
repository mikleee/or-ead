package uk.co.virtual1.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 30.04.16 12:29
 */
@Service
public class FtlTemplateService {

    private Configuration configuration;

    public FtlTemplateService() {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");
    }

    public String process(String templateName, Map<String, Object> params) {
        try {
            Template template = configuration.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(params, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Cant process template " + templateName + " with params: " + params);
        }
    }


}