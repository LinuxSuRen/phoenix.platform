//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.22 at 09:12:44 PM CST 
//


package org.suren.autotest.platform.schemas.suite;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://suite.surenpi.com}suiteParamType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://suite.surenpi.com}commonActionAttr"/>
 *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://suite.surenpi.com}actionEnum" default="click" />
 *       &lt;attribute name="invoker" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionType", propOrder = {
    "param"
})
public class ActionType {

    protected List<SuiteParamType> param;
    @XmlAttribute(name = "field")
    protected String field;
    @XmlAttribute(name = "name")
    protected ActionEnum name;
    @XmlAttribute(name = "invoker")
    protected String invoker;
    @XmlAttribute(name = "beforeSleep")
    protected Integer beforeSleep;
    @XmlAttribute(name = "afterSleep")
    protected Integer afterSleep;
    @XmlAttribute(name = "disable")
    protected Boolean disable;
    @XmlAttribute(name = "repeat")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger repeat;
    @XmlAttribute(name = "rows")
    protected String rows;

    /**
     * Gets the value of the param property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the param property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuiteParamType }
     * 
     * 
     */
    public List<SuiteParamType> getParam() {
        if (param == null) {
            param = new ArrayList<SuiteParamType>();
        }
        return this.param;
    }

    /**
     * Gets the value of the field property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link ActionEnum }
     *     
     */
    public ActionEnum getName() {
        if (name == null) {
            return ActionEnum.CLICK;
        } else {
            return name;
        }
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionEnum }
     *     
     */
    public void setName(ActionEnum value) {
        this.name = value;
    }

    /**
     * Gets the value of the invoker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoker() {
        return invoker;
    }

    /**
     * Sets the value of the invoker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoker(String value) {
        this.invoker = value;
    }

    /**
     * Gets the value of the beforeSleep property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getBeforeSleep() {
        if (beforeSleep == null) {
            return  0;
        } else {
            return beforeSleep;
        }
    }

    /**
     * Sets the value of the beforeSleep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBeforeSleep(Integer value) {
        this.beforeSleep = value;
    }

    /**
     * Gets the value of the afterSleep property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getAfterSleep() {
        if (afterSleep == null) {
            return  0;
        } else {
            return afterSleep;
        }
    }

    /**
     * Sets the value of the afterSleep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAfterSleep(Integer value) {
        this.afterSleep = value;
    }

    /**
     * Gets the value of the disable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDisable() {
        if (disable == null) {
            return false;
        } else {
            return disable;
        }
    }

    /**
     * Sets the value of the disable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisable(Boolean value) {
        this.disable = value;
    }

    /**
     * Gets the value of the repeat property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRepeat() {
        if (repeat == null) {
            return new BigInteger("1");
        } else {
            return repeat;
        }
    }

    /**
     * Sets the value of the repeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRepeat(BigInteger value) {
        this.repeat = value;
    }

    /**
     * Gets the value of the rows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRows() {
        if (rows == null) {
            return "1";
        } else {
            return rows;
        }
    }

    /**
     * Sets the value of the rows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRows(String value) {
        this.rows = value;
    }

}
