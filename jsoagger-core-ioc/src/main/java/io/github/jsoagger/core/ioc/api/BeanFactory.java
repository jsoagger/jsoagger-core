/**
 *
 */
package io.github.jsoagger.core.ioc.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.jsoagger.core.ioc.api.annotations.Bean;
import io.github.jsoagger.core.ioc.api.annotations.Eager;
import io.github.jsoagger.core.ioc.api.annotations.Named;
import io.github.jsoagger.core.ioc.api.annotations.Singleton;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class BeanFactory {

  /**
   * List of know bean providers in application
   */
  List<BeanProvider> providers = new ArrayList<>();

  /** Unique instance */
  static BeanFactory instance;
  static Set<Class<?>> providersToLoad = new HashSet<>();
  private static boolean initialized = false;

  /**
   * Constructor
   */
  private BeanFactory() {
  }

  /**
   * Load all beans
   */
  public static void loadBeans() {
    instance();
    initialized = true;
  }

  /**
   * Return true if beans have been loaded.
   *
   * @return
   */
  public static boolean isInitialized() {
    return initialized;
  }


  /**
   * Get the unique instance of this bean.
   *
   * @return BeanFactory
   */
  public static BeanFactory instance() {
    if (instance == null) {
      instance = new BeanFactory();
      try {
        instance.processBeansProviders();
        instance.getBean("IntegrationService");
        instance.preloadBeans();
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    return instance;
  }

  /**
   * Get an instance of a bean with the given identifier.
   *
   * @param identifier
   * @return
   */
  public Object getBean(String identifier) {
    if (identifier == null || identifier.trim().equals("")) {
      return null;
    }

    for (BeanProvider beanProvider : providers) {
      if (beanProvider.canHandle(identifier)) {
        return beanProvider.getBean(identifier);
      }
    }

    return null;
  }

  private void processBeansProviders() throws InstantiationException, IllegalAccessException {
    _doProcess(providersToLoad);
  }

  private void preloadBeans() {
    for (BeanProvider beanProvider : providers) {
      for(BeanInstance instance: beanProvider.beans) {
        if(instance.isEager() && instance.isSingleton()) {
          beanProvider.getBeBeen(instance);
        }
      }
    }
  }

  private void _doProcess(Set<Class<?>> classes)  throws InstantiationException, IllegalAccessException{
    for (final Class<?> objectClass : classes) {
      final Method[] declaredMethods = objectClass.getDeclaredMethods();

      BeanProvider beanProvider = new BeanProvider();
      beanProvider.setProvider(objectClass.newInstance());
      providers.add(beanProvider);

      for (final Method method : declaredMethods) {
        if (method.isAnnotationPresent(Bean.class)) {
          Named named = method.getAnnotation(Named.class);
          boolean singleton = method.getAnnotation(Singleton.class) != null;
          boolean eager = method.getAnnotation(Eager.class) != null;
          String id = named.value();

          if (id != null) {
            beanProvider.getBeanIds().add(id);
            BeanInstance instance = new BeanInstance();
            instance.setId(id);
            instance.setEager(eager);
            instance.setProviderMethod(method);
            instance.setSingleton(singleton);
            beanProvider.addInstance(instance);
          }
        }
      }
    }
  }

  /*-----------------------------------------------------------------------------
	  | THE BEAN PROCESSOR
   *=============================================================================*/
  /**
   * Provider.
   *
   * @author Ramilafananana  VONJISOA
   *
   */
  static class BeanProvider {
    private Set<String> beanIds = new HashSet<>();
    private List<BeanInstance> beans = new ArrayList<>();
    private Object provider;

    /**
     * Constructor
     *
     * @param provider
     */
    public BeanProvider() {
    }

    public BeanProvider(Object provider) {
      this.provider = provider;
    }

    public void addInstance(BeanInstance instance) {
      beans.add(instance);
    }

    /**
     * Get an instance of the bean with the given identifier.
     *
     * @param identifier
     * @return
     */
    public Object getBean(String identifier) {
      for (BeanInstance beanInstance : beans) {
        if (beanInstance.id.equalsIgnoreCase(identifier)) {
          return getBeBeen(beanInstance);
        }
      }

      return null;
    }

    public Object getBeBeen(BeanInstance beanInstance) {
      if (beanInstance.instance == null || !beanInstance.isSingleton()) {
        Method m = beanInstance.getProviderMethod();
        try {
          Object res = m.invoke(provider);
          beanInstance.setInstance(res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
          e.printStackTrace();
        }
      }

      return beanInstance.instance;
    }

    /**
     * Return true if this object can give an instance of given bean.
     *
     * @param beanId
     * @return
     */
    public boolean canHandle(String beanId) {
      return beanIds.contains(beanId);
    }

    public Set<String> getBeanIds() {
      return beanIds;
    }

    public void setBeanIds(Set<String> beanIds) {
      this.beanIds = beanIds;
    }

    public Object getProvider() {
      return provider;
    }

    public void setProvider(Object provider) {
      this.provider = provider;
    }
  }

  /**
   * A bean
   *
   * @author Ramilafananana  VONJISOA
   */
  static class BeanInstance {
    String id;
    boolean singleton;
    Object instance;
    Method providerMethod;
    private boolean eager = false;

    public Method getProviderMethod() {
      return providerMethod;
    }

    public void setProviderMethod(Method providerMethod) {
      this.providerMethod = providerMethod;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      BeanInstance other = (BeanInstance) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      return true;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public boolean isSingleton() {
      return singleton;
    }

    public void setSingleton(boolean singleton) {
      this.singleton = singleton;
    }

    public Object getInstance() {
      return instance;
    }

    public void setInstance(Object instance) {
      this.instance = instance;
    }

    public boolean isEager() {
      return eager;
    }

    public void setEager(boolean eager) {
      this.eager = eager;
    }
  }

  /**
   * Add a provider to current context
   */
  public static void addProvider(Class clazz) {
    providersToLoad.add(clazz);
  }

  /**
   * Add a provider to current context
   */
  public static void addProviders(Collection<Class<?>> clazz) {
    providersToLoad.addAll(clazz);
  }
}
