package com.example.project

import io.kvision.Application
import io.kvision.BootstrapCssModule
import io.kvision.BootstrapModule
import io.kvision.CoreModule
import io.kvision.DatetimeModule
import io.kvision.core.onChange
import io.kvision.form.time.dateTime
import io.kvision.html.button
import io.kvision.module
import io.kvision.panel.Tab
import io.kvision.panel.hPanel
import io.kvision.panel.root
import io.kvision.panel.tabPanel
import io.kvision.startApplication
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import kotlin.js.Date

class App : Application() {
  val abservable = ObservableValue("")

  override fun start() {
    root("kvapp") {
      hPanel {
        button("Press me") {
          onClick {
            abservable.value = "1"
          }
        }

        tabPanel {
          val tab1 = Tab("Tab 1") {
            dateTime {
              value = Date(2023, 2, 1)

              onChange {
                println("Tab 1 date onChange() set to $value")
              }

              bind(abservable) {
                if (it.isNotBlank()) {
                  println("Tab 1 date bind() value set")
                  value = Date(2024, 7, 1)
                }
              }
            }
          }

          val tab2 = Tab("Tab 2") {
            dateTime {
              value = Date(2023, 2, 1)

              onChange {
                println("Tab 2 date onChange() set to $value")
              }

              bind(abservable) {
                if (it.isNotBlank()) {
                  println("Tab 2 date bind() value set")
                  value = Date(2024, 7, 1)
                }
              }
            }
          }

          // After running with tab1 exposed and tab2 hidden, then swap the tab order and see the console msgs change
          add(tab1)
          add(tab2)
        }
      }
    }
  }
}

fun main() {
  startApplication(
    ::App,
    module.hot,
    BootstrapModule,
    BootstrapCssModule,
    DatetimeModule,
    CoreModule
  )
}