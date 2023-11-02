package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.Dimens.MediumPadding2
import com.example.newsapp.presentation.common.NewsButton
import com.example.newsapp.presentation.common.NewsTextButton
import com.example.newsapp.presentation.onboarding.components.OnBoardingPage
import com.example.newsapp.presentation.onboarding.components.PagerIndicator
import com.example.newsapp.presentation.pages
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Создаем состояние для горизонтального Pager
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size // Инициализируем Pager с количеством страниц из `pages`
        }

        // Создаем состояние для кнопок навигации (названия кнопок "Back" и "Next")
        val buttonsState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next") // На первой странице только кнопка "Next"
                    1 -> listOf("Back", "Next") // На второй странице кнопки "Back" и "Next"
                    2 -> listOf("Back", "Get Started") // На третьей странице кнопки "Back" и "Get Started"
                    else -> listOf("", "") // В других случаях кнопки не отображаются
                }
            }
        }

        // Создаем горизонтальный Pager, который отображает страницы
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f) /* Заполнитель для разделения Pager и кнопок */)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(), // Учитываем системную панель навигации

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Добавляем индикатор страниц
            PagerIndicator(
                modifier = Modifier.width(52.dp),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()

                // Скрыть кнопку "Back", если первый элемент в списке кнопок пуст
                if (buttonsState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonsState.value[0],
                        onClick = {
                            // Переход к предыдущей странице при нажатии "Back"
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonsState.value[1],
                    onClick = {
                        // Переход к следующей странице или завершение ввода при нажатии "Next" или "Get Started"
                        scope.launch {
                            if (pagerState.currentPage == 3) {
                                // Перейти на главный экран и сохранить значение в хранилище данных
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.5f) /* Дополнительный заполнитель внизу */)
    }
}
