/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package io.github.jsoagger.core.bridge.result;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * { "data": { "type": "articles", "id": "1", "attributes": { // ... this
 * article's attributes }}
 * <p>
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class OperationData implements Serializable, Cloneable {

  private static final long serialVersionUID = -4887771763914349926L;
  private String uuid = null;

  private Map<String, Object> meta = new HashMap<>();
  private Map<String, Object> attributes = new HashMap<>();
  private Map<String, Object> masterAttributes = new HashMap<>();
  private Map<String, Object> links = new HashMap<>();
  private Map<String, Object> relationships = new HashMap<>();
  protected Map<String, Object> container = new HashMap<>();
  protected Map<String, Object> businessType = new HashMap<>();

  // roleA <- nestedAttributes ->roleB
  protected OperationData nestedAttributes = null;


  /**
   * Constructor
   */
  public OperationData() {
    uuid = UUID.randomUUID().toString();
  }


  /**
   * Getter of metaData
   *
   * @return the metaData
   */
  public Map<String, Object> getMeta() {
    return meta;
  }


  /**
   * Setter of metaData
   *
   * @param metaData
   *            the metaData to set
   */
  public void setMeta(Map<String, Object> meta) {
    this.meta = meta;
  }


  /**
   * Getter of attributes
   *
   * @return the attributes
   */
  public Map<String, Object> getAttributes() {
    return attributes;
  }


  /**
   * Setter of attributes
   *
   * @param attributes
   *            the attributes to set
   */
  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }


  /**
   * Getter of links
   *
   * @return the links
   */
  public Map<String, Object> getLinks() {
    return links;
  }


  /**
   * Setter of links
   *
   * @param links
   *            the links to set
   */
  public void setLinks(Map<String, Object> links) {
    this.links = links;
  }


  /**
   * Getter of relationships
   *
   * @return the relationships
   */
  public Map<String, Object> getRelationships() {
    return relationships;
  }


  /**
   * Setter of relationships
   *
   * @param relationships
   *            the relationships to set
   */

  public void setRelationships(Map<String, Object> relationships) {
    this.relationships = relationships;
  }

  /**
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public static class Builder {

    private String					type;
    private String					id;
    private Map<String, Object>		meta				= new HashMap<>();
    private Map<String, Object>		attributes			= new HashMap<>();
    private Map<String, Object>		masterAttributes	= new HashMap<>();
    private Map<String, Object>		links				= new HashMap<>();
    private Map<String, Object>		relationships		= new HashMap<>();
    protected Map<String, Object>	container			= new HashMap<>();
    protected Map<String, Object>	businessType		= new HashMap<>();


    public Builder type(String type) {
      this.type = type;
      return this;
    }


    public Builder id(String id) {
      this.id = id;
      return this;
    }


    public Builder meta(Map<String, Object> meta) {
      this.meta = meta;
      return this;
    }


    public Builder container(Map<String, Object> container) {
      this.container = container;
      return this;
    }


    public Builder businessType(Map<String, Object> businessType) {
      this.businessType = businessType;
      return this;
    }


    public Builder addMasterAttributes(String key, Object value) {
      masterAttributes.put(key, value);
      return this;
    }


    public Builder addMeta(String key, Object value) {
      meta.put(key, value);
      return this;
    }


    public Builder attributes(Map<String, Object> attributes) {
      this.attributes = attributes;
      return this;
    }


    public Builder addAttribute(String key, Object value) {
      attributes.put(key, value);
      return this;
    }


    public Builder links(Map<String, Object> links) {
      this.links = links;
      return this;
    }


    public Builder relationships(Map<String, Object> relationships) {
      this.relationships = relationships;
      return this;
    }


    public OperationData build() {
      return new OperationData(this);
    }
  }


  /**
   * Constructor
   *
   * @param builder
   */
  private OperationData(Builder builder) {
    if (!builder.meta.isEmpty()) {
      meta = builder.meta;
    }

    if (!builder.attributes.isEmpty()) {
      attributes = builder.attributes;
    }

    if (!builder.links.isEmpty()) {
      links = builder.links;
    }

    if (!builder.relationships.isEmpty()) {
      relationships = builder.relationships;
    }

    if (!builder.container.isEmpty()) {
      container = builder.container;
    }

    if (!builder.businessType.isEmpty()) {
      businessType = builder.businessType;
    }

    if (!builder.masterAttributes.isEmpty()) {
      masterAttributes = builder.masterAttributes;
    }
  }


  /**
   * Getter of container
   *
   * @return the container
   */
  public Map<String, Object> getContainer() {
    return container;
  }


  /**
   * Setter of container
   *
   * @param container
   *            the container to set
   */
  public void setContainer(Map<String, Object> container) {
    this.container = container;
  }


  /**
   * Getter of businessType
   *
   * @return the businessType
   */
  public Map<String, Object> getBusinessType() {
    return businessType;
  }


  /**
   * Setter of businessType
   *
   * @param businessType
   *            the businessType to set
   */
  public void setBusinessType(Map<String, Object> businessType) {
    this.businessType = businessType;
  }


  /**
   * Getter of masterAttributes
   *
   * @return the masterAttributes
   */
  public Map<String, Object> getMasterAttributes() {
    return masterAttributes;
  }


  /**
   * Setter of masterAttributes
   *
   * @param masterAttributes
   *            the masterAttributes to set
   */
  public void setMasterAttributes(Map<String, Object> masterAttributes) {
    this.masterAttributes = masterAttributes;
  }


  /**
   * Getter of nestedAttributes
   *
   * @return the nestedAttributes
   */
  public OperationData getNestedAttributes() {
    return nestedAttributes;
  }


  /**
   * Setter of nestedAttributes
   *
   * @param nestedAttributes
   *            the nestedAttributes to set
   */
  public void setNestedAttributes(OperationData nestedAttributes) {
    this.nestedAttributes = nestedAttributes;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
    return result;
  }


  /**
   * Two object full id are attribute are equals.
   *
   * @return
   */
  public boolean fullIdEquals(OperationData data) {
    String fullId = (String) getAttributes().get("fullId");
    String fullId2 = (String) data.getAttributes().get("fullId");

    if(fullId == null && fullId2 != null) return false;
    return fullId.equalsIgnoreCase(fullId2);
  }

  /**
   * Two object master full id are attribute are equals.
   *
   * @return
   */
  public boolean masterFullIdEquals(OperationData data) {
    String fullId = (String) getMasterAttributes().get("fullId");
    String fullId2 = (String) data.getMasterAttributes().get("fullId");

    if(fullId == null && fullId2 != null) return false;

    return fullId.equalsIgnoreCase(fullId2);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OperationData other = (OperationData) obj;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return fullIdEquals((OperationData) obj);
    return true;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    OperationData clone = (OperationData) super.clone();
    clone.uuid = uuid;
    return clone;
  }
}
