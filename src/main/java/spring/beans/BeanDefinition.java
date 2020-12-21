package spring.beans;

public interface BeanDefinition {

    String SCOPE_SINGLETION = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    Class<?> getBeanClass();

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    String getFactoryBeanName();

    String getInitMethodName();

    default boolean validate(){
        if(this.getBeanClass() == null){

        }
    }
}
