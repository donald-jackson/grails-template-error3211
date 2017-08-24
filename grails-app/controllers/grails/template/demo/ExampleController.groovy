package grails.template.demo

class ExampleController {

    TemplateService templateService

    def foreground() {
        String template = templateService.doTaskForeground();
        render "done foreground ${template}";
    }

    def background() {
        templateService.doTaskBackground();
        render 'done background';
    }
}
