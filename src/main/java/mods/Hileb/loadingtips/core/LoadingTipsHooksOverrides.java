package mods.Hileb.loadingtips.core;

import mods.Hileb.loadingtips.render.EarlyFontRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

/**
 * @Project LoadingTipsFixer
 * @Author Hileb
 * @Date 2024/5/1 11:18
 **/
public class LoadingTipsHooksOverrides {
    public static final EarlyFontRender fontRenderer = new EarlyFontRender();

    @SuppressWarnings("all")
    public static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        if (text != null) {
            try{
                text = ITextComponent.Serializer.jsonToComponent(text).getFormattedText();
            }catch (Exception ignored){

            }
            fontRenderer.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2),  y, color);
        }
    }

    public static void drawString(String text, int col) {
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        color(col);
        GL11.glScalef(2.0F, 2.0F, 1.0F);
        GL11.glEnable(3553);
        drawCenteredString(fontRenderer, text, 160, 80, 0);
        GL11.glDisable(3553);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    private static void color(int color) {
        GL11.glColor3ub((byte)(color >> 16 & 255), (byte)(color >> 8 & 255), (byte)(color & 255));
    }
}
