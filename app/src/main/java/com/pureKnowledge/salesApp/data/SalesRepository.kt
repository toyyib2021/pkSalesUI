package com.pureKnowledge.salesApp.data

import com.pureKnowledge.salesApp.model.Activation
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerType
import com.pureKnowledge.salesApp.model.Order
import com.pureKnowledge.salesApp.model.Payment
import com.pureKnowledge.salesApp.model.PaymentPlan
import com.pureKnowledge.salesApp.model.Product
import com.pureKnowledge.salesApp.model.ProductRecord
import com.pureKnowledge.salesApp.model.ProductType
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.model.SoldProduct
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface SalesRepository {
    fun configureTheRealm()


    // Sold Product
    fun getSoldProduct(): Flow<List<SoldProduct>>
    fun filterSoldProductByCustomerID(customerID: String): Flow<List<SoldProduct>>
    fun filterSoldProductByProductId(productId: String): Flow<List<SoldProduct>>
    suspend fun insertSoldProduct(soldProduct: SoldProduct)
    suspend fun updateSoldProduct(soldProduct: SoldProduct)
    suspend fun deleteSoldProduct(id: ObjectId)
    suspend fun deleteAllSoldProduct(soldProduct: List<SoldProduct>)


    // ProductRecord
    fun getProductRecord(): Flow<List<ProductRecord>>
    fun filterProductRecordByProductId(productID: String): Flow<List<ProductRecord>>
    suspend fun insertProductRecord(productRecord: ProductRecord)
    suspend fun updateProductRecord(productRecord: ProductRecord)
    suspend fun deleteProductRecord(id: ObjectId)
    suspend fun deleteAllProductRecord(productRecord: List<ProductRecord>)


    // Payment
    fun getPayment(): Flow<List<Payment>>
    fun filterPaymentByDate(date: String): Flow<List<Payment>>
    fun filterPaymentByCustomerId(customerId: String): Flow<List<Payment>>
    suspend fun insertPayment(payment: Payment)
    suspend fun updatePayment(payment: Payment)
    suspend fun deletePayment(id: ObjectId)
    suspend fun deleteAllPayment(payment: List<Payment>)


    // Order
    fun getOrder(): Flow<List<Order>>
    fun filterOrderByPaidAmount(paidAmount: String): Flow<List<Order>>
    fun filterOrderByCustomerId(customerId: String): Flow<List<Order>>
    fun filterOrderByOrderId(orderId: String): Flow<List<Order>>
    fun filterOrderByDate(date: String): Flow<List<Order>>
    suspend fun insertOrder(order: Order)
    suspend fun updateOrder(order: Order)
    suspend fun deleteOrder(id: ObjectId)
    suspend fun deleteAllOrder(order: List<Order>)

    // Product
    fun getProduct(): Flow<List<Product>>
    fun filterProductByName(productName: String): Flow<List<Product>>
    fun filterProductByserviceOrProduct(serviceOrProduct: String): Flow<List<Product>>
    fun filterProductByCategory(category: String): Flow<List<Product>>
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(id: ObjectId)
    suspend fun deleteAllProduct(product: List<Product>)


    // Customer
    fun getCustomer(): Flow<List<Customer>>
    fun filterCustomerByName(customerName: String): Flow<List<Customer>>
    fun filterCustomerByPhone(customerPhone: String): Flow<List<Customer>>
    fun filterCustomerByType(customerType: String): Flow<List<Customer>>
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(id: ObjectId)
    suspend fun deleteAllCustomer(customer: List<Customer>)


    // PaymentPlan
    fun getPaymentPlan(): Flow<List<PaymentPlan>>
    fun filterPaymentPlanByName(name: String): Flow<List<PaymentPlan>>
    suspend fun insertPaymentPlan(paymentPlan: PaymentPlan)
    suspend fun updatePaymentPlan(paymentPlan: PaymentPlan)
    suspend fun deletePaymentPlan(id: ObjectId)
    suspend fun deleteAllPaymentPlan(paymentPlan: List<PaymentPlan>)


    // Activation
    fun getActivation(): Flow<List<Activation>>
    fun filterActivationByName(name: String): Flow<List<Activation>>
    suspend fun insertActivation(activation: Activation)
    suspend fun updateActivation(activation: Activation)
    suspend fun deleteActivation(id: ObjectId)
    suspend fun deleteAllActivation(activation: List<Activation>)

    // Product Type
    fun getProductTypes(): Flow<List<ProductType>>
//    fun filterProductTypes(name: String): Flow<List<ProductType>>
    suspend fun insertProductType(productType: ProductType)
    suspend fun updateProductType(productType: ProductType)
    suspend fun deleteProductType(id: ObjectId)
    suspend fun deleteAllProductType(productType: List<ProductType>)


    // Customer Type
    fun getCustomerType(): Flow<List<CustomerType>>
    fun filterCustomerType(name: String): Flow<List<CustomerType>>
    suspend fun insertCustomerType(customer: CustomerType)
    suspend fun updateCustomerType(customer: CustomerType)
    suspend fun deleteCustomerType(id: ObjectId)
    suspend fun deleteAllCustomerType(customerType: List<CustomerType>)

    // Categories
    fun getCategories(): Flow<List<Categories>>
    fun filterCategoriesByName(name: String): Flow<List<Categories>>
    suspend fun insertCategories(categories: Categories)
    suspend fun updateCategories(categories: Categories)
    suspend fun deleteCategories(id: ObjectId)
    suspend fun deleteAllCategories(categories: List<Categories>)

    // Product Unit
    fun getProductUnit(): Flow<List<ProductUnit>>
    fun filterProductUnitByName(name: String): Flow<List<ProductUnit>>
    suspend fun insertProductUnit(productUnit: ProductUnit)
    suspend fun updateProductUnit(productUnit: ProductUnit)
    suspend fun deleteProductUnit(id: ObjectId)
    suspend fun deleteAllProductUnit(productUnit: List<ProductUnit>)
}