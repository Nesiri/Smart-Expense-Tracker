package com.example.expense_tracker_app.screens


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.expense_tracker_app.R
import com.example.expense_tracker_app.data.categories
import com.example.expense_tracker_app.data.expenses
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.viewModel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
    ){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Expense Tracker")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
                 },
        floatingActionButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("add") },
                    modifier = Modifier.offset(x = 20.dp)
                ) {
                    Text("Add Expense")
                }

                Button(onClick = { navController.navigate("edit") }) {
                    Text("Edit Expense")
                }
            }
        }
    ){
        paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.tertiary)

        ) {
            val expenses by viewModel.expenses.collectAsState()
            val categories by viewModel.categories.collectAsState()

            Text(
                text = "Categories",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier= Modifier.height( 8.dp))
            CategoriesRow(categories)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Monthly Overview", fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExpenseSummaryCard()
            //SCROLLABLE SECTION ONLY
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                expenses.forEach { item ->
                    ExpenseItem(
                        title = item.title,
                        category = item.category,
                        amount = item.amount,
                        description = item.description
                    )
                }
            }
        }
    }
}
@Composable
fun CategoriesRow(categories: List<Category>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { item ->
            CategoryItem(item.title, item.icon)
        }
    }
}

@Composable
fun CategoryItem(title: String, @DrawableRes icon: Int) {
    Box(
        modifier = Modifier
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

            Spacer(modifier = Modifier.height(6.dp)) // pushes icon slightly down from top area

            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .size(50.dp) //  BIG icon
                    .offset(y = (-3).dp) //  slight upward shift
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
fun ExpenseSummaryCard(){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(contentColor =  Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "March 2026", fontSize = 12.sp,
                color = MaterialTheme.colorScheme. primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
            Text(
                "Monthly expense summary", fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "amount : $500",
                    color = Color(0xFF00C853),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "plan: $500",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun ExpenseItem(
    title: String,
    category: String,
    amount: Double,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                category,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.outline
            )

            Text(title, fontWeight = FontWeight.Bold)

            Text(description, fontSize = 12.sp)

            // AMOUNT LEFT + PLAN RIGHT
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "amount : $amount",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "plan: $500",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}