package kevin.module.modules.movement

import kevin.event.ClickWindowEvent
import kevin.event.EventTarget
import kevin.event.UpdateEvent
import kevin.event.UpdateState
import kevin.module.BooleanValue
import kevin.module.Module
import kevin.module.ModuleCategory
import kevin.utils.MovementUtils
import net.minecraft.client.gui.GuiChat
import net.minecraft.client.gui.GuiIngameMenu
import net.minecraft.client.settings.GameSettings
import org.lwjgl.input.Keyboard

class InvMove : Module("InvMove","Allows you to walk while an inventory is opened.",Keyboard.KEY_NONE,ModuleCategory.MOVEMENT){
    val fakeSprint = BooleanValue("FakeSprint",true)
    private val noMoveClicksValue = BooleanValue("NoMoveClicks", false)

    private val affectedBindings = arrayOf(
        mc.gameSettings.keyBindForward,
        mc.gameSettings.keyBindBack,
        mc.gameSettings.keyBindRight,
        mc.gameSettings.keyBindLeft,
        mc.gameSettings.keyBindJump,
        mc.gameSettings.keyBindSprint
    )

    @EventTarget
    fun onUpdate(event: UpdateEvent){
        if (event.eventState == UpdateState.OnLivingUpdate){
            if (mc.currentScreen !is GuiChat && mc.currentScreen !is GuiIngameMenu)
            for (affectedBinding in affectedBindings) {
                affectedBinding.pressed = GameSettings.isKeyDown(affectedBinding)
            }
        }
    }

    @EventTarget
    fun onClick(event: ClickWindowEvent) {
        if (noMoveClicksValue.get() && MovementUtils.isMoving)
            event.cancelEvent()
    }

    override fun onDisable() {
        val isIngame = mc.currentScreen != null
        for (affectedBinding in affectedBindings) {
            if (!GameSettings.isKeyDown(affectedBinding) || isIngame)
                affectedBinding.pressed = false
        }
    }

    override val tag: String?
        get() = if (fakeSprint.get()) "FakeSprint" else null
}