package grails.template.demo

import grails.transaction.Transactional
import groovy.text.Template
import org.grails.gsp.GroovyPagesTemplateEngine
import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Transactional
@Consumer
class TemplateService {

    GroovyPagesTemplateEngine groovyPagesTemplateEngine


    String renderTemplate(String key, String content, Map model) {
        Template template = groovyPagesTemplateEngine.createTemplate(content, key)
        Writable renderedTemplate = template.make(model);
        StringWriter stringWriter = new StringWriter();
        renderedTemplate.writeTo(stringWriter);
        return stringWriter.toString();
    }

    @Selector('background.task')
    String backgroundTask() {
        String template = renderTemplate("myTemplateId", '<h3>Hello, ${user} </h3>', [user: "Grails User"]);
        /* Mail the content, or do something */

        log.info("Built template ${template}");

        return template;
    }

    String doTaskForeground() {
        return backgroundTask();
    }

    void doTaskBackground() {
        notify("background.task",[:]);
    }
}
