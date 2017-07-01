//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.22 at 09:12:27 PM CST 
//


package org.suren.autotest.platform.schemas.datasource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSource" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="page" type="{http://datasource.surenpi.com}dataSourcePageType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="pageClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="pagePackage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dataSource"
})
@XmlRootElement(name = "dataSources")
public class DataSources {

    @XmlElement(required = true)
    protected List<DataSources.DataSource> dataSource;
    @XmlAttribute(name = "pagePackage")
    protected String pagePackage;

    /**
     * Gets the value of the dataSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSources.DataSource }
     * 
     * 
     */
    public List<DataSources.DataSource> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<DataSources.DataSource>();
        }
        return this.dataSource;
    }

    /**
     * Gets the value of the pagePackage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagePackage() {
        return pagePackage;
    }

    /**
     * Sets the value of the pagePackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagePackage(String value) {
        this.pagePackage = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="page" type="{http://datasource.surenpi.com}dataSourcePageType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *       &lt;attribute name="pageClass" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "page"
    })
    public static class DataSource {

        @XmlElement(required = true)
        protected List<DataSourcePageType> page;
        @XmlAttribute(name = "pageClass")
        protected String pageClass;

        /**
         * Gets the value of the page property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the page property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataSourcePageType }
         * 
         * 
         */
        public List<DataSourcePageType> getPage() {
            if (page == null) {
                page = new ArrayList<DataSourcePageType>();
            }
            return this.page;
        }

        /**
         * Gets the value of the pageClass property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPageClass() {
            return pageClass;
        }

        /**
         * Sets the value of the pageClass property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPageClass(String value) {
            this.pageClass = value;
        }

    }

}
