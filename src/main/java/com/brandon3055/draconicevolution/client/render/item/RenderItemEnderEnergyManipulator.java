package com.brandon3055.draconicevolution.client.render.item;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.render.shader.ShaderProgram;
import codechicken.lib.util.TransformUtils;
import com.brandon3055.draconicevolution.client.handler.ClientEventHandler;
import com.brandon3055.draconicevolution.client.render.shaders.DEShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;

/**
 * Created by brandon3055 on 18/04/2017.
 */
public class RenderItemEnderEnergyManipulator implements IItemRenderer, IPerspectiveAwareModel {

    private static ItemStack stack = new ItemStack(Items.SKULL, 1, 1);


    public RenderItemEnderEnergyManipulator() {
    }

    //region Unused
    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    //endregion

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return MapWrapper.handlePerspective(this, TransformUtils.DEFAULT_ITEM.getTransforms(), cameraTransformType);
    }

    @Override
    public void renderItem(ItemStack item, ItemCameraTransforms.TransformType transformType) {
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5, 0.5, 0.5);

        mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);

        if (DEShaders.useShaders()) {
            DEShaders.reactorOp.setAnimation(((float) ClientEventHandler.elapsedTicks + mc.getRenderPartialTicks()) / -100F);
            DEShaders.reactorOp.setIntensity(0.09F);
            DEShaders.reactorShield.freeBindShader();
            mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);

            DEShaders.reactorOp.setAnimation(((float) ClientEventHandler.elapsedTicks + mc.getRenderPartialTicks()) / 100F);
            DEShaders.reactorOp.setIntensity(0.02F);
            DEShaders.reactorShield.freeBindShader();
            mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            ShaderProgram.unbindShader();
        }

        GlStateManager.popMatrix();
    }
}
