package org.example.designer;

import org.example.client.DateSelectorComponent;
import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.designer.model.AbstractDesignerModuleHook;
import com.inductiveautomation.ignition.designer.model.DesignerContext;
import com.inductiveautomation.vision.api.designer.VisionDesignerInterface;
import com.inductiveautomation.vision.api.designer.palette.JavaBeanPaletteItem;
import com.inductiveautomation.vision.api.designer.palette.Palette;
import com.inductiveautomation.vision.api.designer.palette.PaletteItemGroup;

public class DesignerHook extends AbstractDesignerModuleHook {
    public static final String MODULE_ID = "DateSelectorComponent";

    @Override
    public void startup(DesignerContext context, LicenseState activationState) throws Exception {
        // Add the BeanInfo package to the search path
        context.addBeanInfoSearchPath("com.inductiveautomation.ignition.examples.ce.beaninfos");

        // Add my component to its own palette
        VisionDesignerInterface vdi = (VisionDesignerInterface) context
                .getModule(VisionDesignerInterface.VISION_MODULE_ID);

        if (vdi != null) {
            Palette palette = vdi.getPalette();

            PaletteItemGroup group = palette.addGroup("Example");
            group.addPaletteItem(new JavaBeanPaletteItem(DateSelectorComponent.class));
        }
    }
}
