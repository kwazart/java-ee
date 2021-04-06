package com.kwazart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] names = beanFactory.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			if ("blackAndWhiteRoll".equals(name)) {
				beanDefinition.setBeanClassName(ColorRoll.class.getName());
			}

			System.out.println("-------------------------");
		}
	}
}
