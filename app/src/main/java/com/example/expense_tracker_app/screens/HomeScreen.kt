package com.example.expense_tracker_app.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.navigation.Screen
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.ui.theme.component.showDatePicker
import com.example.expense_tracker_app.viewModel.HomeViewModel
import com.example.expense_tracker_app.viewModel.HomeViewModelFactory

@Composable
fun HomeScreen(
    navController: NavHostController,
    appRepository: AppRepository
) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(appRepository)
    )

    val categories by homeViewModel.categories.collectAsState()
    val expenses by homeViewModel.filteredExpenses.collectAsState(initial = emptyList())
    val selectedDate by homeViewModel.selectedDate.collectAsState()
    val total by homeViewModel.totalAmount.collectAsState(initial = 0.0)
    val planned by homeViewModel.totalPlannedAmount.collectAsState(initial = 0.0)

    AppScaffold(
        navController = navController,
        title = Screen.Home.title,
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.tertiary)
        ) {
            Text(
                text = "Categories",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            CategoriesRow(
                categories = categories,
                navController = navController
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Monthly Overview",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (categories.isNotEmpty()) {
                ExpenseSummaryCard(
                    selectedDate = selectedDate,
                    onDateSelected = { homeViewModel.setDate(it) },
                    total = total,
                    planned = planned
                )

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 3.dp
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    var selectedDeleteExpense by remember { mutableStateOf<Expense?>(null) }

                    expenses.forEach { expense ->
                        ExpenseItem(
                            id = expense.id.toString(),
                            title = expense.title,
                            category = expense.category,
                            amount = expense.amount,
                            planned = expense.plannedAmount,
                            description = expense.description,
                            navController = navController,
                            isDeleteMode = selectedDeleteExpense?.id == expense.id,
                            onEdit = { expenseId ->
                                navController.navigate(Screen.Edit.createRoute(expenseId.toInt()))
                            },
                            onDelete = {
                                if (selectedDeleteExpense?.id == expense.id) {
                                    homeViewModel.deleteExpense(selectedDeleteExpense!!)
                                    selectedDeleteExpense = null
                                } else {
                                    selectedDeleteExpense = expense
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun ExpenseSummaryCard(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    total: Double,
    planned: Double
) {
    val context = LocalContext.current
    val color = if (total <= planned) {
        Color(0xFF00C853)
    } else {
        Color(0xFFD50000)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = selectedDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePicker(context) { date ->
                            onDateSelected(date)
                        }
                    },
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Right
            )

            Text(
                "Monthly expense summary",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "amount : $$total",
                    color = color,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "plan: $$planned",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun CategoriesRow(
    categories: List<Category>,
    navController: NavHostController
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { item ->
            CategoryItem(
                title = item.title,
                icon = item.icon,
                onClick = {
                    if (item.title.lowercase() != "total") {
                        navController.navigate(
                            Screen.Category.createRoute(item.title)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryItem(title: String, @DrawableRes icon: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 5.dp)
            .size(100.dp)
            .background(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = (-3).dp)
            )
            Text(
                text = title,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
    }
}

@Composable
fun ExpenseItem(
    id: String,
    title: String,
    category: String,
    amount: Double,
    planned: Double,
    description: String,
    isDeleteMode: Boolean,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit,
    navController: NavHostController
) {
    val color = if (amount <= planned) {
        Color(0xFF00C853)
    } else {
        Color(0xFFD50000)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = { onDelete(id) },
                onDoubleClick = {
                    if (id.isNotBlank()) {
                        onEdit(id)
                    }
                }
            ),
        border = if (isDeleteMode) {
            BorderStroke(2.dp, Color.Red)
        } else null
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(category, fontSize = 10.sp)
            Text(title, fontWeight = FontWeight.Bold)
            Text(description, fontSize = 12.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "amount: $amount",
                    color = color,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "plan: $planned",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Light
                )
                if (isDeleteMode) {
                    Text(
                        text = "🗑",
                        color = Color.Red,
                        modifier = Modifier.clickable { onDelete(id) }
                    )
                }
            }
        }
    }
}