package mods.Hileb.loadingtips.core;

import me.modmuss50.loadingtips.core.RebornTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * @Project LoadingTipsFixer
 * @Author Hileb
 * @Date 2024/5/1 15:47
 **/
public class LoadingTipTransformer implements IClassTransformer {
    private static final RebornTransformer rebornTransformer = new RebornTransformer();
    static {
        rebornTransformer.transformClass("me.modmuss50.loadingtips.LoadingTipsHooks")
                .findMethod("drawString", "(Ljava/lang/String;I)V")
                .transform(mn -> {
                    mn.instructions.clear();
                    mn.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    mn.instructions.add(new VarInsnNode(Opcodes.ILOAD, 1));
                    mn.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "mods/Hileb/loadingtips/core/LoadingTipsHooksOverrides", "drawString", "(Ljava/lang/String;I)V", false));
                    mn.instructions.add(new InsnNode(Opcodes.RETURN));
                });
    }
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        return rebornTransformer.transform(name, transformedName, basicClass);
    }
}
