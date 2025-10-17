package com.github.evil0th.intellijcomposeplugindependencyhelper.toolWindow

import androidx.compose.runtime.LaunchedEffect
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.jetbrains.jewel.bridge.addComposeTab
import com.github.evil0th.intellijcomposeplugindependencyhelper.CoroutineScopeHolder
import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.ChatAppSample
import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.repository.ChatRepository
import com.github.evil0th.intellijcomposeplugindependencyhelper.chatApp.viewmodel.ChatViewModel
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.model.Location
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.services.LocationsProvider
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.services.WeatherForecastService
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.ui.WeatherAppSample
import com.github.evil0th.intellijcomposeplugindependencyhelper.weatherApp.ui.WeatherAppViewModel

class ComposeSamplesToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun shouldBeAvailable(project: Project) = true

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        weatherApp(project, toolWindow)
        chatApp(project, toolWindow)
    }

    private fun weatherApp(project: Project, toolWindow: ToolWindow) {
        // create ViewModel once per tool window
        val viewModel = WeatherAppViewModel(
            listOf(Location("Munich", "Germany")),
            project.service<CoroutineScopeHolder>()
                .createScope(::WeatherAppViewModel.name),
            WeatherForecastService()
        )
        Disposer.register(toolWindow.disposable, viewModel)

        toolWindow.addComposeTab("Weather App") {
            LaunchedEffect(Unit) {
                viewModel.onReloadWeatherForecast()
            }

            WeatherAppSample(
                viewModel,
                viewModel,
                service<LocationsProvider>()
            )
        }
    }

    private fun chatApp(project: Project, toolWindow: ToolWindow) {
        val viewModel = ChatViewModel(
            project.service<CoroutineScopeHolder>()
                .createScope(ChatViewModel::class.java.simpleName),
            service<ChatRepository>()
        )
        Disposer.register(toolWindow.disposable, viewModel)

        toolWindow.addComposeTab("Chat App") { ChatAppSample(viewModel) }
    }
}
