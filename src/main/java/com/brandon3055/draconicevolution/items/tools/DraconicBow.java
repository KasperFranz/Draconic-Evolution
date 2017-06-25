package com.brandon3055.draconicevolution.items.tools;

import com.brandon3055.brandonscore.lib.Set3;
import com.brandon3055.draconicevolution.api.itemconfig.BooleanConfigField;
import com.brandon3055.draconicevolution.api.itemconfig.DoubleConfigField;
import com.brandon3055.draconicevolution.api.itemconfig.ItemConfigFieldRegistry;
import net.minecraft.item.ItemStack;

import static com.brandon3055.draconicevolution.api.itemconfig.IItemConfigField.EnumControlType.SLIDER;

/**
 * Created by brandon3055 on 5/06/2016.
 */
public class DraconicBow extends WyvernBow {
    public DraconicBow() {
        setEnergyStats(ToolStats.DRACONIC_BASE_CAPACITY, 8000000, 0);
    }

    @Override
    public ItemConfigFieldRegistry getFields(ItemStack stack, ItemConfigFieldRegistry registry) {
        registry.register(stack, new BooleanConfigField("bowFireArrow", false, "config.field.bowFireArrow.description"));
        registry.register(stack, new DoubleConfigField("bowShockPower", 0, 0, 4, "config.field.bowShockPower.description", SLIDER));
        return super.getFields(stack, registry);
    }

    @Override
    public float getMaxZoomModifier(ItemStack stack) {
        return 6;
    }

    @Override
    public int getToolTier(ItemStack stack) {
        return 1;
    }

    @Override
    public int getMaxUpgradeLevel(ItemStack stack, String upgrade) {
        return 3;
    }


    @Override
    public int getReaperLevel(ItemStack stack) {
        return 2;
    }

    @Override
    protected Set3<String, String, String> getTextureLocations() {
        return Set3.of("items/tools/draconic_bow00", "items/tools/obj/draconic_bow00", "models/item/tools/draconic_bow00.obj");
    }
}
