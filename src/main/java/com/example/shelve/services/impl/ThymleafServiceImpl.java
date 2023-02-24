package com.example.shelve.services.impl;

import com.example.shelve.services.ThymleafService;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Map;
@Service
public class ThymleafServiceImpl implements ThymleafService {

    private static final String MAIL_TEMPLATE_BAME_NAME = "mail/MailMessages";

    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";

    private static final String MAIL_TEMPLATE_SUBFIX = ".html";

    private static final String UTF_8 = "UTF-8";

    private  static TemplateEngine templateEngine;

    static {
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine(){
        final SpringTemplateEngine templateEngine  = new SpringTemplateEngine();

        templateEngine.setTemplateResolver(htmlTemplateReslver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());

        return templateEngine;
    }

    private static ITemplateResolver htmlTemplateReslver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUBFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);

        return templateResolver;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BAME_NAME);
        return  messageSource;
    }

    @Override
    public String createContent(String template, Map<String, Object> variables) {
        final Context context = new Context();
        context.setVariables(variables);

        return templateEngine.process(template,context);
    }
}
