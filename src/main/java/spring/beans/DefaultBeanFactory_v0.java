package spring.beans;

import java.io.Closeable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory_v0 implements BeanFactory_v1 ,BeanDefinitionRegistry, Closeable {

    // 如何存储BeanDefination
    // Map<String,BeanDefinition>
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    // beanName重名怎么办




    // 1.实现定义信息注册
    // 2.实现bean工厂getBean
    // 3.实现初始化方法执行
    // 4.实现单例
    // 5.实现容器关闭时执行单例的销毁方法

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException{
        Objects.requireNonNull(beanName,"注册bean需要给入beanName");
        Objects.requireNonNull(beanDefinition,"注册bean需要给如beanDefinition");

        // 校验给入的Bean是否合法
        if(!beanDefinition.validate()){
            throw new BeanDefinitionRegistException("名字为["+beanName+"]的bean定义不合法");
        }

        if(this.containsBeanDefinition(beanName)){
            throw new BeanDefinitionRegistException(
                    "名字为["+beanName+"]的bean定义已存在" + this.getBeanDefinition(beanName)
            );
        }

        this.beanDefinitionMap.put(beanName,beanDefinition);

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName){return this.beanDefinitionMap.get(beanName);}

    @Override
    public boolean containsBeanDefinition(String beanName){return this.beanDefinitionMap.containsKey(beanName);}

    @Override
    public Object getBean(String name) throws Exception{

        return this.doGetBean(name);
    }

}
