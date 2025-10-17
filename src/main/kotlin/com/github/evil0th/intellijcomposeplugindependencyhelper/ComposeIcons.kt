package com.github.evil0th.intellijcomposeplugindependencyhelper

import com.intellij.ui.IconManager

@Suppress("unused")
object ComposeIcons {
    @JvmField
    val ComposeToolWindow =
        IconManager.getInstance().getIcon("/icons/composeToolWindow.svg", javaClass.getClassLoader())
}