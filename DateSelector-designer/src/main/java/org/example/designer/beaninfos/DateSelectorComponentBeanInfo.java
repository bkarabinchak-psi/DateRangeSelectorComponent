package org.example.designer.beaninfos;

import java.beans.IntrospectionException;

import org.example.client.DateSelectorComponent;
import com.inductiveautomation.factorypmi.designer.property.customizers.DynamicPropertyProviderCustomizer;
import com.inductiveautomation.factorypmi.designer.property.customizers.StyleCustomizer;
import com.inductiveautomation.vision.api.designer.beans.CommonBeanInfo;
import com.inductiveautomation.vision.api.designer.beans.VisionBeanDescriptor;

public class DateSelectorComponentBeanInfo extends CommonBeanInfo {

    public DateSelectorComponentBeanInfo() {
        super(DateSelectorComponent.class,
                DynamicPropertyProviderCustomizer.VALUE_DESCRIPTOR,
                StyleCustomizer.VALUE_DESCRIPTOR);
    }

    @Override
    protected void initProperties() throws IntrospectionException {
        super.initProperties();

        addProp("text", "Text", "The text to display in the component", CAT_DATA, PREFERRED_MASK | BOUND_MASK);
    }

    @Override
    protected void initDesc() {
        VisionBeanDescriptor bean = getBeanDescriptor();
        bean.setName("Date Selection");
        bean.setDisplayName("Date Selection Component");
        bean.setShortDescription("A component that lets allows easy start/end date selection around current date.");
    }

}
