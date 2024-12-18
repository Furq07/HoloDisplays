package dev.furq.holodisplays.menu

import dev.furq.holodisplays.config.DisplayConfig
import dev.furq.holodisplays.data.display.BlockDisplay
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

data object BlockEditMenu : LineEditMenu() {
    fun show(source: ServerCommandSource, name: String) {
        val display = DisplayConfig.getDisplay(name)!!
        val blockDisplay = display.display as BlockDisplay

        addEmptyLines(source)
        showHeader(source)

        source.sendFeedback({
            Text.literal("■ ")
                .formatted(Formatting.GREEN)
                .append(
                    Text.literal("Block Display Editor")
                        .formatted(Formatting.WHITE)
                )
                .append(
                    Text.literal(" » ")
                        .formatted(Formatting.GRAY)
                )
                .append(
                    Text.literal(name)
                        .formatted(Formatting.GREEN)
                )
        }, false)

        source.sendFeedback({ Text.literal("") }, false)

        showSectionHeader(source, "Block Properties")
        source.sendFeedback({
            Text.literal("│ ")
                .formatted(Formatting.GRAY)
                .append(
                    Text.literal("• ")
                        .formatted(Formatting.GREEN)
                )
                .append(
                    Text.literal("Block: ")
                        .formatted(Formatting.GRAY)
                )
                .append(
                    Text.literal(blockDisplay.id)
                        .formatted(Formatting.WHITE)
                )
                .append(Text.literal(" "))
                .append(createButton("Edit", "/holo edit display $name block id ", Formatting.GREEN))
        }, false)
        showSectionFooter(source)

        source.sendFeedback({ Text.literal("") }, false)
        showSectionHeader(source, "Common Properties")
        showCommonProperties(source, name, blockDisplay)
        showSectionFooter(source)
        showFooter(source, "/holo list display")
    }
}