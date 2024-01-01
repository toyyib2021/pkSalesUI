package com.pureKnowledge.salesApp.data

import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.Product
import com.pureKnowledge.salesApp.model.ProductRecord

val customers = listOf<Customer>(
    Customer(name = "Pure Knowledge Ltd", phone = "07030857693", address = "A Division Police Barrack Warri", status = "School", date = "12-06-2023" ),
    Customer(name = "Sure Knowledge Ltd", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Rep", date = "12-06-2023" ),
    Customer(name = "Success Key Ltd", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Others", date = "12-06-2023" ),
    Customer(name = "AK Publishing", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Publisher", date = "12-06-2023" ),
    Customer(name = "Pure Knowledge Ltd Two", phone = "07030857693", address = "A Division Police Barrack Warri", status = "School", date = "12-06-2023" ),
    Customer(name = "Sure Knowledge Ltd Two", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Rep", date = "12-06-2023" ),
    Customer(name = "Success Key Ltd Two", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Others", date = "12-06-2023" ),
    Customer(name = "AK Publishing Two", phone = "07030857693", address = "A Division Police Barrack Warri", status = "Publisher", date = "12-06-2023" ),
)

val product = listOf<Product>(
    Product(productName = "Dot-To-Dot Number Activity For KG", categories = "Kindergarten", type = "", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Dot-To-Dot Letter Activity For KG", categories = "Kindergarten", type = "", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy Patterns Ans Writing For KG", categories = "Kindergarten", type = "", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy English For Nursery", categories = "Nursery", type = "Book One", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy English For Nursery", categories = "Nursery", type = "Book Two", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy English For Nursery", categories = "Nursery", type = "Book Three", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy Mathematics For Nursery", categories = "Nursery", type = "Book One", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy Mathematics For Nursery", categories = "Nursery", type = "Book Two", price = "1,200", repPrice = "900", discountPrice = "1,000"),
    Product(productName = "Legacy Mathematics For Nursery", categories = "Nursery", type = "Book Three", price = "1,200", repPrice = "900", discountPrice = "1,000"),
)


