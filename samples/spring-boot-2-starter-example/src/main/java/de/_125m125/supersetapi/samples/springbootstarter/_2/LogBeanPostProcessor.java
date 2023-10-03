package de._125m125.supersetapi.samples.springbootstarter._2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import de._125m125.supersetapi.adapter.SupersetAdapter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    log.info("Bean instantiated with name {} and class {}. {}", beanName,
        bean.getClass().getSimpleName(), bean instanceof SupersetAdapter);
    return bean;
  }
}
